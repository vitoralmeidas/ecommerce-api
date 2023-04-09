package com.vaos.store.api.controller;

import com.vaos.store.api.entity.Product;
import com.vaos.store.api.error.ProductNotFoundException;
import com.vaos.store.api.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/api/v1/product")
    public Product saveProduct( @RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping("/api/v1/product")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/api/v1/product/{id}")
    public Product getProductById(@PathVariable("id") Long productId) throws ProductNotFoundException {
        return productService.getProductById(productId);
    }

    @DeleteMapping("/api/v1/product/{id}")
    public String deleteProductById(@PathVariable("id") Long productId){
         productService.deleteProductById(productId);
         return "Product has been deleted!";
    }

    @PutMapping("/api/v1/product/{id}")
    public Product updateProduct(@PathVariable("id") Long productId, @RequestBody Product product) throws ProductNotFoundException {
        return productService.updateProduct(productId, product);
    }
}
