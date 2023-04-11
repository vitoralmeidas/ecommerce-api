package com.vaos.store.api.service;

import com.vaos.store.api.entity.Product;
import com.vaos.store.api.error.ProductNotFoundException;

import java.util.List;

public interface ProductService {

    public Product saveProduct(Product product);

    public List<Product> getAllProducts();

    public Product getProductById(Long productId) throws ProductNotFoundException;

    public void deleteProductById(Long productId);

    public Product updateProduct(Long productId, Product product) throws ProductNotFoundException;

    public Product getProductByName(String productName);
}
