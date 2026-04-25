package com.inventory.model;

import jakarta.persistence.*;

/**
 * Sale entity - same logic as original Sale.java
 * Stored in SQLite via JPA.
 */
@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @Column(name = "sale_id")
    private int saleId;

    @Column(name = "product_id")
    private int productId;

    private int qty;
    private double total;

    public Sale() {}

    public Sale(int saleId, int productId, int qty, double total) {
        this.saleId = saleId;
        this.productId = productId;
        this.qty = qty;
        this.total = total;
    }

    // Same as original toFileString()
    public String toFileString() {
        return saleId + "," + productId + "," + qty + "," + total;
    }

    public int getSaleId() { return saleId; }
    public void setSaleId(int saleId) { this.saleId = saleId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}
