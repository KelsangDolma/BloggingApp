package com.takeo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.takeo.entities.Post;

public interface PostRepo extends JpaRepository<Post, Long> {

}
