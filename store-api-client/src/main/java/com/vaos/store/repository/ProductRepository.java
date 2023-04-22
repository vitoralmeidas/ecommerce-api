package com.vaos.store.repository;

import com.vaos.store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE LOWER(p.productName) LIKE LOWER(:productName)||'%'")
    List<Product> findProductsStartingWithIgnoreCase(String productName);
}
