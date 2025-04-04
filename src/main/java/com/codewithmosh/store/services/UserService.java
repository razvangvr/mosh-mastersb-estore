package com.codewithmosh.store.services;

import com.codewithmosh.store.StoreApplication;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final EntityManager entityManager;

    public void showEntityStates(){
        var user = StoreApplication.basicUser();
        inspectEntityState(user);
        //Save User Transaction, while the Transaction is Active we're gonna have a PersitenceContext
        userRepository.save(user);
        inspectEntityState(user);
    }

    private void inspectEntityState(User user){
        if(entityManager.contains(user)){
            System.out.println("PERSISTENT Entity State");
        }else
            System.out.println("TRANSIENT/DETACHED  State");
    }

}
