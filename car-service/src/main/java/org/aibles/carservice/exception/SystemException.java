package org.aibles.carservice.exception;

import org.springframework.http.HttpStatus;

/**
 * @author ToanNS
 */

public class SystemException extends BaseException {
  public SystemException(String message, HttpStatus httpStatus){
    super(message,httpStatus);
  }
}
