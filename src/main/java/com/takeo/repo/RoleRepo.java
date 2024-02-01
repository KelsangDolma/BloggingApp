package com.takeo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.takeo.entities.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
	Optional<Role> findByRole(String role);


}
