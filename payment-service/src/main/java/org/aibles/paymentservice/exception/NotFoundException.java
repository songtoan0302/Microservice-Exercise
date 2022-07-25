package org.aibles.paymentservice.exception;

/**
 * @author toanns
 */
public class NotFoundException extends RuntimeException {

  private String message;

  public NotFoundException(String message) {
    super();
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
