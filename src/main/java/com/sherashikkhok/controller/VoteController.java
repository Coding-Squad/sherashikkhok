package com.sherashikkhok.controller;

import com.sherashikkhok.model.Teacher;
import com.sherashikkhok.model.User;
import com.sherashikkhok.model.Vote;
import com.sherashikkhok.repository.VoteRepository;
import com.sherashikkhok.service.TeacherService;
import com.sherashikkhok.service.UserService;
import com.sherashikkhok.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;



@Controller
public class VoteController {

	@Autowired
	private UserService userService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private VoteService voteService;

	@Autowired
	private VoteRepository voteRepository;

	@RequestMapping(value = "/voteCount/{nameAndIdOfTeacher}", method = RequestMethod.POST)
	public ModelAndView votesCount(@Valid Vote vote, BindingResult bindingResult,
								   @PathVariable("nameAndIdOfTeacher") String nameOfTeacher) {

		Teacher teacher = null;
		ModelAndView modelAndView = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userExists = userService.findUserByEmail(auth.getName());
		if (userExists != null) {
			System.out.println("user Id: " + userExists.getId() + " , " + userExists.getEmail());

		} else {
			bindingResult.rejectValue("email", "error.user", "Please login for vote");
		}

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("login");
		} else {
			//System.out.println(">>>>>>>>>>"+nameOfTeacher);
			teacher = teacherService.findTeacherByName(nameOfTeacher);

			if (userExists != null) {

				long userId = userExists.getId();
				if (teacher != null){
					long teacherId = Long.valueOf(teacher.getTeacherId());
					if (voteService.isVotedByUserAndTeacher(userId, teacherId) == true) {

						long id = voteService.getVoteId(userId, teacherId);
						Vote voteToUpdate = voteRepository.findOne(id);
						voteRepository.delete(voteToUpdate);

						modelAndView.addObject("successMessage", "Vote Already given");
					} else {

						vote.setUserId(userExists.getId());
						vote.setTeacherId(Long.valueOf(teacher.getTeacherId()));
						vote.setTeacherName(teacher.getName());
						vote.setVote(true);

						voteService.saveVote(vote);
						modelAndView.addObject("successMessage", "Vote has been given");
					}
				}

			}
			modelAndView.addObject("vote", new Vote());
			modelAndView.setViewName("teachersList");
			/*modelAndView.setViewName("/admin/dashboard");*/
		}

		int totalVoteByTeacher = voteService.getAllVoteByTeacherId(nameOfTeacher, true);
	//	System.out.println("totalVoteByTeacher : " + totalVoteByTeacher);
		modelAndView.addObject("totalTeacher", totalVoteByTeacher);

		return modelAndView;

	}

}
