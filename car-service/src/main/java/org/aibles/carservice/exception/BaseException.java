package org.aibles.carservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author ToanNS
 */
@Data
public class BaseException extends RuntimeException{
  private String message;
  private HttpStatus httpStatus;

  public BaseException(String message, HttpStatus httpStatus) {
    this.httpStatus=httpStatus;
    this.message=message;
  }
}
