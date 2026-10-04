package com.victorllunes.EventClean.infrastructure.mapper.event;

import com.victorllunes.EventClean.core.entities.Event;
import com.victorllunes.EventClean.infrastructure.persistence.EventEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * CAMADA: infrastructure — MAPPER (domínio <-> banco).
 *
 * Converte entre:
 *   - Event       (entidade do core, Java puro)
 *   - EventEntity (entidade JPA, com anotações de banco)
 *
 * Usado pelo EventRepositoryGateway. Graças a ele, o core nunca precisa
 * enxergar anotações do JPA.
 */
@Component
public class EventEntityMapper {

    // Banco -> domínio: usado depois de salvar/buscar no repositório.
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

    // Domínio -> banco: usado antes de chamar o repositório.
    // A ordem dos argumentos precisa bater com a ordem dos campos de EventEntity
    // (o construtor é gerado pelo @AllArgsConstructor do Lombok).
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

    // Banco -> domínio para listas: aplica o toEvent em cada item.
    // Usado no allEvents do EventRepositoryGateway.
    // Obs.: apesar do nome, ele devolve List<Event> (e não de EventEntity);
    // "toEventList" descreveria melhor o retorno.
    public List<Event> toEventEntityList(List<EventEntity> eventEntityList) {
        return eventEntityList.stream()
                .map(event -> this.toEvent(event))
                .toList();
    }
}
