package com.codewithmosh.store.repositories;

import com.codewithmosh.store.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Notice the Join with Tags
     * from users u1_0 left join user_tags t1_0 on u1_0.id=t1_0.user_id left join tags t1_1 on t1_1.id=t1_0.tag_id where u1_0.email=?
     * */

    //Set attributePaths to the Name of the Related Entities you want to Load
    @EntityGraph(attributePaths = {"tags", "addresses"/*, "addresses.country"*/})
    /**
     * select u1_0.id,a1_0.user_id,a1_0.id,a1_0.city,a1_0.state,a1_0.street,a1_0.zipcode,u1_0.email,u1_0.name,u1_0.password,t1_0.user_id,t1_1.id,t1_1.name
     * from users u1_0 left join addresses a1_0 on u1_0.id=a1_0.user_id
     * */
    Optional<User> findByEmail(String email);
}
