package com.flipkart.daily.exception;

/**
 * Custom exception class to handle item not found scenarios.
 */
public class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String message) {
        super(message);
    }
}