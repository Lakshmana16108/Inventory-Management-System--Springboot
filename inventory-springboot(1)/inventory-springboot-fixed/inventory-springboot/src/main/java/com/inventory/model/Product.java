package com.inventory.model;

import jakarta.persistence.*;

/**
 * Product entity - same logic as original Product.java
 * Persisted via JPA into SQLite (replaces products.txt file storage).
 */
@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "product_id")
    private int id;

    private String name;
    private double price;
    private int quantity;

    @Column(name = "supplier_id")
    private int supplierId;

    public Product() {}

    public Product(int id, String name, double price, int quantity, int supplierId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.supplierId = supplierId;
    }

    // Same logic as original addStock()
    public void addStock(int q) {
        quantity += q;
    }

    // Same logic as original reduceStock()
    public boolean reduceStock(int q) {
        if (quantity >= q) {
            quantity -= q;
            return true;
        }
        return false;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getSupplierId() { return supplierId; }
    public void setSupplierId(int supplierId) { this.supplierId = supplierId; }
}
