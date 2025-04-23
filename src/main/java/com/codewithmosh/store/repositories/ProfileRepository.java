package com.codewithmosh.store.repositories;

import com.codewithmosh.store.dtos.UserSummary;
import com.codewithmosh.store.entities.Profile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
    @EntityGraph(attributePaths = "user")
    @Query("select p.id as id, p.user.email as email from Profile p where p.loyaltyPoints >= :loyaltyVal order by p.user.email")
    List<UserSummary> findProfilesByLoyaltyPointsGreaterThan(@Param("loyaltyVal") Integer loyaltyVal);
}
