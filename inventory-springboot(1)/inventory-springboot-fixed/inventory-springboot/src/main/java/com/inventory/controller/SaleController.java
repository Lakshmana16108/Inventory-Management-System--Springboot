package com.inventory.controller;

import com.inventory.model.Sale;
import com.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API for Sale operations.
 * Maps to original menu: Record Sale (Case 4).
 */
@RestController
@RequestMapping("/api/sales")
@CrossOrigin(origins = "*")
public class SaleController {

    @Autowired
    private InventoryService inventoryService;

    // Case 4: Record Sale
    @PostMapping
    public ResponseEntity<Sale> recordSale(
            @RequestParam int saleId,
            @RequestParam int productId,
            @RequestParam int qty) {
        return ResponseEntity.ok(inventoryService.recordSale(saleId, productId, qty));
    }

    // View all sales
    @GetMapping
    public ResponseEntity<List<Sale>> getAllSales() {
        return ResponseEntity.ok(inventoryService.getAllSales());
    }
}
