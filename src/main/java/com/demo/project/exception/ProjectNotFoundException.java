package com.demo.project.exception;

/**
 * Exception class  to handle Project NOT found exception.
 */
public class ProjectNotFoundException extends RuntimeException {

    public ProjectNotFoundException(String message) {
        super(message);
    }
}
