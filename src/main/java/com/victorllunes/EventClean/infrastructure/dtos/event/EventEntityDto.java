package com.victorllunes.EventClean.infrastructure.dtos.event;

import com.victorllunes.EventClean.core.enums.TypeEvent;

import java.time.LocalDateTime;

public record EventEntityDto(
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
