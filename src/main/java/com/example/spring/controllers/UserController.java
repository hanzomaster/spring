package com.example.spring.controllers;

import com.example.spring.dtos.mappers.UserMapper;
import com.example.spring.dtos.user.UserCreateDto;
import com.example.spring.dtos.user.UserDto;
import com.example.spring.dtos.user.UserUpdateDto;
import com.example.spring.entities.User;
import com.example.spring.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/users")
public class UserController {
    UserMapper userMapper;
    UserRepository userRepository;

    @GetMapping
    public Iterable<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    @PostMapping
    public UserDto createUser(@RequestBody @Valid UserCreateDto userCreateDto) {
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userCreateDto)));
    }

    @PatchMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateDto updateDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDto(userRepository.save(userMapper.partialUpdate(updateDto, user)));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
