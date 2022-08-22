package org.aibles.carservice.exception.handler;

import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.aibles.carservice.exception.SystemException;
import org.aibles.carservice.exception.response.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ToanNS
 */
@Slf4j
@RestControllerAdvice
public class SystemExceptionHandler {
  @ExceptionHandler(value = {SystemException.class})
  public ResponseEntity<ExceptionResponse> execute(SystemException error) {
    log.error(
        "Exception: errorCode: {}, Message: {}", error.getHttpStatus().value(), error.getMessage());
    ExceptionResponse exceptionResponse = new ExceptionResponse();
    exceptionResponse.setError("Exception!");
    exceptionResponse.setMessage(error.getMessage());
    exceptionResponse.setTimestamp(Instant.now());
    return new ResponseEntity<>(exceptionResponse, error.getHttpStatus());
  }
}
