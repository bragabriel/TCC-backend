package br.com.spotted.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ArtefatoNaoEncontradoException extends RuntimeException {
    public ArtefatoNaoEncontradoException(String message) {
        super(message);
    }
}
