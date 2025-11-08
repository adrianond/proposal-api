package br.com.example.proposal.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StatusPropostaNotFoundException extends RuntimeException {

    public StatusPropostaNotFoundException(String msg) {
        super(msg);
    }
}
