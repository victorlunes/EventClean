package com.victorllunes.EventClean.infrastructure.mapper.event;

import com.victorllunes.EventClean.core.entities.Event;
import com.victorllunes.EventClean.infrastructure.persistence.EventEntity;
import org.springframework.stereotype.Component;

@Component
public class EventEntityMapper {
    public Event toEvent(EventEntity eventEntity) {
        return new Event(
                eventEntity.getId(),
                eventEntity.getName(),
                eventEntity.getLocation(),
                eventEntity.getOrganizer(),
                eventEntity.getDescription(),
                eventEntity.getIdentificator(),
                eventEntity.getEndEvent(),
                eventEntity.getStartEvent(),
                eventEntity.getCapacity(),
                eventEntity.getTypeEvent()
        );
    }

    public EventEntity toEventEntity(Event event) {
        return new EventEntity(
                event.id(),
                event.name(),
                event.location(),
                event.organizer(),
                event.description(),
                event.identificator(),
                event.endEvent(),
                event.startEvent(),
                event.capacity(),
                event.typeEvent()
        );
    }
}
