package com.victorllunes.EventClean.core.useCases.createEvent;

import com.victorllunes.EventClean.core.entities.Event;

public interface CreateEventUseCase {
    public Event execute(Event event);
}
