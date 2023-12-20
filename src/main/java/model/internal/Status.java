package model.internal;

import java.util.Optional;

public class Status {
    private final StatusCode statusCode;
    private final String statusMessage;
    private final Optional<Account> respondAccount;

    public Status(StatusCode code, String message){
        statusCode = code;
        statusMessage = message;
        respondAccount = Optional.empty();

    }

    public Status(StatusCode code, String message, Optional<Account> respond){
        statusCode = code;
        statusMessage = message;
        respondAccount = respond;

    }
    public String getStatusMessage() {
        return statusMessage;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public Optional<Account> getRespondObject() {
        return respondAccount;
    }
}
