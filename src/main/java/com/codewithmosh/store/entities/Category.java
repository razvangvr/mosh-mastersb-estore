package com.codewithmosh.store.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "categories")
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "category"
            /*, cascade = CascadeType.PERSIST*/ /*! Be careful, not really necessary*/
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Product> products = new HashSet<>();

    public Category(int categoryId) {
        this.id = (byte) categoryId;
    }
}
