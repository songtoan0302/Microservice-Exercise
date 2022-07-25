package org.aibles.carservice.exception.handler;

import java.time.Instant;
import org.aibles.carservice.exception.ServerInternalException;
import org.aibles.carservice.exception.response.ExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author toanns
 */
@RestControllerAdvice
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerInternalExceptionHandler {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(ServerInternalExceptionHandler.class);

  @ExceptionHandler(value = {ServerInternalException.class})
  public ExceptionResponse execute(ServerInternalException error) {
    LOGGER.error("Exception: errorCode: {}, Message: {}", HttpStatus.INTERNAL_SERVER_ERROR.value(),
        error.getMessage());
    ExceptionResponse exceptionResponse = new ExceptionResponse();
    exceptionResponse.setError("Server Internal Error!");
    exceptionResponse.setMessage(error.getMessage());
    exceptionResponse.setTimestamp(Instant.now());
    return exceptionResponse;
  }
}
