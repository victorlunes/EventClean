package com.victorllunes.EventClean.core.entities;

import com.victorllunes.EventClean.core.enums.TypeEvent;

import java.time.LocalDateTime;

/**
 * CAMADA: core (domínio) — ENTIDADE.
 *
 * Representa o que é um "evento" para o NEGÓCIO, sem nenhum detalhe técnico.
 * Repare que não há @Entity, @Table, @Column nem nada do Spring/JPA aqui:
 * se amanhã você trocar o PostgreSQL por MongoDB, ou REST por GraphQL,
 * esta classe não muda.
 *
 * É o objeto que circula DENTRO do core (use cases e gateways recebem/devolvem Event).
 * As camadas de fora convertem seus próprios formatos para Event usando os mappers:
 *   - EventDto    (API)   <-> Event  via EventMapper
 *   - EventEntity (banco) <-> Event  via EventEntityMapper
 *
 * Por ser um record, é imutável: os dados são definidos na criação e não mudam.
 * Regras de negócio da entidade (ex.: "startEvent deve ser antes de endEvent")
 * poderiam ser validadas no construtor compacto do record.
 */
public record Event(
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
