package com.codewithmosh.store.services;

import com.codewithmosh.store.ProductRepository;
import com.codewithmosh.store.entities.Category;
import com.codewithmosh.store.entities.CategoryRepository;
import com.codewithmosh.store.entities.Product;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Product saveProduct(Product product) {

        return productRepository.save(product);
    }

    @Transactional
    public void addProductToExistingCategory(long categoryId,Product product) {
        //Retrieve existing Category
        Category category = categoryRepository.findById((byte) categoryId).orElseThrow();

        //Create another Product and assign it to Fetched Category
        product.addCategory(category);

        productRepository.save(product);
    }
}
