package com.inventory.exception;

/**
 * Thrown when an invalid (negative) quantity is provided - mirrors original "Invalid quantity!" logic.
 */
public class InvalidQuantityException extends RuntimeException {
    public InvalidQuantityException(int qty) {
        super("Invalid quantity: " + qty + ". Quantity must be >= 0.");
    }
}
