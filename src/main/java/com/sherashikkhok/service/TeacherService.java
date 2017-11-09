package com.sherashikkhok.service;

import com.sherashikkhok.model.Teacher;

import java.util.List;

public interface TeacherService {

	public Teacher findteacherByEmail(String email);
	
	//public List<Teacher> findLatest5();
		
	public Teacher findTeacherByName(String name); 
	
	public void saveTeacher(Teacher teacher);
	
	List<Teacher> findAll();
	
}
