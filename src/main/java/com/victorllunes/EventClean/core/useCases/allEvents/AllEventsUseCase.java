package com.victorllunes.EventClean.core.useCases.allEvents;

import com.victorllunes.EventClean.core.entities.Event;

import java.util.List;

/**
 * CAMADA: core — CASO DE USO (interface).
 *
 * Descreve a ação "listar todos os eventos".
 * Segue o mesmo padrão do CreateEventUseCase: uma interface por caso de uso,
 * com um único método execute (aqui sem parâmetros, pois não há filtro).
 *
 * Quem usa:        infrastructure/presentation/EventController (GET /event/all-events).
 * Quem implementa: AllEventsUseCaseImpl.
 */
public interface AllEventsUseCase {
    public List<Event> execute();
}
