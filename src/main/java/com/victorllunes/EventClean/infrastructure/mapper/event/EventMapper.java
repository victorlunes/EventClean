package com.victorllunes.EventClean.infrastructure.mapper.event;

import com.victorllunes.EventClean.core.entities.Event;
import com.victorllunes.EventClean.infrastructure.dtos.event.EventDto;
import org.springframework.stereotype.Component;

/**
 * CAMADA: infrastructure — MAPPER (API <-> domínio).
 *
 * Converte entre:
 *   - EventDto (formato do JSON da API)
 *   - Event    (entidade do core)
 *
 * Usado pelo EventController: na entrada (DTO -> Event) antes de chamar o use case,
 * e na saída (Event -> DTO) antes de responder ao cliente.
 */
@Component
public class EventMapper {

    // Entrada: JSON recebido -> objeto de domínio (passo 2 do fluxo).
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

    // Saída: objeto de domínio -> JSON de resposta (passo 8 do fluxo).
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
