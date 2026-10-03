package com.victorllunes.EventClean.core.useCases.createEvent;

import com.victorllunes.EventClean.core.entities.Event;

/**
 * CAMADA: core — CASO DE USO (interface).
 *
 * Descreve UMA ação que o sistema sabe fazer: "criar um evento".
 * Cada caso de uso costuma ter sua própria interface com um único método (execute).
 *
 * Quem usa:        infrastructure/presentation/EventController.
 * Quem implementa: CreateEventUseCaseImpl.
 *
 * O controller depende desta interface, não da implementação — assim é fácil
 * trocar a implementação ou criar um mock em testes.
 */
public interface CreateEventUseCase {
    public Event execute(Event event);
}
