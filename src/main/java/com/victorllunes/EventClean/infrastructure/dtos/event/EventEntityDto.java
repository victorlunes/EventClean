package com.victorllunes.EventClean.infrastructure.dtos.event;

import com.victorllunes.EventClean.core.enums.TypeEvent;

import java.time.LocalDateTime;

/**
 * CAMADA: infrastructure — DTO.
 *
 * Atenção: esta classe NÃO está sendo usada em nenhum lugar do projeto no momento.
 * A conversão para o banco é feita direto entre Event e EventEntity
 * (veja EventEntityMapper), então este DTO pode ser removido se não houver
 * um uso planejado para ele.
 */
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
