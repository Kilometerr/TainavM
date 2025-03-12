package com.server.tainav.statistics.exception;

import lombok.Getter;

@Getter
public class HealthCheckException extends Exception{
    public HealthCheckException(String message) {
        super(message);
    }
    public HealthCheckException(String message, Throwable cause) {
        super(message, cause);
    }
}
