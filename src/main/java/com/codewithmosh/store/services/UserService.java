package com.codewithmosh.store.services;

import com.codewithmosh.store.StoreApplication;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final EntityManager entityManager;

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

}
