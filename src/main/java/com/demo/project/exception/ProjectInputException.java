package com.demo.project.exception;

/**
 * Exception class to handle exceptions related to the Input Payload.
 */
public class ProjectInputException extends RuntimeException {

    public ProjectInputException(String message) {
        super(message);
    }
}
