package com.ucentral.edu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ucentral.edu.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> getByUsername(String username);
}
