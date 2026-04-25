package com.inventory.service;

import com.inventory.exception.*;
import com.inventory.model.*;
import com.inventory.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * InventoryService - contains ALL original business logic from Inventory.java.
 * File I/O replaced by SQLite via JPA repositories.
 * All method logic is preserved exactly as the original.
 */
@Service
public class InventoryService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SaleRepository saleRepository;

    // ===================== SUPPLIER =====================

    /**
     * Original: inv.addSupplier(s)
     */
    public Supplier addSupplier(Supplier s) {
        return supplierRepository.save(s);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    // ===================== PRODUCT =====================

    /**
     * Original: inv.addProduct(p) + saveProductsToFile()
     */
    public Product addProduct(Product p) {
        return productRepository.save(p);
    }

    /**
     * Original: inv.findProduct(id)
     */
    public Product findProduct(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    /**
     * Original: inv.displayProducts()
     */
    public List<Product> displayProducts() {
        return productRepository.findAll();
    }

    // ===================== SALE =====================

    /**
     * Original: inv.recordSale(saleId, productId, qty)
     * Logic preserved: find product -> check stock -> reduce -> compute total -> save sale
     */
    @Transactional
    public Sale recordSale(int saleId, int productId, int qty) {
        Product p = findProduct(productId);  // throws ProductNotFoundException if not found

        if (!p.reduceStock(qty)) {  // same reduceStock() logic from Product.java
            throw new InsufficientStockException(productId, qty, p.getQuantity());
        }

        double total = qty * p.getPrice();
        Sale sale = new Sale(saleId, productId, qty, total);

        productRepository.save(p);      // update stock in DB (was saveProductsToFile())
        return saleRepository.save(sale);
    }

    /**
     * Original: inv.showLowStock() - products with quantity < 5
     */
    public List<Product> showLowStock() {
        return productRepository.findByQuantityLessThan(5);
    }

    /**
     * Original: inv.updateStock(productId, newQty)
     * Logic preserved: find -> validate -> compute diff -> addStock or reduceStock
     */
    @Transactional
    public Product updateStock(int productId, int newQty) {
        if (newQty < 0) {
            throw new InvalidQuantityException(newQty);  // original: "Invalid quantity!"
        }

        Product p = findProduct(productId);  // original: "Product not found!"
        int currentQty = p.getQuantity();
        int diff = newQty - currentQty;

        if (diff > 0) {
            p.addStock(diff);       // same addStock() from Product.java
        } else if (diff < 0) {
            p.reduceStock(-diff);   // same reduceStock() from Product.java
        }

        return productRepository.save(p);  // was saveProductsToFile()
    }

    // ===================== SALES LIST =====================

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }
}
