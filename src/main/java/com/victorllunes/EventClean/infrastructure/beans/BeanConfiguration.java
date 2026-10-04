package com.victorllunes.EventClean.infrastructure.beans;

import com.victorllunes.EventClean.core.gateway.EventGateway;
import com.victorllunes.EventClean.core.useCases.allEvents.AllEventsUseCase;
import com.victorllunes.EventClean.core.useCases.allEvents.AllEventsUseCaseImpl;
import com.victorllunes.EventClean.core.useCases.createEvent.CreateEventUseCaseImpl;
import com.victorllunes.EventClean.core.useCases.createEvent.CreateEventUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * CAMADA: infrastructure — CONFIGURAÇÃO / "COLA" (composition root).
 *
 * Como as classes do core não têm @Service/@Component (para não depender do Spring),
 * o Spring não as encontra sozinho. Esta classe ensina o Spring a criá-las.
 *
 * É o único lugar onde "ligamos os fios": pega a implementação concreta do
 * EventGateway (EventRepositoryGateway, que é um @Component) e entrega ao use case.
 *
 * COMO O SPRING LIGA TUDO (injeção de dependência):
 * Pense no Spring como um "armário de objetos" (o container). Ao subir a aplicação,
 * ele cria os beans e guarda cada um com uma etiqueta: o seu TIPO.
 *
 *   Etiqueta (tipo)      Objeto guardado                De onde veio
 *   EventRespository     implementação gerada           extends JpaRepository
 *   EventEntityMapper    new EventEntityMapper()        @Component
 *   EventMapper          new EventMapper()              @Component
 *   EventGateway         new EventRepositoryGateway()   @Component + implements EventGateway
 *   CreateEventUseCase   new CreateEventUseCaseImpl()   @Bean abaixo
 *   AllEventsUseCase     new AllEventsUseCaseImpl()     @Bean abaixo
 *
 * Num método @Bean, a etiqueta é o TIPO DE RETORNO do método (por isso retornamos
 * a interface CreateEventUseCase, e não CreateEventUseCaseImpl).
 *
 * Quando o Spring vai criar uma classe (ex.: o EventController), ele olha os
 * parâmetros do construtor, procura no armário um objeto de cada TIPO pedido e
 * chama o construtor com eles. O nome do método/parâmetro não importa, só o tipo:
 *
 *   aqui:            public CreateEventUseCase createEventUseCase(...)
 *   EventController: public EventController(CreateEventUseCase createEventUseCase, ...)
 *                    -> mesmo tipo, o Spring conecta
 *
 * Ordem em que o Spring monta (ele resolve sozinho: cria primeiro o que cada um pede):
 *   EventRespository + EventEntityMapper
 *     -> EventRepositoryGateway  (guardado como EventGateway)
 *     -> CreateEventUseCaseImpl  (guardado como CreateEventUseCase, por este @Bean)
 *     -> EventController         (recebe CreateEventUseCase e EventMapper)
 *
 * Sem os métodos @Bean, a aplicação nem sobe:
 *   "No qualifying bean of type 'CreateEventUseCase' available"
 *
 * Regra prática: cada novo caso de uso do core ganha um método @Bean aqui.
 */
@Configuration
public class BeanConfiguration {

    // O Spring procura um bean do tipo EventGateway (encontra o EventRepositoryGateway)
    // e passa como parâmetro. O objeto retornado é guardado com a etiqueta
    // CreateEventUseCase, e é ele que o EventController recebe no construtor.
    @Bean
    public CreateEventUseCase createEventUseCase(EventGateway eventGateway) {
        return new CreateEventUseCaseImpl(eventGateway);
    }

    @Bean
    public AllEventsUseCase allEventsUseCase(EventGateway eventGateway) {
        return new AllEventsUseCaseImpl(eventGateway);
    }
}
