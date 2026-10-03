package com.victorllunes.EventClean.infrastructure.persistence;

import com.victorllunes.EventClean.core.enums.TypeEvent;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * CAMADA: infrastructure — ENTIDADE DE PERSISTÊNCIA (JPA).
 *
 * Representa a TABELA "event" do banco. Não confunda com core/entities/Event:
 *   - Event       = conceito de negócio (Java puro, imutável)
 *   - EventEntity = linha da tabela (cheia de anotações JPA, mutável)
 *
 * A estrutura da tabela é criada pela migration do Flyway em
 * resources/db/migration/V1__create_table_event.sql.
 * Conversão com o domínio: infrastructure/mapper/event/EventEntityMapper.
 */
@Entity
@NoArgsConstructor // o JPA exige um construtor vazio
@AllArgsConstructor // usado pelo EventEntityMapper.toEventEntity
@Data // para usar getters e setters
@Table(name = "event")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id gerado pelo banco
    private Long id;

    private String name;
    private String location;
    private String organizer;
    private String description;
    private String identificator;
    private LocalDateTime endEvent;
    private LocalDateTime startEvent;
    private int capacity;
    // grava o nome do enum ("PALESTRA") em vez da posição (1), que é o padrão do JPA
    // e violaria o CHECK da coluna type_event
    @Enumerated(EnumType.STRING)
    private TypeEvent typeEvent;
}
