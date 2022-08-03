package org.aibles.orderservice.exception.handler;

import org.aibles.orderservice.exception.BadRequestException;
import org.aibles.orderservice.exception.response.ExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

/**
 * @author toanns
 */
@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(BadRequestExceptionHandler.class);

  @ExceptionHandler(value = {BadRequestException.class})
  public ExceptionResponse execute(BadRequestException error) {
    LOGGER.error("Exception: errorCode: {}, Message: {}", HttpStatus.BAD_REQUEST.value(),
        error.getMessage());
    ExceptionResponse exceptionResponse = new ExceptionResponse();
    exceptionResponse.setError("Bad Request!");
    exceptionResponse.setMessage(error.getMessage());
    exceptionResponse.setTimestamp(Instant.now());
    return exceptionResponse;
  }
}
