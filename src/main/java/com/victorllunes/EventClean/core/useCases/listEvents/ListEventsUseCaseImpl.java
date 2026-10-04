package com.victorllunes.EventClean.core.useCases.listEvents;

import com.victorllunes.EventClean.core.entities.Event;
import com.victorllunes.EventClean.core.gateway.EventGateway;

import java.util.List;

/**
 * CAMADA: core — CASO DE USO (implementação).
 *
 * Lógica de "listar todos os eventos". Hoje só repassa para o gateway, mas
 * regras de negócio da listagem entrariam aqui (ex.: esconder eventos que
 * já terminaram, ordenar por data de início).
 *
 * Como o CreateEventUseCaseImpl, é Java puro: quem cria esta classe é o
 * método @Bean listEventsUseCase do infrastructure/beans/BeanConfiguration.
 */
public class ListEventsUseCaseImpl implements ListEventsUseCase {

    // Mesmo EventGateway usado pelo CreateEventUseCaseImpl: o Spring entrega
    // o mesmo objeto (EventRepositoryGateway) aos dois casos de uso.
    private final EventGateway eventGateway;

    public ListEventsUseCaseImpl(EventGateway eventGateway) {
        this.eventGateway = eventGateway;
    }

    // (listar) Passo 2: pede ao gateway todos os eventos (passo 3),
    // sem saber que por trás existe um banco de dados.
    @Override
    public List<Event> execute() {
        return eventGateway.findAll();
    }
}
