package com.codewithmosh.store.services;

import com.codewithmosh.store.ProductRepository;
import com.codewithmosh.store.entities.Product;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product saveProduct(Product product) {

        return productRepository.save(product);
    }
}
