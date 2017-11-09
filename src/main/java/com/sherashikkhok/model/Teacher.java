package com.sherashikkhok.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;


@Entity
@Table(name = "teacher")
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "teacher_id")
	private int teacherId;
	
	@Column(name = "teacherEmail")
	@Email(message ="*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String teacherEmail;
	
	@Column(name = "instituteName")
	@NotEmpty(message = "*Please provide your School/College/University name")
	private String instituteName;
	
	@Column(name = "name")
	@NotEmpty(message = "*Please provide your name")
	private String name;
	
	@Column(name = "mobile")
	@NotEmpty(message = "*Please provide your mobile number")
	private String mobileNumber;	

	@Column(name = "nid")
	private String nid;
	
	@Column(name = "facebookId")
	private String facebookId;
	
	@Column(name = "aboutTeacher",  length = 800)
	@NotEmpty(message = "*Please write something about your favourite teacher.")
	private String aboutTeacher;
	
	@Column(name = "teacher_Image")
	private Blob mImage;
	
	@Column(nullable = false)
	private Date date = new Date();
	
	
	@Transient
	private MultipartFile teacherImage;

	@Transient
	private int total;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public MultipartFile getTeacherImage() {
		return teacherImage;
	}

	public void setTeacherImage(MultipartFile teacherImage) {
		this.teacherImage = teacherImage;
	}

	public Blob getmImage() {
		return mImage;
	}

	public void setmImage(Blob mImage) {
		this.mImage = mImage;
	}

	

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherEmail() {
		return teacherEmail;
	}

	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getAboutTeacher() {
		return aboutTeacher;
	}

	public void setAboutTeacher(String aboutTeacher) {
		this.aboutTeacher = aboutTeacher;
	}

	



	@Override
	public String toString() {
		return "Teacher [teacherId=" + teacherId + ", teacherEmail=" + teacherEmail + ", instituteName=" + instituteName
				+ ", name=" + name + ", mobileNumber=" + mobileNumber + ", nid=" + nid + ", facebookId=" + facebookId
				+ ", aboutTeacher=" + aboutTeacher + ", mImage=" + mImage + ", teacherImage=" + teacherImage + "]";
	}
	
	

			
}
