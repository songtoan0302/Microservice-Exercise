package org.aibles.orderservice.exception;

/**
 * @author toanns
 */
public class ServerInternalException extends RuntimeException {

  private final String message;

  public ServerInternalException(String message) {
    super();
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
