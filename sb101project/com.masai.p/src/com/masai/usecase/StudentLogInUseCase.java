package com.masai.usecase;

import java.util.Scanner;

import com.masai.Exception.StudentException;
import com.masai.bean.Student;
import com.masai.dao.StudentDao;
import com.masai.dao.StudentDaoImpl;

public class StudentLogInUseCase {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		
		System.out.println("Enter the student email:");
		String email=sc.next();
		
		System.out.println("Enter Password:");
		String password=sc.next();
		
		StudentDao dao=new StudentDaoImpl();
		try {
			Student student=dao.studentlogin(email, password);
			System.out.println("Welcome "+student.getName());
			
		} catch (StudentException e) {
			
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		sc.close();
	}

}
