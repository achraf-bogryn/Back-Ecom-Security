package com.telusko.springecom.repository;

import com.telusko.springecom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
