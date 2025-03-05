package com.codewithmosh.store.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "email")
    private String email;

    @Column(nullable = false, name = "password")
    private String password;


    /**
     * We have to tell Hibernate who's the Owner of the Relationship
     *
     * */
    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<Address> addresses = new ArrayList<>();

    /**
     * Let's add a Helper Method Here
     * */
    public void addAddress(Address address) {
        addresses.add(address);
        address.setUser(this);//Always set the Owning side
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setUser(null);
    }
}
