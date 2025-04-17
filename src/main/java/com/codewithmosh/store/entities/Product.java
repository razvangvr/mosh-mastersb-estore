package com.codewithmosh.store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
     * Category Field in the Product Entity.
     * Product is the Owner of the Relationship
     * */
    @JoinColumn(name = "category_id")
    private Category category;

    public void addCategory(Category categ) {
        this.category = categ;//set the Owning side
        categ.getProducts().add(this);
    }


// In reality, once we have a Product
// We don't need to show all the users who have that Product in their wishlist,
// So there is no need to Navigate from a Product to a List of Users
/*    @ManyToMany(mappedBy = "wishlist")
    @ToString.Exclude
    private Set<User> users = new HashSet<>(); */
}
