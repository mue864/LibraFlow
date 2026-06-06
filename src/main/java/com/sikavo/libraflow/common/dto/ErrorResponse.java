package com.sikavo.libraflow.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
//    HTTP status code
    private int status;
//    Error message
    private String message;
//    Timestamp when the error occurred
    private LocalDateTime timestamp;
//    additional error details
    private Map<String, String> details;

    public ErrorResponse (int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse (int status, String message, Map<String, String> details) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.details = details;
    }
}
