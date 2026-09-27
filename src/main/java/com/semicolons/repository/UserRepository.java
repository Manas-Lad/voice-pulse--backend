package com.semicolons.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semicolons.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
