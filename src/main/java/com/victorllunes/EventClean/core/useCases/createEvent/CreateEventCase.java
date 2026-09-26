package com.victorllunes.EventClean.core.useCases.createEvent;

import com.victorllunes.EventClean.core.entities.Event;

public interface CreateEventCase {
    public Event execute(Event event);
}
