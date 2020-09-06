package com.ucentral.edu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ucentral.edu.model.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, Integer> {

	Optional<AppRole> findByName(String name);

}
