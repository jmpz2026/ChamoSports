package com.chamo.chamosports.Exception;

import com.chamo.chamosports.dto.ApiResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Este es el manejador global de las apis
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponseDTO<?>> handleApiException(ApiException exception) {

        ApiResponseDTO<Object> response = new ApiResponseDTO<>();
        response.setSuccess(false);
        response.setMessage(exception.getMessage());
        response.setData(null);

        return ResponseEntity
                .status(exception.getStatus())
                .body(response);
    }
}
