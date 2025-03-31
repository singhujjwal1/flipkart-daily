package com.flipkart.daily.exception;

/**
 * Custom exception class to handle item existence scenarios.
 */
public class ItemExistsException extends Exception {
    public ItemExistsException(String message) {
        super(message);
    }
}