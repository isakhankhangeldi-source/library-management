package com.example.library.security;

import com.example.library.entity.IsakhanKhangeldiSecurityUser;
import com.example.library.repository.IsakhanKhangeldiSecurityUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class IsakhanKhangeldiCustomUserDetailsService implements UserDetailsService {

    private final IsakhanKhangeldiSecurityUserRepository securityUserRepository;

    public IsakhanKhangeldiCustomUserDetailsService(IsakhanKhangeldiSecurityUserRepository securityUserRepository) {
        this.securityUserRepository = securityUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        IsakhanKhangeldiSecurityUser user = securityUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}