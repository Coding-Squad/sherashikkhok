package com.sherashikkhok.controller;

import com.sherashikkhok.model.Image;
import com.sherashikkhok.model.User;
import com.sherashikkhok.repository.ImageRepository;
import com.sherashikkhok.service.ImageService;
import com.sherashikkhok.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;



@Controller
public class ImageController {  
	
    @Autowired
    private ImageRepository repository;
    
    @Autowired
	private UserService userService;
    
    @Autowired
    private ImageService imageService;
    
    
    public ImageController(ImageRepository repository) {
        this.repository = repository;
    }
    
    @RequestMapping(value = "/allImagelist", method = RequestMethod.GET)
    public String listImages(Model model) {
    	
        Iterable<Image> allImagelists = imageService.getAllImages();
        
        
        
        model.addAttribute("allImageList", allImagelists);
        
        return "allImagelist";
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String myImageListImages(Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		Iterable<Image> myImages = imageService.findByUploaderId(user.getId());
        
        if (myImages != null) {
        	
        	 model.addAttribute("myImages", myImages);
		}
       
        return "list";
    }
    
    @RequestMapping(value="/list", method = RequestMethod.POST)
    public String addImage(@RequestParam("file") MultipartFile file, RedirectAttributes attrs) {
        try {     
        	
        	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    		User user = userService.findUserByEmail(auth.getName());
        	
            String mimetype = file.getContentType();
            byte[] data = file.getBytes();
          //  Image image = new Image(data, mimetype);
            
            Image image = new Image(user.getId(), user.getEmail(), data, mimetype,file.getOriginalFilename());
            repository.save(image);
            
            
        } catch (IOException ioe) {
            attrs.addAttribute("flash", ioe.getMessage());
        }
        return "redirect:/list";
    }
    
    
    
    
    
  //For Show image by image Id 
    @RequestMapping(value = "/imageid/{idOfImage}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> viewsTeacherImage(@PathVariable("idOfImage") long imageId) {
    	Image imageEntity = null;
    	byte[] avatarImage = null;    	
    	
    		imageEntity = repository.findOne(imageId);
            if (imageEntity != null) {           	
            
            	avatarImage = imageEntity.getData();
            	
			}else {
				try {
					File file = new ClassPathResource("static/images/google.png").getFile();
					byte[] defaultAvatarImage = Files.readAllBytes(file.toPath());
					avatarImage = defaultAvatarImage;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
    	
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<byte[]> (avatarImage, headers, HttpStatus.CREATED);        
    }
    
    
    
    
}