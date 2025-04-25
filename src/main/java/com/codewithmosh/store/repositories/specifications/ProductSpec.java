package com.codewithmosh.store.repositories.specifications;

import com.codewithmosh.store.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;


public class ProductSpec {


    //We add a bunch of Static Utility Methods for Creating Specification Objects

    public static Specification<Product> hasName(String productName) {
        return (root, query, cb) -> cb.like(root.get("name"), "%" + productName + "%");
    }

    public static Specification<Product> hasPriceAbove(BigDecimal price) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> hasPriceBelow(BigDecimal price) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), price);
    }


}
