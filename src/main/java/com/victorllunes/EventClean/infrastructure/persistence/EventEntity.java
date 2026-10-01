package com.victorllunes.EventClean.infrastructure.persistence;

import com.victorllunes.EventClean.core.enums.TypeEvent;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data // para usar getters e setters
@Table(name = "event")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
