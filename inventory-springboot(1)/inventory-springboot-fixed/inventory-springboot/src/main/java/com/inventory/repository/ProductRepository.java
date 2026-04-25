package com.inventory.repository;

import com.inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Same as original showLowStock() logic: find products with quantity < 5
    List<Product> findByQuantityLessThan(int threshold);
}
