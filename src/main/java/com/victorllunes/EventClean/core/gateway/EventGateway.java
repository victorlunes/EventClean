package com.victorllunes.EventClean.core.gateway;

import com.victorllunes.EventClean.core.entities.Event;

/**
 * CAMADA: core — GATEWAY (também chamado de "porta" / port).
 *
 * É o CONTRATO que o core define dizendo: "eu preciso que alguém consiga salvar um evento".
 * O core NÃO sabe como isso é feito (banco SQL, API externa, arquivo, memória...).
 *
 * Quem implementa: infrastructure/gateway/EventRepositoryGateway (o "adapter").
 * Quem usa:        core/useCases/createEvent/CreateEventUseCaseImpl.
 *
 * Isso é a Inversão de Dependência (o "D" do SOLID): o use case depende desta
 * interface (abstração do core), e não do JPA/repositório (detalhe da infra).
 * Assim a seta de dependência continua apontando para dentro.
 */
public interface EventGateway {

    // Passo 4 do fluxo: o use case chama este método sem saber quem o implementa.
    public Event createEvent(Event event);
}
