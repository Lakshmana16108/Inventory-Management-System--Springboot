package com.inventory.controller;

import com.inventory.model.Product;
import com.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API for Product operations.
 * Maps to original menu: Add Product, Display Products, Show Low Stock, Update Stock.
 */
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private InventoryService inventoryService;

    // Case 2: Add Product
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(inventoryService.addProduct(product));
    }

    // Case 3: Display Products
    @GetMapping
    public ResponseEntity<List<Product>> displayProducts() {
        return ResponseEntity.ok(inventoryService.displayProducts());
    }

    // Case 5: Show Low Stock (quantity < 5)
    @GetMapping("/low-stock")
    public ResponseEntity<List<Product>> showLowStock() {
        return ResponseEntity.ok(inventoryService.showLowStock());
    }

    // Case 6: Update Stock
    @PutMapping("/{productId}/stock")
    public ResponseEntity<Product> updateStock(
            @PathVariable int productId,
            @RequestParam int newQty) {
        return ResponseEntity.ok(inventoryService.updateStock(productId, newQty));
    }

    // Get single product
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        return ResponseEntity.ok(inventoryService.findProduct(id));
    }
}
