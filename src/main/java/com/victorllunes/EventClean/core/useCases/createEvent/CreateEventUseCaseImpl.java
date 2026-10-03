package com.victorllunes.EventClean.core.useCases.createEvent;

import com.victorllunes.EventClean.core.entities.Event;
import com.victorllunes.EventClean.core.gateway.EventGateway;

/**
 * CAMADA: core — CASO DE USO (implementação).
 *
 * É aqui que mora a LÓGICA da aplicação para criar um evento: validações,
 * regras ("não pode criar evento com data no passado", "identificador deve
 * ser único"...), e a orquestração do que precisa acontecer.
 * Hoje ele só repassa para o gateway, mas é o lugar certo para crescer.
 *
 * Note que não há @Service nem nenhuma anotação do Spring: o core é Java puro.
 * Quem cria esta classe e injeta o EventGateway é o
 * infrastructure/beans/BeanConfiguration.
 */
public class CreateEventUseCaseImpl implements CreateEventUseCase {

    // Depende da INTERFACE do core, não de EventRepositoryGateway nem do JPA.
    private final EventGateway eventGateway;

    // Injeção de dependência via construtor (feita pelo BeanConfiguration).
    public CreateEventUseCaseImpl(EventGateway eventGateway) {
        this.eventGateway = eventGateway;
    }

    // Passo 3 do fluxo: recebe o Event (já convertido pelo controller)
    // e pede ao gateway para persisti-lo (passo 4).
    @Override
    public Event execute(Event event) {
        return eventGateway.createEvent(event);
    }
}
