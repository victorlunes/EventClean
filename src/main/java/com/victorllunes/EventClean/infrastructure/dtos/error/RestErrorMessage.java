package com.victorllunes.EventClean.infrastructure.dtos.error;

import org.springframework.http.HttpStatus;

public record RestErrorMessage(
        String message, HttpStatus status
) {}
