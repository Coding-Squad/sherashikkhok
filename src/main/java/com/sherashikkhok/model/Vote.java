package com.sherashikkhok.model;

import javax.persistence.*;

@Entity
@Table(name = "vote")
public class Vote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "vote_id")
	private Long voteId;
	
	@Column(name = "user_id")
	//@NotEmpty(message = "*Please provide User Id")
	private Long userId;
	
	@Column(name = "teacher_id")
	//@NotEmpty(message = "*Please provide Teacher Id")
	private Long teacherId;
	
	@Column(name = "teacher_name")
	//@NotEmpty(message = "*Please provide your name")
	private String teacherName;
	

	@Column(name = "vote")
	private Boolean vote;


	public Long getVoteId() {
		return voteId;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public Long getTeacherId() {
		return teacherId;
	}


	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}


	public String getTeacherName() {
		return teacherName;
	}


	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}


	public Boolean getVote() {
		return vote;
	}


	public void setVote(Boolean vote) {
		this.vote = vote;
	}


	@Override
	public String toString() {
		return "Vote [voteId=" + voteId + ", userId=" + userId + ", teacherId=" + teacherId + ", teacherName="
				+ teacherName + ", vote=" + vote + "]";
	}
	
}
