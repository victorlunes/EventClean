package com.victorllunes.EventClean.core.gateway;

import com.victorllunes.EventClean.core.entities.Event;

import java.util.List;

/**
 * CAMADA: core — GATEWAY (também chamado de "porta" / port).
 *
 * É o CONTRATO que o core define dizendo: "eu preciso que alguém consiga salvar
 * e listar eventos". O core NÃO sabe como isso é feito (banco SQL, API externa,
 * arquivo, memória...).
 *
 * Quem implementa: infrastructure/gateway/EventRepositoryGateway (o "adapter").
 * Quem usa:        core/useCases/createEvent/CreateEventUseCaseImpl (createEvent)
 *                  core/useCases/allEvents/AllEventsUseCaseImpl     (allEvents)
 *
 * Cada nova operação que o core precisar (buscar por id, deletar...) vira um
 * novo método aqui, e o EventRepositoryGateway é obrigado a implementá-lo.
 *
 * Isso é a Inversão de Dependência (o "D" do SOLID): o use case depende desta
 * interface (abstração do core), e não do JPA/repositório (detalhe da infra).
 * Assim a seta de dependência continua apontando para dentro.
 */
public interface EventGateway {

    // Passo 4 do fluxo: o use case chama este método sem saber quem o implementa.
    public Event createEvent(Event event);

    // (listar) Passo 3: o AllEventsUseCaseImpl chama este método. Note que ele
    // devolve List<Event> (domínio), nunca List<EventEntity> (banco).
    public List<Event> allEvents();
}
