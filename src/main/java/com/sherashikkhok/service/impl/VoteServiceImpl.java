package com.sherashikkhok.service.impl;

import com.sherashikkhok.model.Vote;
import com.sherashikkhok.repository.VoteRepository;
import com.sherashikkhok.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service("voteService")
public class VoteServiceImpl implements VoteService{
	
	@Autowired
	private VoteRepository voteRepository;


	@Override
	public List<Vote> findByUserId(long userId) {
		// TODO find By User Id
	
		
		return null;
	}



	@Override
	public boolean isVotedByUserAndTeacher(long userId, long teacherId) {
		// TODO find Vote By User And Teacher
		boolean voted = false;
		List<Vote> votes = voteRepository.findAll();
		if (votes != null) {
			for (Vote vote : votes) {
				if (userId == vote.getUserId() && teacherId == vote.getTeacherId() ) {					
					voted = true;
				}
			}
		}
		return voted;
	}



	@Override
	public int getAllVoteByTeacherId(String teacherName, boolean isVoted) {
		// TODO get All Vote By Teacher Id method stub
		int tolalVoteCount = 0;
		List<Vote> votes = voteRepository.findAll();
		if (votes != null) {
			for (Vote vote : votes) {
				//System.out.println(teacherName.equalsIgnoreCase(vote.getTeacherName()));
				if (teacherName.equalsIgnoreCase(vote.getTeacherName()) && isVoted == true) {
				//	System.out.println(vote.getVoteId());
					tolalVoteCount=tolalVoteCount+1;
				}
			}
			
		}
		
		return tolalVoteCount;
	}



	@Override
	public void saveVote(Vote vote) {
		voteRepository.save(vote);
		
	}



	@Override
	public long getVoteId(long userId, long teacherId) {
		long id = 0;
		List<Vote> votes = voteRepository.findAll();		
		if (votes != null) {
			for (Vote vote : votes) {
				if (userId == vote.getUserId() && teacherId == vote.getTeacherId()) {
					id = vote.getVoteId();
				}
			}
		}
		return id;
	}



}
