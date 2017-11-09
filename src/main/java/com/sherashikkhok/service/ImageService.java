package com.sherashikkhok.service;

import com.sherashikkhok.model.Image;

import java.util.List;

public interface ImageService {

	List<Image> getAllImages();
    
	List<Image> findByUploaderId(long userId);
    
	//void save(Image image, User user);
	
}
