package org.aibles.carservice.exception;

/**
 * @author toanns
 */
public class BadRequestException extends RuntimeException {

  private String message;

  public BadRequestException(String message) {
    super();
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
