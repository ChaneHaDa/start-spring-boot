package com.chan.ssb.exception;

import java.time.LocalDate;

public class ErrorDetails {
    private LocalDate timestamp;
    private String message;
    private String details;

    public ErrorDetails(LocalDate timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public LocalDate getTimestamp(){
        return this.timestamp;
    }

    public String getMessage(){
        return this.message;
    }

    public String getDetails(){
        return this.details;
    }

}
