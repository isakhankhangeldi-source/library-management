package com.example.library.controller;

import com.example.library.entity.IsakhanKhangeldiSecurityUser;
import com.example.library.repository.IsakhanKhangeldiSecurityUserRepository;
import com.example.library.security.IsakhanKhangeldiJwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class IsakhanKhangeldiAuthController {

    private final IsakhanKhangeldiSecurityUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IsakhanKhangeldiJwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody IsakhanKhangeldiSecurityUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody IsakhanKhangeldiSecurityUser loginRequest) {
        IsakhanKhangeldiSecurityUser user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        String token = jwtUtil.generateToken(user.getUsername());
        return Map.of("token", token);
    }
}