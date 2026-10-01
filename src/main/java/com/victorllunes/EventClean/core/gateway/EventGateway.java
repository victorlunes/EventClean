package com.victorllunes.EventClean.core.gateway;

import com.victorllunes.EventClean.core.entities.Event;

public interface EventGateway {

    public Event createEvent(Event event);
}
