package com.codewithmosh.store.services;

import com.codewithmosh.store.StoreApplication;
import com.codewithmosh.store.entities.Address;
import com.codewithmosh.store.entities.Profile;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.repositories.AddressRepository;
import com.codewithmosh.store.repositories.ProfileRepository;
import com.codewithmosh.store.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    private final EntityManager entityManager;
    private final AddressRepository addressRepository;

    @Transactional
    public void showEntityStates() {
        var user = StoreApplication.basicUser();
        inspectEntityState(user);
        //Save User Transaction, while the Transaction is Active we're gonna have a PersitenceContext
        userRepository.save(user);
        //The Transaction is Active ONLY while `repository.save` Method is Executed
        //So After the Method is executed,the Transaction Completes, PersistenceContext is removed
        //And Our Entity Becomes Detached
        inspectEntityState(user);
        /**
         * TRANSIENT/DETACHED  State
         * Hibernate: insert into users (email,name,password) values (?,?,?)
         * PERSISTENT Entity State
         * */
    }

    private void inspectEntityState(User user) {
        if (entityManager.contains(user)) {
            System.out.println("PERSISTENT Entity State");
        } else
            System.out.println("TRANSIENT/DETACHED  State");
    }

    @Transactional
    public void showRelatedEntities() {
        Profile profile = profileRepository.findById(2L).orElseThrow();//Transaction Terminates when `findById` Returns

        //Caused by: org.hibernate.LazyInitializationException
        System.out.println(profile.getUser().getEmail());
    }

    public void fetchAddresses() {
        Address address = addressRepository.findById(1L).orElseThrow();
    }

    /**
     * org.hibernate.TransientObjectException: persistent instance references an unsaved transient instance of 'com.codewithmosh.store.entities.Profile'
     * (save the transient instance before flushing)
     */
    public void saveUserAndTags() {

        User user = StoreApplication.userWithProfileAndTags();

        Address myAddress = Address.builder()
                .state("Street")
                .city("city")
                .street("street")
                .state("state")
                .zipCode("zipCode")
                .build();

        user.addAddress(myAddress);

        userRepository.save(user);
    }

    public void persistRelated() {
        User userWithAddress = StoreApplication.basicUser();

        Address myAddress = Address.builder()
                .state("Street")
                .city("city")
                .street("street")
                .state("state")
                .zipCode("zipCode")
                .build();

        userWithAddress.addAddress(myAddress);

        /**
         * Hibernate: insert into users (email,name,password) values (?,?,?)
         * ONLY, USER IS PERSISTED
         */
        userRepository.save(userWithAddress);

    }

    /**
     * Delete Parent Entity
     */
    public void deleteRelated(long id) {
        userRepository.deleteById(id);
    }

    /**
     * But what about deleting a User's Address ?
     */
    @Transactional
    //We apply the @Transactional annotation to keep the
    //Transaction and the Persistence Context throughout this method
    public void deleteRelatedAddress(long userId) {
        var user = userRepository.findById(userId).orElseThrow();

        Address address = user.getAddresses().stream().findFirst().orElseThrow();
        user.removeAddress(address);
        /*
        When we remove an Address from the User:
        address.setUser(null);
        so we mark it as Orphan, So we need to update the JPA relation to
        orphanRemoval = true// We tell Hibernate the Remove Orphan Entities
        * */

        userRepository.save(user);
    }

}
