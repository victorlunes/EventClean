package com.victorllunes.EventClean.core.useCases.findEventByIdentificator;

import com.victorllunes.EventClean.core.entities.Event;
import com.victorllunes.EventClean.core.gateway.EventGateway;
import com.victorllunes.EventClean.infrastructure.gateway.EventRepositoryGateway;
import com.victorllunes.EventClean.infrastructure.presentation.exception.event.EventNotExistException;

import java.util.Optional;

public class FindEventByIdentificatorUseCaseImpl implements FindEventByIdentificatorUseCase {

    private final EventGateway eventGateway;

    public FindEventByIdentificatorUseCaseImpl(EventGateway eventGateway) {
        this.eventGateway = eventGateway;
    }

    @Override
    public Event execute(String identificator) {
         if(eventGateway.existsByIdentificator(identificator)){
             return eventGateway.findByIdentificator(identificator);
         }

         throw new EventNotExistException();
    }
}
