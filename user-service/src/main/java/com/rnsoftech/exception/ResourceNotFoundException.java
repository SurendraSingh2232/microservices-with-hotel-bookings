package com.rnsoftech.exception;/*
 * @Created 22/04/2024 - 22:15
 * @User ${"PRAVENDRA KUMAR"}
 */

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Resource not found on server !!");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
