package com.codewithmosh.store;

import com.codewithmosh.store.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
