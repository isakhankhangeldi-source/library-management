package com.example.library.repository;

import com.example.library.entity.IsakhanKhangeldiSecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IsakhanKhangeldiSecurityUserRepository extends JpaRepository<IsakhanKhangeldiSecurityUser, Long> {
    Optional<IsakhanKhangeldiSecurityUser> findByUsername(String username);
}