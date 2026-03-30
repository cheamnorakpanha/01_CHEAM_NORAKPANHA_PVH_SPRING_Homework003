package org.me.homework003.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
public class InvalidPaginationException extends RuntimeException {
    private final Map<String, String> errors;

    public InvalidPaginationException(Map<String, String> errors) {
        super("Invalid pagination parameters");
        this.errors = errors;
    }
}
