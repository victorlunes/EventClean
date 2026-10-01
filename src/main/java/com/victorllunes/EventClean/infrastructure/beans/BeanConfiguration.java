package com.victorllunes.EventClean.infrastructure.beans;

import com.victorllunes.EventClean.core.gateway.EventGateway;
import com.victorllunes.EventClean.core.useCases.createEvent.CreateEventUseCaseImpl;
import com.victorllunes.EventClean.core.useCases.createEvent.CreateEventUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CreateEventUseCase createEventUseCase(EventGateway eventGateway) {
        return new CreateEventUseCaseImpl(eventGateway);
    }
}
