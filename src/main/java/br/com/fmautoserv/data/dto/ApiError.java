package br.com.fmautoserv.data.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class ApiError {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private List<String> details;

    public ApiError(int status, String error, String message, List<String> details) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.details = details;
    }
}
