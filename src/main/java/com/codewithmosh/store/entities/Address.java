package com.codewithmosh.store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(nullable = false, name = "zipcode")
    private String zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    /**
     * 'Owning side', or the Owner of the Relationship
     * Which Entity is the Owner of the Relationship Here?
     * So in this case, the AddressEntity is the owner of the Relationship
     * */
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;
}
