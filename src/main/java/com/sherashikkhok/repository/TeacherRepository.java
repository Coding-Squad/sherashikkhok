package com.sherashikkhok.repository;

import com.sherashikkhok.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("teacherRepository")
public interface TeacherRepository extends JpaRepository<Teacher, Long>{
	
	Teacher findByTeacherEmail(String email);
	
	Teacher findByName(String name);
	
	//List<Teacher> findLatest5();

}
