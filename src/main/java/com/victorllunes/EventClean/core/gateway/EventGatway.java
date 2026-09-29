package com.victorllunes.EventClean.core.gateway;

import com.victorllunes.EventClean.core.entities.Event;

public interface EventGatway {

    public Event createEvent(Event event);
}
