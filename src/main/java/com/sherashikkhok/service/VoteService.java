package com.sherashikkhok.service;

import com.sherashikkhok.model.Vote;

import java.util.List;

public interface VoteService {
	
	 List<Vote> findByUserId(long userId);
	 
	 int getAllVoteByTeacherId(String teacherName, boolean isVoted);
	 
	 boolean isVotedByUserAndTeacher(long userId, long teacherId);
	 
	 public void saveVote(Vote vote);
	 
	 public long getVoteId(long userId, long teacherId);
	 
}
