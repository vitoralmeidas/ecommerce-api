package com.vaos.store.api.repository;

import com.vaos.store.api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {

    Product findByProductNameIgnoreCase(String productName);
}
