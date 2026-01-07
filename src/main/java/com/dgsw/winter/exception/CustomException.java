package com.dgsw.winter.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

//@AllArgsConstructor
@Getter
public abstract class CustomException extends RuntimeException {

    private final HttpStatus status;

    public CustomException(String message,HttpStatus status) {
        super(message);
        this.status = status;
    }
}
