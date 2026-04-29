package com.kai.football_api.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kai.football_api.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  boolean existsByEmail(String email);

  User findByEmail(String email);
}