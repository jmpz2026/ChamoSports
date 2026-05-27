package com.chamo.chamosports.Exception;

import org.springframework.http.HttpStatus;

public class ResourceExistsException extends ApiException {
    public ResourceExistsException(String message) {
        super(message, HttpStatus.CONFLICT.value());
    }
}
