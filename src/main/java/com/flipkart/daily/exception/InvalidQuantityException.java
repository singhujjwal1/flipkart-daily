package com.flipkart.daily.exception;

/**
 * Custom exception class to handle invalid quantity scenarios.
 */
public class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String message) {
        super(message);
    }
}