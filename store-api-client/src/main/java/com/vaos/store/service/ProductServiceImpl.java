package com.vaos.store.service;

import com.vaos.store.entity.Product;
import com.vaos.store.error.ProductNotFoundException;
import com.vaos.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long productId) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(productId);
        if (!product.isPresent()) {
            throw new ProductNotFoundException("Product Not Available");
        }
        return product.get();
    }

    @Override
    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Product updateProduct(Long productId, Product product) throws ProductNotFoundException {
        Product productDataBase = getProductById(productId);

        if (Objects.nonNull(product.getProductName()) &&
                !"".equalsIgnoreCase(product.getProductName())) {
            productDataBase.setProductName(product.getProductName());
        }

        if (Objects.nonNull(product.getProductPrice()) && (product.getProductPrice().doubleValue() > 0.01)) {
            productDataBase.setProductPrice(product.getProductPrice());
        }
        if (Objects.nonNull(product.getProductManufacturingDate())
                && !"".equalsIgnoreCase(product.getProductManufacturingDate())) {
            productDataBase.setProductManufacturingDate(product.getProductManufacturingDate());
        }
        if (product.getProductIsAvailable() == 0
                || product.getProductIsAvailable() == 1) {
            productDataBase.setProductIsAvailable(product.getProductIsAvailable());
        }
        if (product.getProductRating() >= 0
                && product.getProductRating() <= 5) {
            productDataBase.setProductRating(product.getProductRating());
        }
        if(product.getQuantity() >= 0) {
            productDataBase.setQuantity(product.getQuantity());
        }
        if(!"".equalsIgnoreCase(product.getCompany())){
            productDataBase.setCompany(product.getCompany());
        }
        return productRepository.save(productDataBase);
    }

    @Override
    public List<Product> getProductByName(String productName) {
        return productRepository.findProductsStartingWithIgnoreCase(productName);
    }
}
