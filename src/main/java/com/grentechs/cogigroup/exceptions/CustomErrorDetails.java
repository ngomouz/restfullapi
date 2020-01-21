package com.grentechs.cogigroup.exceptions;

import java.time.LocalDateTime;

public class CustomErrorDetails {

    private LocalDateTime timestamp;
    private String message;
    private String errordetails;

    public CustomErrorDetails() {
    }

    public CustomErrorDetails(LocalDateTime timestamp, String message, String errordetails) {
        this.timestamp = timestamp;
        this.message = message;
        this.errordetails = errordetails;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrordetails() {
        return errordetails;
    }

    public void setErrordetails(String errordetails) {
        this.errordetails = errordetails;
    }
}
