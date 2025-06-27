package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.mappers.UserMapper;
import com.codewithmosh.store.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    final UserMapper userMapper;


    @GetMapping()
    public List<UserDto> getAllUsers(
            @RequestParam(required = false, defaultValue = "") String sort) {

        String sortBy;
        if (Set.of("name", "email").contains(sort)) {
            sortBy = sort;
        } else {
            //throw new IllegalArgumentException("Invalid sort parameter: " + sort);
            sortBy = "name";
        }
        Sort sorter = Sort.by(Sort.Direction.DESC, sortBy);

        var usersList =  userRepository.findAll(sorter);

        return usersList.stream()
                .map(userMapper::toDto)
                .toList();

    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable long id) {
        var user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
//            UserDto userDto = new UserDto(user.getId(), user.getName(), user.getEmail());
            UserDto userDto = userMapper.toDto(user);
            return ResponseEntity.ok(userDto);
        }
    }
}
