package com.takeo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.takeo.entities.User;
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	Optional<User> findByOtp(String otp);
	Optional<User> findByEmail(String email);


}
