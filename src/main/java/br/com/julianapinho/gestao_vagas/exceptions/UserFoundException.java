package br.com.julianapinho.gestao_vagas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserFoundException extends RuntimeException {
    public UserFoundException(String message) {
        super(message);
    }
}
