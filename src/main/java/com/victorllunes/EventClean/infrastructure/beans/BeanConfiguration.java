package com.victorllunes.EventClean.infrastructure.beans;

import com.victorllunes.EventClean.core.gateway.EventGateway;
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
 */
@Configuration
public class BeanConfiguration {

    // O Spring procura um bean do tipo EventGateway (encontra o EventRepositoryGateway),
    // passa como parâmetro, e registra o CreateEventUseCase retornado como bean.
    // A partir daí, o EventController pode recebê-lo no construtor.
    @Bean
    public CreateEventUseCase createEventUseCase(EventGateway eventGateway) {
        return new CreateEventUseCaseImpl(eventGateway);
    }
}
