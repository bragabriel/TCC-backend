package br.com.spotted.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TransporteNaoEncontradoException extends RuntimeException {
    public TransporteNaoEncontradoException(String message) {
        super(message);
    }
}
