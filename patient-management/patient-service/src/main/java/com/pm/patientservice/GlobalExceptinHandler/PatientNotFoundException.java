package com.pm.patientservice.GlobalExceptinHandler;

public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(String message) {
        super(message);
    }
}
