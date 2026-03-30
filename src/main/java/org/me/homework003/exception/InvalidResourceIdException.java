package org.me.homework003.exception;

import java.util.Map;

public class InvalidResourceIdException extends RuntimeException {
    private final Map<String, String> errors;

    public InvalidResourceIdException(Map<String, String> errors) {
        super("Invalid resource id");
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
