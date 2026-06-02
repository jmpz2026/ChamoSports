package com.chamo.chamosports.Exception;

import com.chamo.chamosports.constant.MessageConstant;
import org.springframework.http.HttpStatus;

public class CapacityExceededException extends ApiException{
    public CapacityExceededException(String message) {
        super(message, HttpStatus.BAD_REQUEST.value());
    }
}
