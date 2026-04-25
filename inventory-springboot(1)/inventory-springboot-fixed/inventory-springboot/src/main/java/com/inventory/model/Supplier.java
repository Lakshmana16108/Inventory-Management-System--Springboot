package com.inventory.model;

import jakarta.persistence.*;

/**
 * Supplier entity - same logic as original Supplier.java
 * Extends Person (inherits name and phone).
 */
@Entity
@Table(name = "suppliers")
public class Supplier extends Person {

    @Id
    @Column(name = "supplier_id")
    private int supplierId;

    public Supplier() {}

    public Supplier(int supplierId, String name, String phone) {
        super(name, phone);
        this.supplierId = supplierId;
    }

    public int getSupplierId() { return supplierId; }
    public void setSupplierId(int supplierId) { this.supplierId = supplierId; }
}
