package com.inventory.exception;

/**
 * Thrown when stock is not enough for a sale - mirrors original "Not enough stock!" logic.
 */
public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(int productId, int requested, int available) {
        super("Not enough stock! Product ID: " + productId +
              " | Requested: " + requested +
              " | Available: " + available);
    }
}
