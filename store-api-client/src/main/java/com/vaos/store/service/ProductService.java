package com.vaos.store.service;

import com.vaos.store.entity.Product;
import com.vaos.store.error.ProductNotFoundException;

import java.util.List;

public interface ProductService {

    public Product saveProduct(Product product);

    public List<Product> getAllProducts();

    public Product getProductById(Long productId) throws ProductNotFoundException;

    public void deleteProductById(Long productId);

    public Product updateProduct(Long productId, Product product) throws ProductNotFoundException;

    public List<Product> getProductByName(String productName);
}
