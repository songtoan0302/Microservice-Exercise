package org.aibles.carservice.exception.handler;

import org.aibles.carservice.exception.BadRequestException;
import org.aibles.carservice.exception.response.ExceptionResponse;
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
    LOGGER.info(error.getMessage());
    ExceptionResponse exceptionResponse = new ExceptionResponse();
    exceptionResponse.setError("Bad request");
    exceptionResponse.setMessage(error.getMessage());
    exceptionResponse.setTimestamp(Instant.now());
    return exceptionResponse;
  }
}
