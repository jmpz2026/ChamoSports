package com.chamo.chamosports.Exception;

import org.springframework.http.HttpStatus;

public class ResourceNotExistsException extends ApiException {
    public ResourceNotExistsException(String message) {
        super(message, HttpStatus.NOT_FOUND.value());
    }
}
