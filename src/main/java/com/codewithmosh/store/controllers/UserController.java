package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;


    @GetMapping()
    public Iterable<UserDto> getAllUsers() {
        var usersList =  userRepository.findAll();

        return usersList.stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail()))
                .toList();

    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable long id) {
        var user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            UserDto userDto = new UserDto(user.getId(), user.getName(), user.getEmail());
            return ResponseEntity.ok(userDto);
        }
    }
}
