package com.edu.ifpb.caprin.business.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RgAlreadyExistsException extends RuntimeException {
    public RgAlreadyExistsException(String message) {
        super(message);
    }
    
}
