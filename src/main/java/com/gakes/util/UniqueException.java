package com.gakes.util;

/**
 */
public class UniqueException extends Exception {

    public UniqueException(Throwable e) {
        super(e);
    }

    public UniqueException(String e) {
        super(e);
    }
}
