package com.victorllunes.EventClean.infrastructure.presentation.exception;

import com.victorllunes.EventClean.infrastructure.presentation.exception.event.EventAlreadyExistsException;
import com.victorllunes.EventClean.infrastructure.dtos.error.RestErrorMessage;
import com.victorllunes.EventClean.infrastructure.presentation.exception.event.EventNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * CAMADA: infrastructure — tratamento GLOBAL de erros.
 *
 * @RestControllerAdvice vale para todos os controllers: quando uma exceção
 * escapa de um deles, o Spring procura aqui um @ExceptionHandler do mesmo tipo.
 * É o único lugar que transforma exceções do core em status HTTP.
 * <p>
 * Estende ResponseEntityExceptionHandler para que os erros do próprio Spring
 * (JSON inválido, rota inexistente) mantenham 400/404 em vez de cair no 500.
 */

@RestControllerAdvice
public class RestExceptionHandle extends ResponseEntityExceptionHandler {
    // Passo 5: identificador repetido -> 409 Conflict
    @ExceptionHandler(EventAlreadyExistsException.class)
    public ResponseEntity<RestErrorMessage> eventAlreadyExists(EventAlreadyExistsException exception) {
        RestErrorMessage response = new RestErrorMessage(exception.getMessage(), HttpStatus.CONFLICT);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(EventNotExistException.class)
    public ResponseEntity<RestErrorMessage> eventNotExistException(EventNotExistException exception) {
        RestErrorMessage response = new RestErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Qualquer erro inesperado -> 500 com mensagem genérica
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestErrorMessage> internalServerError(Exception exception) {
        RestErrorMessage response = new RestErrorMessage("Internal Server Error.", HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
