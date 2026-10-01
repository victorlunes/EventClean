package com.victorllunes.EventClean.infrastructure.gateway;

import com.victorllunes.EventClean.core.entities.Event;
import com.victorllunes.EventClean.core.gateway.EventGateway;
import com.victorllunes.EventClean.infrastructure.mapper.event.EventEntityMapper;
import com.victorllunes.EventClean.infrastructure.persistence.EventEntity;
import com.victorllunes.EventClean.infrastructure.persistence.EventRespository;
import org.springframework.stereotype.Component;

@Component
public class EventRepositoryGateway implements EventGateway {

    private final EventRespository eventRespository;
    private final EventEntityMapper eventEntityMapper;

    public EventRepositoryGateway(
            EventRespository repository,
            EventEntityMapper eventEntityMapper
    ) {
        this.eventRespository = repository;
        this.eventEntityMapper = eventEntityMapper;
    }

    @Override
    public Event createEvent(Event event) {
        EventEntity eventEntity = eventEntityMapper.toEventEntity(event);
        EventEntity newEvent = eventRespository.save(eventEntity);
        return eventEntityMapper.toEvent(newEvent);
    }
}
