package com.sherashikkhok.controller;

import com.sherashikkhok.model.Posts;
import com.sherashikkhok.model.Teacher;
import com.sherashikkhok.model.User;
import com.sherashikkhok.service.PostsService;
import com.sherashikkhok.service.TeacherService;
import com.sherashikkhok.service.UserService;
import com.sherashikkhok.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private VoteService voteService;
	

	@Autowired
	private PostsService postsService;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		List<Teacher> teachersList = teacherService.findAll();

		if (teachersList!=null) {
			for (Teacher teacher : teachersList) {
				
				int totalVoteOnTeacher = voteService.getAllVoteByTeacherId(teacher.getName(), true);
				modelAndView.addObject("totalVote", totalVoteOnTeacher);
				teacher.setTotal(totalVoteOnTeacher);
			}

			
			modelAndView.addObject(teachersList);
			modelAndView.setViewName("index");
		}
		
		List<Posts> postsList = postsService.getAllPosts();
		if (postsList != null) {
			modelAndView.addObject(postsList);
			modelAndView.setViewName("index");
		}
		
		
		/*List<Posts> postsList = postsService.getAllPosts();
		if (postsList != null) {
			modelAndView.addObject(postsList);
			modelAndView.setViewName("postsList");
		}*/

		// modelAndView.setViewName("index");
		return modelAndView;
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user",
					"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");

		}
		return modelAndView;
	}

	@RequestMapping(value = "/admin/dashboard", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName",
				"<center>Welcome</center> <br></br>" + user.getName() + " " + user.getLastName() + "<br></br> "
						+ "<a style=" + "text-decoration:none;" + "color:white;" + " target=" + "blank" + " href="
						+ user.getFacebookId() + ">Facebook ID Link</a>" + "<br></br> " + user.getInstituteName()
						+ "<br></br> " + user.getMobileNumber() + "<br></br> (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");

        List<Teacher> allTeachersList = teacherService.findAll();

        if (allTeachersList!=null) {
            for (Teacher teacher : allTeachersList) {
                System.out.println("Blob Image found : "+teacher.getmImage());
                int totalVoteOnTeacher = voteService.getAllVoteByTeacherId(teacher.getName(), true);
                teacher.setTotal(totalVoteOnTeacher);
            }

            modelAndView.addObject("myallteacher",allTeachersList);
        }

		modelAndView.setViewName("admin/dashboard");
		return modelAndView;
	}

}
