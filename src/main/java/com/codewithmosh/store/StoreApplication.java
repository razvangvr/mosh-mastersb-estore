package com.codewithmosh.store;

import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.entities.Profile;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.services.ProductService;
import com.codewithmosh.store.services.ProfileService;
import com.codewithmosh.store.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class StoreApplication {

    public static final String EMAIL = "me@me.com";

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(StoreApplication.class, args);
        var userService = applicationContext.getBean(UserService.class);
        var productService = applicationContext.getBean(ProductService.class);
        var profileService = applicationContext.getBean(ProfileService.class);

//        userService.showRelatedEntities();
//        userService.fetchAddresses();
//        userService.persistRelated();
//        userService.deleteRelated(2L);
//        userService.deleteRelatedAddress(3L);

//        userService.saveUserAndTags();

//        Product product = basicProduct(10.99D);
//        Category electronics = new Category();
//        electronics.setName("Electronics");
//        product.addCategory(electronics);
//        productService.saveProduct(product);
//        productService.addProductToExistingCategory(1L, basicProduct(13.99D));
//        productService.addAllProductsToUserWishList(3L);
//        productService.deleteProduct(6L);
//        productService.updatePriceInCategory(1l, 100.99);

//        productService.findByCategoryId(1);
//        productService.findByCategory(new Category(2));

//        userService.fetchUser("me1@me.com");
//        userService.fetchUsers();

//        productService.findProductsByPriceRange();

//        List<Profile> aboveLoyaltyValue = profileService.findAboveLoyaltyValue(7);
//        System.out.println("aboveLoyaltyValue 7 => "+aboveLoyaltyValue.size());


//        productService.fetchProducts("product");

        /* select p1_0.id from products p1_0 where p1_0.price>=? */
//        productService.fetchProductsByCriteria(null);
        /* select p1_0.id from products p1_0 where p1_0.name=? and p1_0.price>=?*/
//        productService.fetchProductsByCriteria("product");

//        productService.fetchProductsBySpecification("prod", BigDecimal.valueOf(11), null);

        /* select p1_0.id,p1_0.category_id,p1_0.name,p1_0.price from products p1_0 where p1_0.price>=? order by p1_0.name,p1_0.price desc */
        productService.fetchSortedProducts( BigDecimal.valueOf(11), null);


    }

    private static Product basicProduct(double price) {
        Product product = Product.builder()
                .name("Product1")
                .price(BigDecimal.valueOf(price))
                .build();
        return product;
    }

    public static User userWithProfileAndTags() {
        var user = basicUser();

        user.addTag("Top Spender");

        var profile = Profile.builder()
                .bio("my Beautiful Life")
                .build();

        user.setProfile(profile);
        profile.setUser(user);
        System.out.println(user);
        return user;
    }

    public static User basicUser() {
        var user = User.builder()
                .name("John")
                .email(EMAIL)
                .password("password")
                .build();
        return user;
    }

}
