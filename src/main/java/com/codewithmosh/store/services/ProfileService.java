package com.codewithmosh.store.services;

import com.codewithmosh.store.entities.Profile;
import com.codewithmosh.store.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<Profile> findAboveLoyaltyValue(int greaterThan) {
        List<Profile> profilesByLoyaltyPoints = profileRepository.findProfilesByLoyaltyPointsGreaterThan(greaterThan);
        profilesByLoyaltyPoints.forEach(p -> {
            System.out.println(p.getId());
        });
        return profilesByLoyaltyPoints;
    }
}
