package com.masai.application;

import java.util.Scanner;

import com.masai.bean.Student;
import com.masai.dao.AdminDao;
import com.masai.dao.AdminDaoImpl;
import com.masai.dao.StudentDao;
import com.masai.dao.StudentDaoImpl;


public class App {

	public static void main(String[] args) {
		System.out.println("Welcome to Student Registration System");
		
		System.out.println("Please Select the correct option given below..");
		System.out.println("1. Login As Administrator\n2. Login As Student\n3. New Student?Register Now..");
		Scanner sc= new Scanner(System.in);
		int choise=sc.nextInt();
		
		AdminDao adao=new AdminDaoImpl();
		StudentDao sdao= new StudentDaoImpl();
		
		switch (choise) {
		case 1: {
			System.out.println("Welcome admin");
			System.out.println("Enter your username :");
			String username= sc.next();
			System.out.println("Enter your password :");
			String password= sc.next();
			String result= adao.logInAsAdmin(username,password);
			System.out.println(result);
			if(result=="Log in Sucessfull.") {
				AdminOperatins.selectOption();
			}
			break;
		}
		case 2: {
			System.out.println("Welcome Student");
			System.out.println("Enter your username :");
			String username= sc.next();
			System.out.println("Enter your password :");
			String password= sc.next();
			String result=sdao.studentlogin(username, password);
			System.out.println(result);
			if(result=="login successfull!") {
				StudentOperations.selectOption();
			}
			break;
		}
		case 3:{
			//New Student?Register Now
			System.out.println("wecome student");
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
			
			
			Student student=new Student();
			
			
			// set student details to student object;
			student.setName(name);
			student.setAddress(address);
			student.setEmail(email);
			student.setMobile_number(mobile_number);
			student.setPassword(password);
			
			String result= sdao.registerStudent(student);
			System.out.println(result);
			StudentOperations.selectOption();
			break;
		}
		default:
			System.out.println("Unexpected value: " + choise);
		}
		sc.close();

	}

}
