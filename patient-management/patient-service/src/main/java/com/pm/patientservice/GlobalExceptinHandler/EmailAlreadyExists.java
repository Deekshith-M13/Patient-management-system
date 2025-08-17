package com.pm.patientservice.GlobalExceptinHandler;

public class EmailAlreadyExists extends RuntimeException {
    public EmailAlreadyExists(String message) {
        super(message);
    }
}
