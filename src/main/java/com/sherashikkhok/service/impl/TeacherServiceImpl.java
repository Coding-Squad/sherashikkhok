package com.sherashikkhok.service.impl;

import com.sherashikkhok.model.Teacher;
import com.sherashikkhok.repository.TeacherRepository;
import com.sherashikkhok.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;



@Service("teacherService")
public class TeacherServiceImpl implements TeacherService{

	@Qualifier("teacherRepository")
	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	public Teacher findteacherByEmail(String email) {
		return teacherRepository.findByTeacherEmail(email) ;
	}
	
	

	@Override
	public Teacher findTeacherByName(String name) {
		// TODO Auto-generated method stub
		return teacherRepository.findByName(name);
	}

	@Override
	public void saveTeacher(Teacher teacher) {
		
		teacherRepository.save(teacher);
		
	}


	@Override
	public List<Teacher> findAll() {
		List<Teacher> teachersList = teacherRepository.findAll();		
		return teachersList;
	}



	/*@Override
	public List<Teacher> findLatest5() {
		List<Teacher> latestFive = teacherRepository.findLatest5();
		return latestFive;
	}
*/
}
