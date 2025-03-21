package com.codewithmosh.store.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    @ManyToMany
    @JoinTable(
            name = "user_tags",
            joinColumns = @JoinColumn( name = "user_id"),
            inverseJoinColumns = @JoinColumn( name = "tag_id")
    )
    @Builder.Default
    private Set<Tag> tags = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "wishlist",
            joinColumns = @JoinColumn( name = "user_id"),
            inverseJoinColumns = @JoinColumn( name = "product_id")
    )
    private Set<Product> wishList = new HashSet<>();


    //Again, we have to tell Hibernate about
    //the owner of the Relationship
    @OneToOne(mappedBy = "user")
    private Profile profile;

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

    public void addTag(String tag) {
        var aTag = new Tag("tag1");
        tags.add(aTag);//User knows about the Tag
        aTag.getUsers().add(this);//Now we should tell Tag about the User
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
        tag.getUsers().remove(this);
    }
}
