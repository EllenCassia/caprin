package com.edu.ifpb.caprin.business.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TokenException extends RuntimeException {
    public TokenException(String message) {
        super(message);
    }
}
