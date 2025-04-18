package com.codewithmosh.store;

import com.codewithmosh.store.entities.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByName(String name);//Spring will generate a Query like
    //SELECT * FROM products WHERE name  = ?
    List<Product> findByNameLike(String name);


    //Numbers
    List<Product> findByPrice(double price);
    //SELECT * FROM products WHERE price = ?
    List<Product> findByPriceGreaterThan(double price);
    List<Product> findByPriceBetween(double min, double max);


    /**
     * Find Products whose Prices are in a Given Range and Sort By Name
     * */
    List<Product> findByPriceBetweenOrderByName(BigDecimal min, BigDecimal max);

//SQL or JPQL query
    @Query(value = "select * from products p where p.price between :min and :max order by p.name", nativeQuery = true)
    List<Product> findProducts(@Param("min") BigDecimal min, @Param("max")  BigDecimal max);


    @Query("select p from Product p  join p.category where p.price between :min and :max")
    List<Product> jpqlFindProducts(@Param("min") BigDecimal min, @Param("max")  BigDecimal max);

    @Query("update Product p set p.price = :newPrice where p.category.id = :categoryId")
    @Modifying
    void updatePriceByCategory(BigDecimal newPrice, byte categoryId);
}
