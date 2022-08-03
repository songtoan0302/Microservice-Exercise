package org.aibles.orderservice.exception.handler;

import org.aibles.orderservice.exception.NotFoundException;
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
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(NotFoundException.class);

  @ExceptionHandler(value = {NotFoundException.class})
  public ExceptionResponse execute(NotFoundException error) {
    LOGGER.error("Exception: errorCode: {}, Message: {}", HttpStatus.NOT_FOUND.value(),
        error.getMessage());
    ExceptionResponse exceptionResponse = new ExceptionResponse();
    exceptionResponse.setError("Not Found!");
    exceptionResponse.setMessage(error.getMessage());
    exceptionResponse.setTimestamp(Instant.now());
    return exceptionResponse;
  }
}
