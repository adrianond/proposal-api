package br.com.example.proposal.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UsuarioNaoAutenticadoException extends RuntimeException {

    public UsuarioNaoAutenticadoException(String msg) {
        super(msg);
    }
}
