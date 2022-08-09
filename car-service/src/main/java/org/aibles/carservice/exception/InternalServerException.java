package org.aibles.carservice.exception;

/**
 * @author toanns
 */
public class InternalServerException extends RuntimeException {

  private final String message;

  public InternalServerException(String message) {
    super();
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
