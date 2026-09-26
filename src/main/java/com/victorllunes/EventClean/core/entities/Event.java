package com.victorllunes.EventClean.core.entities;

import com.victorllunes.EventClean.core.enums.TypeEvent;

import java.time.LocalDateTime;

public record Event(
        Long id,
        String name,
        String location,
        String organizer,
        String description,
        String identificator,
        LocalDateTime endEvent,
        LocalDateTime startEvent,
        int capacity,
        TypeEvent typeEvent
) {

}
