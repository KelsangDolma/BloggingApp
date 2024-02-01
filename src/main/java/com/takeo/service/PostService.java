package com.takeo.service;

import com.takeo.dto.PostDto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {
	String create(PostDto post, Long uid, String cname);

	List<PostDto> read();

	List<PostDto> read(Long uid);

	Page<PostDto> readCatPost(String catid, Pageable pageable);

	public PostDto readPost(Long pid);

	String update(PostDto post, Long uid, Long pid);

	String delete(Long pid);

	String updatePostPicture(MultipartFile file, Long pid);

	public byte[] viewPostPicture(Long pid);

}
