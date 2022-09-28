package com.masai.dao;

import com.masai.Exception.StudentException;
import com.masai.bean.Student;

//Data Access Operation.
//Show all the operation as like menu card 
//it is a interface

public interface StudentDao {
	
	//all the parameter comes form student bean class.
	public String registerStudent(Student student);
	
	public Student getStudentBtRoll(int roll)throws StudentException;
	
}
