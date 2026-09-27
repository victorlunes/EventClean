package com.victorllunes.EventClean.infrastructure.presentation;

import com.victorllunes.EventClean.core.entities.Event;
import com.victorllunes.EventClean.core.useCases.createEvent.CreateEventUseCase;
import com.victorllunes.EventClean.infrastructure.dtos.event.EventDto;
import com.victorllunes.EventClean.infrastructure.mapper.event.EventMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController {

    private final CreateEventUseCase createEventUseCase;
    private final EventMapper eventMapper;

    public EventController(CreateEventUseCase createEventUseCase, EventMapper eventMapper) {
        this.createEventUseCase = createEventUseCase;
        this.eventMapper = eventMapper;
    }

    @PostMapping("create-event")
    public EventDto createEvent(@RequestBody EventDto eventDto ) {
        Event newEvent = createEventUseCase.execute(eventMapper.toEvent(eventDto));
        return eventMapper.toEventDto(newEvent);
    }
}
