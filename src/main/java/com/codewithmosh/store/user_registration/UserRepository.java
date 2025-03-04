package com.codewithmosh.store.user_registration;

import java.util.HashMap;
import java.util.Map;

public interface UserRepository {

     void save(User user);

     User findByEmail(String email);

}
