package com.victorllunes.EventClean.infrastructure.presentation;

import com.victorllunes.EventClean.core.entities.Event;
import com.victorllunes.EventClean.core.useCases.allEvents.AllEventsUseCase;
import com.victorllunes.EventClean.core.useCases.createEvent.CreateEventUseCase;
import com.victorllunes.EventClean.infrastructure.dtos.event.EventDto;
import com.victorllunes.EventClean.infrastructure.mapper.event.EventMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CAMADA: infrastructure — APRESENTAÇÃO (porta de entrada HTTP).
 *
 * Responsabilidade: receber a requisição, converter os dados, chamar o caso de
 * uso e devolver a resposta. NÃO deve conter regra de negócio — isso é papel
 * do use case.
 *
 * Fluxo completo de POST /event/create-event:
 *   1. EventController recebe o JSON como EventDto
 *   2. EventMapper converte EventDto -> Event
 *   3. CreateEventUseCase.execute(event)          (core)
 *   4. EventGateway.createEvent(event)            (interface do core)
 *   5. EventRepositoryGateway converte Event -> EventEntity (EventEntityMapper)
 *   6. EventRespository.save(...) grava no banco
 *   7. EventEntity salvo -> Event (EventEntityMapper) e volta para o use case
 *   8. EventMapper converte Event -> EventDto e o Spring devolve como JSON
 *
 * Fluxo completo de GET /event/all-events (nos outros arquivos aparece como "(listar) Passo N"):
 *   1. EventController recebe a requisição (não há corpo, então não há DTO de entrada)
 *   2. AllEventsUseCase.execute()                 (core)
 *   3. EventGateway.allEvents()                   (interface do core)
 *   4. EventRepositoryGateway chama EventRespository.findAll() -> List<EventEntity>
 *   5. EventEntityMapper converte cada EventEntity -> Event
 *   6. A List<Event> volta pelo use case até o controller e o Spring devolve como JSON
 */
@RestController
@RequestMapping("/event")
public class EventController {

    // Depende da INTERFACE do caso de uso; o Spring injeta o bean criado no BeanConfiguration.
    private final CreateEventUseCase createEventUseCase;
    private final AllEventsUseCase allEventsUseCase;
    private final EventMapper eventMapper;

    // O controller NÃO cria nem procura suas dependências: só declara no construtor
    // o que precisa. Ao criar o controller, o Spring procura no container um objeto
    // de cada TIPO pedido e o passa aqui (injeção de dependência):
    //   CreateEventUseCase -> criado pelo @Bean no BeanConfiguration
    //   AllEventsUseCase   -> criado pelo @Bean no BeanConfiguration
    //   EventMapper        -> criado pelo Spring por causa do @Component
    // O controller não sabe qual classe concreta recebeu (ex.: CreateEventUseCaseImpl).
    //
    // COMO O BeanConfiguration FUNCIONA (versão simples):
    // As classes do core não têm @Service, então o Spring não sabe criá-las sozinho.
    // O BeanConfiguration é uma "receita" que ensina o Spring a fazer isso:
    //
    //   @Bean
    //   public CreateEventUseCase createEventUseCase(EventGateway eventGateway) {
    //       return new CreateEventUseCaseImpl(eventGateway);
    //   }
    //
    // Lendo a receita: "quando alguém pedir um CreateEventUseCase, entregue um
    // new CreateEventUseCaseImpl(...)". O Spring executa esse método uma vez ao subir
    // a aplicação, guarda o objeto e entrega esse mesmo objeto a quem pedir esse TIPO.
    // Como este construtor pede um CreateEventUseCase, ele recebe esse objeto.
    //
    // Analogia: o controller é um cliente que pede "um CreateEventUseCase" no balcão;
    // o BeanConfiguration é a receita que diz à cozinha (o Spring) como preparar o pedido.
    //
    // Explicação detalhada em infrastructure/beans/BeanConfiguration.
    public EventController(
            CreateEventUseCase createEventUseCase,
            EventMapper eventMapper,
            AllEventsUseCase allEventsUseCase) {
        this.createEventUseCase = createEventUseCase;
        this.eventMapper = eventMapper;
        this.allEventsUseCase = allEventsUseCase;
    }

    @PostMapping("create-event")
    public EventDto createEvent(@RequestBody EventDto eventDto ) {
        // Passos 1 a 7: DTO -> Event -> caso de uso -> ... -> Event salvo
        Event newEvent = createEventUseCase.execute(eventMapper.toEvent(eventDto));
        // Passo 8: Event -> DTO de resposta
        return eventMapper.toEventDto(newEvent);
    }

    @GetMapping("all-events")
    public List<Event> getAllEvents() {
        // (listar) Passos 1 a 6: o use case devolve os eventos já como List<Event>.
        // Atenção: aqui a entidade do domínio (Event) sai direto como JSON. No
        // create-event usamos EventDto para separar o contrato da API do core;
        // para manter o mesmo padrão, o ideal seria retornar List<EventDto>
        // convertendo cada item com eventMapper.toEventDto.
        return allEventsUseCase.execute();
    }
}
