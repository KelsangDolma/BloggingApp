package com.takeo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.takeo.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long> {

}
