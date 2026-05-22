package com.example.library.controller;

import com.example.library.entity.IsakhanKhangeldiUser;
import com.example.library.repository.IsakhanKhangeldiUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class IsakhanKhangeldiUserController {

    private final IsakhanKhangeldiUserRepository userRepository;

    @GetMapping
    public List<IsakhanKhangeldiUser> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public IsakhanKhangeldiUser createUser(@RequestBody IsakhanKhangeldiUser user) {
        return userRepository.save(user);
    }
}