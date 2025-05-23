package com.codewithmosh.store.services;

import com.codewithmosh.store.dtos.ProductSummaryDTO;
import com.codewithmosh.store.entities.Category;
import com.codewithmosh.store.entities.CategoryRepository;
import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.repositories.ProductRepository;
import com.codewithmosh.store.repositories.UserRepository;
import com.codewithmosh.store.repositories.specifications.ProductSpec;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public void addProductToExistingCategory(long categoryId, Product product) {
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

    //Whereever we have an Update Transaction with Custom Queries
    // we should wrap our method call inside a Transaction
    @Transactional
    public void updatePriceInCategory(long categoryId, double newPrice) {
        productRepository.updatePriceByCategory(BigDecimal.valueOf(newPrice), (byte) categoryId);
    }


    public List<Product> findByCategoryId(int categoryId) {
        List<Product> products = productRepository.findByCategory_Id((byte) categoryId);
        products.forEach(System.out::println);
        return products;
    }

    /**
     * When we use Interface Projections a RunTimeProxy for the Interface is returned
     * org.springframework.data.jpa.repository.query.AbstractJpaQuery$TupleConverter$TupleBackedMap@66f73d3d
     * <pre>
     * Hibernate: select p1_0.id,p1_0.name from products p1_0 where p1_0.category_id=?
     * org.springframework.data.jpa.repository.query.AbstractJpaQuery$TupleConverter$TupleBackedMap@66f73d3d
     * </pre>
     * <p>
     * With Class Projections
     * <pre>
     *     Hibernate: select p1_0.id,p1_0.name from products p1_0 where p1_0.category_id=?
     *      com.codewithmosh.store.dtos.ProductSummaryDTO@3d8bf514
     * </pre>
     */
    public List<ProductSummaryDTO> findByCategory(Category category) {
        var products = productRepository.findByCategory(category);
        products.forEach(System.out::println);
        return products;
    }

    @Transactional
    public void findProductsByPriceRange() {
        productRepository.findByPriceRange(BigDecimal.valueOf(5), BigDecimal.valueOf(30)).forEach(System.out::println);
    }

    @Transactional
    public List<Product> fetchProducts(String productName) {
        var product = new Product();
        product.setName(productName);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var productExample = Example.of(product, matcher);


        List<Product> products = productRepository.findAll(productExample);
        products.forEach(System.out::println);

        return products;
    }

    public void fetchProductsByCriteria(String productName) {
        productRepository.findByCriteria(productName, BigDecimal.valueOf(1L), null)
                .forEach(System.out::println);
    }

    public void fetchProductsBySpecification(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        //FROM products
        Specification<Product> spec = Specification.where(null);

        if (name != null) {
            spec = spec.and(ProductSpec.hasName(name));
        }

        if (minPrice != null) {
            spec = spec.and(ProductSpec.hasPriceAbove(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpec.hasPriceBelow(maxPrice));
        }

        productRepository.findAll(spec).forEach(System.out::println);
    }

    public void fetchSortedProducts(BigDecimal minPrice, BigDecimal maxPrice) {
        //FROM products
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductSpec.hasPriceAbove(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpec.hasPriceBelow(maxPrice));
        }

        Sort sort = Sort.by("name").and(
                Sort.by("price").descending()
        );

        productRepository.findAll(spec, sort).forEach(System.out::println);
    }
}
