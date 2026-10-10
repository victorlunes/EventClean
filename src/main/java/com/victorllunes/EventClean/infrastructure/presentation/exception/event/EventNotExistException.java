package com.victorllunes.EventClean.infrastructure.presentation.exception.event;

public class EventNotExistException extends RuntimeException {

    public EventNotExistException() {
        super("Event not exists with this identificator!");
    }

    public EventNotExistException(String message) {
        super(message);
    }
}
