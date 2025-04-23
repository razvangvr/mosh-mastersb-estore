package com.codewithmosh.store.services;

import com.codewithmosh.store.entities.Profile;
import com.codewithmosh.store.repositories.ProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    //Could not initialize proxy [com.codewithmosh.store.entities.User#2] - no session
    @Transactional
    public List findAboveLoyaltyValue(int greaterThan) {
        var profilesByLoyaltyPoints = profileRepository.findProfilesByLoyaltyPointsGreaterThan(greaterThan);
        profilesByLoyaltyPoints.forEach(p -> {
            System.out.println("Profile Id:"+p.getId());
            System.out.println(p.getEmail());
        });
        return profilesByLoyaltyPoints;
    }
}
