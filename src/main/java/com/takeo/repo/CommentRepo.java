package com.takeo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.takeo.entities.Comment;
import com.takeo.entities.Post;
import com.takeo.entities.User;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
	List<Comment> findByUser(User user);
	List<Comment> findByPost(Post post);
}
