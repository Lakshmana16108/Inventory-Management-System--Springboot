package com.inventory.exception;

/**
 * Thrown when a Product is not found by ID.
 */
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(int id) {
        super("Product not found with ID: " + id);
    }
}
