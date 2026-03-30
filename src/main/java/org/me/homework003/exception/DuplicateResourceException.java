package org.me.homework003.exception;

import lombok.Getter;

@Getter
public class DuplicateResourceException extends RuntimeException {
    private final String type;

    public DuplicateResourceException(String message, String type) {
        super(message);
        this.type = type;
    }
}
