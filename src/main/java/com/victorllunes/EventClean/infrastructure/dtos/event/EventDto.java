package com.victorllunes.EventClean.infrastructure.dtos.event;

import com.victorllunes.EventClean.core.enums.TypeEvent;

import java.time.LocalDateTime;

/**
 * CAMADA: infrastructure — DTO (Data Transfer Object) da API.
 *
 * Representa o formato do JSON que ENTRA e SAI pelo EventController.
 * Existe separado da entidade Event para que o contrato da API possa mudar
 * (esconder campos, renomear, adicionar validações como @NotBlank) sem
 * afetar o core — e vice-versa.
 *
 * Conversão DTO <-> Event: infrastructure/mapper/event/EventMapper.
 */
public record EventDto(
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
