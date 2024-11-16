package com.example.payload;

import lombok.Data;

import java.util.Date;
@Data

public class ErrorDetails {
    private String message;
    private String request;
    private Date date;

    public ErrorDetails(String message, String request, Date date) {
        this.message = message;
        this.request = request;
        this.date = date;
    }
}
