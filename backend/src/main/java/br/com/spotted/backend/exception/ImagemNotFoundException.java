package br.com.spotted.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ImagemNotFoundException extends RuntimeException {
    public ImagemNotFoundException(String message) {
        super(message);
    }
}