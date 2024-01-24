package com.takeo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.takeo.entities.User;

public interface UserRepo extends JpaRepository<User, Long> {

}
