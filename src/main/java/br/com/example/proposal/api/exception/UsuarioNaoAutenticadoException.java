package br.com.example.proposal.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UsuarioNaoAutenticadoException extends AuthenticationException {

    public UsuarioNaoAutenticadoException(String msg) {
        super(msg);
    }
}
