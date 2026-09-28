package dev.sorokin.eventmanager.exceptionHandler;

public class RegistrationConflictException extends RuntimeException {
    public RegistrationConflictException(String message) {
        super(message);
    }
}
