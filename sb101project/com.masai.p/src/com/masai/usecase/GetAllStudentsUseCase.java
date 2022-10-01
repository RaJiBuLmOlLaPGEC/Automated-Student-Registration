package com.masai.usecase;

import java.util.List;

import com.masai.Exception.StudentException;
import com.masai.bean.Student;
import com.masai.dao.StudentDao;
import com.masai.dao.StudentDaoImpl;

public class GetAllStudentsUseCase {

	public static void main(String[] args) {
		StudentDao dao= new StudentDaoImpl();
		
		
		try {
			List<Student> students= dao.getAllStudentDetails();
			
			students.forEach(s -> {
				System.out.println(s);
				System.out.println("============================");
			});
			
		} catch (StudentException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		
	}

}
