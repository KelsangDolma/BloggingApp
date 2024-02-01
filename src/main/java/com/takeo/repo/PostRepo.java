package com.takeo.repo;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.takeo.entities.Post;
import com.takeo.entities.User;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
	List<Post> findByUser(User user);

	@Query("SELECT DISTINCT p FROM Post p JOIN p.categories c WHERE c.cname = :cname")
	Page<Post> findByCname(@Param("cname") String cname,Pageable pageable);

}
