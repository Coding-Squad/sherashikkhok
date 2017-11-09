package com.sherashikkhok.controller;

import com.sherashikkhok.model.Teacher;
import com.sherashikkhok.service.TeacherService;
import com.sherashikkhok.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.sql.rowset.serial.SerialException;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;



@Controller
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private VoteService voteService;

    /*@RequestMapping(value="/admin/dashboard", method = RequestMethod.GET)
    public ModelAndView getAllTeacher() {
        ModelAndView modelAndView = new ModelAndView();

        List<Teacher> allTeachersList = teacherService.findAll();

        if (allTeachersList!=null) {
            for (Teacher teacher : allTeachersList) {
                int totalVoteOnTeacher = voteService.getAllVoteByTeacherId(teacher.getName(), true);
                teacher.setTotal(totalVoteOnTeacher);
            }

            modelAndView.addObject(allTeachersList);

            modelAndView.setViewName("admin/dashboard");
        }

        return modelAndView;
    }*/


	@RequestMapping(value="/teachersList", method = RequestMethod.GET)
	public ModelAndView allTeachersList() {
		ModelAndView modelAndView = new ModelAndView();
		
		List<Teacher> teachersList = teacherService.findAll();
		
		if (teachersList!=null) {
			for (Teacher teacher : teachersList) {
				
				int totalVoteOnTeacher = voteService.getAllVoteByTeacherId(teacher.getName(), true);
				//modelAndView.addObject("totalVote", totalVoteOnTeacher);
				teacher.setTotal(totalVoteOnTeacher);
			}

			
			modelAndView.addObject(teachersList);

			modelAndView.setViewName("teachersList");
		}
		
		/*int totalVoteByTeacher = voteService.getAllVoteByTeacherId(teacherService.findTeacherByName(name), true);
		System.out.println("totalVoteByTeacher : " + totalVoteByTeacher);
		modelAndView.addObject("totalTeacher", totalVoteByTeacher);*/

		//System.out.println("modeland view:.........................."+"         "+modelAndView);
		return modelAndView;
	}
	
	
	@RequestMapping(value="/teacherRegistration", method = RequestMethod.GET)
	public ModelAndView teacherRegistration(){
		ModelAndView modelAndView = new ModelAndView();
		Teacher teacher = new Teacher();
		modelAndView.addObject("teacher", teacher);
		modelAndView.setViewName("teacherRegistration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/teacherRegistration", method = RequestMethod.POST)
	public ModelAndView createNewTeacher(@Valid Teacher teacher, 
			BindingResult bindingResult ,@RequestParam("file")
	MultipartFile file, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView();
		Teacher teacherExists = teacherService.findteacherByEmail(teacher.getTeacherEmail());
		if (teacherExists != null) {
			bindingResult
					.rejectValue("email", "error.teacher",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("teacherRegistration");
		} else {
			
			if (file.isEmpty()) {
	            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
	            modelAndView.setViewName("teacherRegistration");
	        }
	        try {

	            // Get the file and save it somewhere
	            byte[] bytes = file.getBytes();
	      
	       /*    //for a particular folder
	            Path path = Paths.get(UPLOADED_FOLDER +file.getOriginalFilename());
	            Files.write(path, bytes);*/
	            
	            Blob fileBlob = new javax.sql.rowset.serial.SerialBlob(bytes);
				teacher.setmImage(fileBlob);
	       
	            redirectAttributes.addFlashAttribute("message",
	                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (SerialException e) {				
				e.printStackTrace();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			
			teacherService.saveTeacher(teacher);
			modelAndView.addObject("successMessage", "Teacher has been added successfully");
			modelAndView.addObject("teacher", new Teacher());
			modelAndView.setViewName("teacherRegistration");
			
		}
		return modelAndView;
	}
	
	//For Show image by teacher Name    
    @RequestMapping(value = "/user/avatar/image/{nameOfTeacher}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> viewsTeacherImage(@PathVariable("nameOfTeacher") String nameOfTeacher) {
       // System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+nameOfTeacher);
        Teacher imageEntity = null;
    	byte[] avatarImage = null;    	
    	
    		imageEntity = teacherService.findTeacherByName(nameOfTeacher);
            if (imageEntity != null) {
            	Blob imageBlob = imageEntity.getmImage();
            	try {
            		if (imageBlob != null) {
            			avatarImage = imageBlob.getBytes(1, (int) imageBlob.length());
					}
					
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
            	
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



