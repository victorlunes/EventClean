package com.victorllunes.EventClean.infrastructure.presentation.exception.event;

public class EventAlreadyExistsException extends RuntimeException {

    /**
     * CAMADA: core — EXCEÇÃO DE DOMÍNIO.
     *
     * Lançada quando já existe um evento com o mesmo identificador.
     * Java puro (sem Spring): o core só diz O QUE deu errado.
     * Quem traduz para HTTP (409) é o infrastructure/presentation/RestExceptionHandle.
     */

    public EventAlreadyExistsException() {
        super("Event already exists with this identificator!");
    }

    public EventAlreadyExistsException(String message) {
        super(message);
    }
}
