package org.aibles.paymentservice.exception;

/**
 * @author toanns
 */
public class ServerInternalException extends RuntimeException{

    private String message;

    public ServerInternalException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
