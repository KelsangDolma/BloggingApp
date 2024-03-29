package com.takeo.service.impl;

import com.takeo.entities.Post;
import com.takeo.dto.PostDto;
import com.takeo.entities.Category;
import com.takeo.entities.User;
import com.takeo.exception.ResourceNotFoundException;
import com.takeo.repo.CategoryRepo;
import com.takeo.repo.PostRepo;
import com.takeo.repo.UserRepo;
import com.takeo.service.PostService;
import com.takeo.utils.ImageFile;
import com.takeo.utils.ImageNameGenerator;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

	private final String DB_PATH = "/Users/kerusangu/Desktop/db/";

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ImageFile imageFile;

	@Autowired
	private PostRepo postDaoImpl;

	@Autowired
	private UserRepo userDaoImpl;

	@Autowired
	private CategoryRepo categoryDaoImpl;

	@Autowired
	private ImageNameGenerator fileNameGenerator;

	@Override
	public String create(PostDto postDto, Long uid, String cname) {
		User user = userDaoImpl.findById(uid)
				.orElseThrow(() -> new ResourceNotFoundException("User with id " + uid + " not found"));
		Optional<Category> cat = categoryDaoImpl.findByCname(cname);
		Category category;
		if (cat.isEmpty()) {
			category = new Category();
			category.setCname(cname);
		} else {
			category = cat.get();
		}
		String message = "Post not created";
		Post post = new Post();
		BeanUtils.copyProperties(postDto, post);

		// Setting the user for the post
		post.setUser(user);

		// Adding the category to the post
		post.getCategories().add(category);

		category.getCategoriesPosts().add(post);

		Post savedPost = postDaoImpl.save(post);

		if (savedPost != null) {
			message = "Post created, in category: " + category.getCname();
		} else {
			message = "Failed to create post.";
		}
		return message;
	}

	@Override
	public List<PostDto> read() {
		List<Post> posts = postDaoImpl.findAll();
		List<PostDto> postsDto = new ArrayList<>();
		if (posts.size() != 0) {
			for (Post p : posts) {
				PostDto postDto = new PostDto();
				BeanUtils.copyProperties(p, postDto);
				postsDto.add(postDto);
			}
			return postsDto;
		}
		throw new ResourceNotFoundException("No posts found");
	}

	@Override
	public List<PostDto> read(Long uid) {
		User user = new User();
		user.setUid(uid);
		List<Post> posts = postDaoImpl.findByUser(user);

		if (posts.size() != 0) {
			List<PostDto> postsDto = new ArrayList<>();
			for (Post p : posts) {
				PostDto postDto = new PostDto();
				BeanUtils.copyProperties(p, postDto);
				postsDto.add(postDto);
			}
			return postsDto;
		}
		throw new ResourceNotFoundException("User with uid " + uid + " has no posts");
	}
	
	@Override
	public Page<PostDto> readCatPost(String category, Pageable pageable) {
		categoryDaoImpl.findByCname(category)
				.orElseThrow(() -> new ResourceNotFoundException("Category " + category + " not found"));
		Page<Post> posts = postDaoImpl.findByCname(category, pageable);
        return posts.map(post -> {
            PostDto postDto = new PostDto();
            BeanUtils.copyProperties(post, postDto);
            return postDto;
        });
	}
	
	
	
	
	@Override
	public PostDto readPost(Long pid) {
		Post post = postDaoImpl.findById(pid)
				.orElseThrow(() -> new ResourceNotFoundException("Post with " + pid + " not found"));
		PostDto postDto = new PostDto();
		BeanUtils.copyProperties(post, postDto);
		return postDto;
	}

	@Override
	public String update(PostDto postDto, Long uid, Long pid) {
		// TODO Auto-generated method stub
		String message = "Not updated";
		Post existingPost = postDaoImpl.findById(pid)
				.orElseThrow(() -> new ResourceNotFoundException("Post with " + pid + " not found"));

		if (existingPost.getUser().getUid() == uid) {
			postDto.setPid(existingPost.getPid());
			modelMapper.map(postDto, existingPost);

			Post savePost = postDaoImpl.save(existingPost);
			if (savePost != null) {
				message = "Post updated";
			}
		}
		return message;
	}

	
	@Override
	public String delete(Long pid) {
		String message = "Post not deleted";
		postDaoImpl.findById(pid).orElseThrow(() -> new ResourceNotFoundException("Post with " + pid + " not found"));

		postDaoImpl.deleteById(pid);
		message = "Post deleted";
		return message;
	}

	@Override
	public byte[] viewPostPicture(Long pid) {
		Post post = postDaoImpl.findById(pid)
				.orElseThrow(() -> new ResourceNotFoundException("Post with " + pid + " not found"));

		String filePath = post.getImage();
		try {
			return Files.readAllBytes(Paths.get(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		throw new ResourceNotFoundException("No picture found for post id: " + pid);
	}

	@Override
	public String updatePostPicture(@RequestParam("file") MultipartFile file, Long pid) {
		Post existingPost = postDaoImpl.findById(pid)
				.orElseThrow(() -> new ResourceNotFoundException("Post with " + pid + " not found"));

		String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmSSS"));
		String fileName = timeStamp + fileNameGenerator.getFileExtensionName(file.getOriginalFilename());
		String filePath = DB_PATH + fileName;

		if (imageFile.isImageFile(file)) {
			// Create the folder if it doesnt exist
			File folder = new File(filePath);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			try {
				file.transferTo(new File(filePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
			existingPost.setImage(filePath);
			Post updatePhoto = postDaoImpl.save(existingPost);
			if (updatePhoto != null) {
				return "Updated";
			}
		}
		return ("Only image files are allowed.");
	}
	
	
	

}