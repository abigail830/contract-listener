package com.github.abigail830.contractlistener.controller;

import com.github.abigail830.contractlistener.domain.ErrorDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionController extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomizedResponseEntityExceptionController.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<ErrorDetails> handleIllegalArgumentExceptionException(IllegalArgumentException ex,
                                                                                      WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(
                new Timestamp(System.currentTimeMillis()),
                ex.getMessage(),
                request.getDescription(false),
                HttpStatus.UNPROCESSABLE_ENTITY);

        logger.error("HTTP error would be replied for Exception {} ", errorDetails);
        return new ResponseEntity<>(errorDetails, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
