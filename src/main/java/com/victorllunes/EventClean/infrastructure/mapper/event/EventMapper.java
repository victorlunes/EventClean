package com.victorllunes.EventClean.infrastructure.mapper.event;

import com.victorllunes.EventClean.core.entities.Event;
import com.victorllunes.EventClean.infrastructure.dtos.event.EventDto;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {
    public Event toEvent(EventDto eventDto) {
         return new Event(
                 eventDto.id(),
                eventDto.name(),
                eventDto.location(),
                eventDto.organizer(),
                eventDto.description(),
                eventDto.identificator(),
                eventDto.endEvent(),
                eventDto.startEvent(),
                eventDto.capacity(),
                eventDto.typeEvent()

        );
    }

    public EventDto toEventDto(Event event) {
        return new EventDto(
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
