package com.masai.usecase;

import java.util.Scanner;

import com.masai.bean.Student;
import com.masai.dao.StudentDao;
import com.masai.dao.StudentDaoImpl;

public class UpdateStudentDetails {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter you roll to update details :");
		int roll=sc.nextInt();
		
		System.out.println("Enter updated Student name:");
		String name=sc.next();
		
		System.out.println("Enter updated Student email:");
		String email=sc.next();
		
		System.out.println("create a new password:");
		String password=sc.next();
		
		System.out.println("Enter Student  new address");
		String address=sc.next();
		
		System.out.println("Enter Student new mobile number:");
		String mobile_number=sc.next();
		
		
		//create a empty student object(default assign);
		Student student=new Student();
		
		
		// set student details to student object;
		student.setRoll(roll);
		student.setName(name);
		student.setAddress(address);
		student.setEmail(email);
		student.setMobile_number(mobile_number);
		student.setPassword(password);

		//I have student details --> update those 
		//details to student table.
		//for that i need studentImpl() object and 
		//need to call registerStudentmethod 
		//with student object parameter.
		
		
		StudentDao dao=new StudentDaoImpl();
		String result=dao.updateStudent(student);
		
		//result contain a message with inserted or not.
		System.out.println(result);
		
		sc.close();

	}

}
