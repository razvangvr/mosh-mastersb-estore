package com.codewithmosh.store.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @ManyToOne
    /**
     * Category Field in the Product Entity
     * is the Owner of the Relationship
     * */
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * This product is wished be following users
     * */
    @ManyToMany(mappedBy = "wishlist")
    @ToString.Exclude
    private Set<User> users = new HashSet<>();
}
