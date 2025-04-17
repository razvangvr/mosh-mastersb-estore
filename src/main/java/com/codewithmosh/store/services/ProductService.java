package com.codewithmosh.store.services;

import com.codewithmosh.store.ProductRepository;
import com.codewithmosh.store.entities.Category;
import com.codewithmosh.store.entities.CategoryRepository;
import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
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

    @Transactional
    public void addAllProductsToUserWishList(long userId) {

        User user = userRepository.findById(userId).orElseThrow();

        Iterable<Product> allIterable = productRepository.findAll();

        List<Product> allProducts = Streamable.of(allIterable).toList();

        allProducts.forEach(user::addFavoriteProduct);

        userRepository.save(user);
    }

    public void deleteProduct(long productId) {
        productRepository.deleteById(productId);
    }
}
