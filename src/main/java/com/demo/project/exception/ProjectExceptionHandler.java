package com.demo.project.exception;

import com.demo.project.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Exception handling Controller for different types of Exception
 */
@ControllerAdvice
@Slf4j
public class ProjectExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String RECORD_NOT_FOUND = "ER001";
    private static final String INVALID_INPUT_PAYLOAD = "ER002";
    private static final String DB_EXCEPTION = "ER003";
    private static final String SYSTEM_FAILURE = "ER004";

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseBody
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        log.error("ConstraintViolationException occurred {}", ex);
        ErrorResponse error = ErrorResponse.builder()
                .errorCode(INVALID_INPUT_PAYLOAD)
                .errorMessage("Required Field(s) are missing. Please check logs for more info")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({SQLGrammarException.class, SQLException.class, InvalidDataAccessResourceUsageException.class})
    @ResponseBody
    public ResponseEntity<Object> handleGenericException(SQLGrammarException ex, WebRequest request) {
        log.error("DB Exception {}", ex);
        ErrorResponse error = ErrorResponse.builder()
                .errorCode(DB_EXCEPTION)
                .errorMessage("DB Exception has occurred")
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        log.error("System Failure {}", ex);
        ErrorResponse error = ErrorResponse.builder()
                .errorCode(SYSTEM_FAILURE)
                .errorMessage(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(ProjectInputException.class)
    @ResponseBody
    public ResponseEntity<Object> handleProjectInputException(ProjectInputException ex, WebRequest request) {
        log.error("ProjectInputException occurred: {}", ex);
        ErrorResponse error = ErrorResponse.builder()
                .errorCode(INVALID_INPUT_PAYLOAD)
                .errorMessage(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ProjectNotFoundException.class})
    @ResponseBody
    protected ResponseEntity<Object> handleRecordNotFound(RuntimeException ex, WebRequest request) {
        log.error("Project Not found exception occurred : {}", ex);
        ErrorResponse error = ErrorResponse.builder()
                .errorCode(RECORD_NOT_FOUND)
                .errorMessage(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("MethodArgumentNotValidException occurred : {}", ex.getMessage());
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> String.format("[%1$s [%2$s] %3$s]",
                        fieldError.getField(),
                        fieldError.getRejectedValue(),
                        fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        ErrorResponse error = ErrorResponse.builder()
                .errorCode(INVALID_INPUT_PAYLOAD)
                .errorMessage(ex.getMessage())
                .errors(errorList)
                .build();
        return handleExceptionInternal(ex, error, headers, HttpStatus.BAD_REQUEST, request);
    }
}
