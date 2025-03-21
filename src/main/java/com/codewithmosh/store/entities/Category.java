package com.codewithmosh.store.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Product> products = new HashSet<>();
}
