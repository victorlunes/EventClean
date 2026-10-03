package com.victorllunes.EventClean.core.enums;

/**
 * CAMADA: core (domínio).
 *
 * Tipos de evento que o negócio reconhece. Fica no core porque é um conceito
 * do domínio, e por isso pode ser usado por qualquer camada (DTO, entidade JPA...),
 * já que todas podem depender do core.
 */
public enum TypeEvent {
    SHOW,
    PALESTRA,
    WORKSHOP,
    ESPORTIVO
}
