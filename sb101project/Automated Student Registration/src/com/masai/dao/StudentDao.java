package com.masai.dao;

import java.util.List;

import com.masai.Exception.CourseException;
import com.masai.Exception.StudentException;
import com.masai.bean.Student;

//Data Access Operation.
//Show all the operation as like menu card 
//it is a interface

public interface StudentDao {
	
	//all the parameter comes form student bean class.
	public String registerStudent(Student student);
	
	public String updateStudent(Student student);
	
	public Student getStudentByRoll(int roll)throws StudentException;
	
	public Student getStudentByUsername(String username) throws StudentException;
	
	public String studentlogin(String email,String password);
	
	public List<Student> getAllStudentDetails()throws StudentException;
	
	public String registerStudentInsideACourse(int cid,int roll);
}
