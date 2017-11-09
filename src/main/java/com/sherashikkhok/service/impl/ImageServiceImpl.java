package com.sherashikkhok.service.impl;

import com.sherashikkhok.model.Image;
import com.sherashikkhok.model.User;
import com.sherashikkhok.repository.ImageRepository;
import com.sherashikkhok.service.ImageService;
import com.sherashikkhok.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("imageService")
@Primary
public class ImageServiceImpl implements ImageService{
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public List<Image> getAllImages() {
		
		List<Image> myAllImageList = new ArrayList<Image>();
		
		List<Image> allImageList = imageRepository.findAll();
				
			if (allImageList != null) {
			
				for (Image image : allImageList) {				
					Image image2 = new Image();
					image2.setId(image.getId());
					image2.setUserId(image.getUserId());
					image2.setEmail(image.getEmail());							
					image2.setData(image.getData());
					image2.setMimetype(image.getMimetype());
					image2.setImageName(image.getImageName());	
					
					User user = userService.findUserByEmail(image.getEmail());
					image2.setUserName(user.getName());
													
						
					myAllImageList.add(image2);
					
				
				}
			}	
		
		return myAllImageList;
	}
	
	@Override
	public List<Image> findByUploaderId(long userId) {
		
		List<Image> myImageList = new ArrayList<Image>();
		
		List<Image> allImageList = imageRepository.findAll();
		
		if (allImageList != null) {
			
			for (Image image : allImageList) {
				
				if (userId == image.getUserId() ) {
					myImageList.add(image);
				}
			}
		}	
		return  myImageList;
	}

	/*@Override
	public void save(Image image, User user) {		
		image.setUserId(user.getId());
		image.setEmail(user.getEmail());
		imageRepository.save(image);
		
	}*/

}
