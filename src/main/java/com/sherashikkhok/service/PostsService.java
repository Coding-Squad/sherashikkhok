package com.sherashikkhok.service;

import com.sherashikkhok.model.Posts;
import com.sherashikkhok.model.User;

import java.util.List;

public interface PostsService {
	
	List<Posts> getAllPosts();
	
    List<Posts> findLatest5();
    
    void insert(Posts post);
    
    List<Posts> findByCreatorId(long userId);
    
	void create(Posts post, User user);
        
    Posts edit(Posts post);
    
    boolean deletePost(Long postId);

}
