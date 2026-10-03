package com.victorllunes.EventClean.infrastructure.presentation;

import com.victorllunes.EventClean.core.entities.Event;
import com.victorllunes.EventClean.core.useCases.createEvent.CreateEventUseCase;
import com.victorllunes.EventClean.infrastructure.dtos.event.EventDto;
import com.victorllunes.EventClean.infrastructure.mapper.event.EventMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
 */
@RestController
@RequestMapping("/event")
public class EventController {

    // Depende da INTERFACE do caso de uso; o Spring injeta o bean criado no BeanConfiguration.
    private final CreateEventUseCase createEventUseCase;
    private final EventMapper eventMapper;

    public EventController(CreateEventUseCase createEventUseCase, EventMapper eventMapper) {
        this.createEventUseCase = createEventUseCase;
        this.eventMapper = eventMapper;
    }

    @PostMapping("create-event")
    public EventDto createEvent(@RequestBody EventDto eventDto ) {
        // Passos 1 a 7: DTO -> Event -> caso de uso -> ... -> Event salvo
        Event newEvent = createEventUseCase.execute(eventMapper.toEvent(eventDto));
        // Passo 8: Event -> DTO de resposta
        return eventMapper.toEventDto(newEvent);
    }
}
