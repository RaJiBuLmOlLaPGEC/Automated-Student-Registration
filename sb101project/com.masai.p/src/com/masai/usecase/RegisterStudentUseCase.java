package com.masai.usecase;

import java.util.Scanner;

import com.masai.bean.Student;
import com.masai.dao.StudentDao;
import com.masai.dao.StudentDaoImpl;

// taking input and pass the constructor;

public class RegisterStudentUseCase {

	public static void main(String[] args) {
		//taking student details by user
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter Student name:");
		String name=sc.next();
		
		System.out.println("Enter Student email:");
		String email=sc.next();
		
		System.out.println("create a password:");
		String password=sc.next();
		
		System.out.println("Enter Student address");
		String address=sc.next();
		
		System.out.println("Enter Student mobile number:");
		String mobile_number=sc.next();
		
		
		//create a empty student object(default assign);
		Student student=new Student();
		
		
		// set student details to student object;
		student.setName(name);
		student.setAddress(address);
		student.setEmail(email);
		student.setMobile_number(mobile_number);
		student.setPassword(password);

		//I have student details --> put those 
		//details to student table.
		//for that i need studentImpl() object and 
		//need to call registerStudentmethod 
		//with student object parameter.
		
		
		StudentDao dao=new StudentDaoImpl();
		String result=dao.registerStudent(student);
		
		//result contain a message with inserted or not.
		System.out.println(result);
		
		sc.close();
	}
	

}
