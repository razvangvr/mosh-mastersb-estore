package com.codewithmosh.store.repositories;

import com.codewithmosh.store.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductCriteriaRepository {


    List<Product> findByCriteria(String productName, BigDecimal minPrice, BigDecimal maxPrice);



}
