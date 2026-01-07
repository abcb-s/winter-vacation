package com.dgsw.winter.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ErrorResponse {

    private final LocalDateTime timestamp;

    private final String message;

    private final int status;
}
