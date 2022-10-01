package com.masai.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.masai.Exception.CourseException;
import com.masai.Exception.StudentException;
import com.masai.bean.Course;
import com.masai.bean.CourseSeat;
import com.masai.bean.Student;
import com.masai.dao.CourseDao;
import com.masai.dao.CourseDaoImpl;
import com.masai.dao.StudentDao;
import com.masai.dao.StudentDaoImpl;

public class StudentOperations {
	
	static CourseDao cdao=new CourseDaoImpl();
	static StudentDao sdao=new StudentDaoImpl();
	
	public static void selectOption() {
		System.out.println("Please Select the correct option given below..");
		System.out.println("1. Register in a course.\n"
				+ "2. Update student details.\n"
				+ "3. course list and their seat.\n"
				+ "4. Exit the application");
		Scanner sc= new Scanner(System.in);
		int choise=sc.nextInt();
		switch (choise) {
		case 1: {
			//Register in a course
			try {
				List<Course> courses=cdao.availableCourse();
				System.out.println("Available courses are"+courses);
				System.out.println("Enter Course Id: ");
				int cid= sc.nextInt();
				System.out.println("Re Enter Your username: ");
				String username=sc.next();
				Student student;
				try {
					student = sdao.getStudentByUsername(username);
					System.out.println(student);
					System.out.println("Enter your roll: ");
					int roll= sc.nextInt();
					String result= sdao.registerStudentInsideACourse(cid,roll);
					System.out.println(result);
				} catch (StudentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			} catch (CourseException e) {
				System.out.println(e.getMessage());
			}
			
			StudentOperations.selectOption();
			break;
		}
		case 2: {
			//Update student details.
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
			
			String result= sdao.updateStudent(student);
			System.out.println(result);
			StudentOperations.selectOption();
			break;
		}
		case 3: {
			//Course list and their seat.
			List<Course> courses;
			try {
				courses = cdao.availableCourse();
				System.out.println("Available courses are"+courses);
				
				System.out.println("Enter Course Id: ");
				int cid= sc.nextInt();
				CourseSeat courseseat= cdao.courseSeat(cid);
				System.out.println(courseseat);
				
			} catch (CourseException e) {
				System.out.println("No more seat available..");
			}
			
			StudentOperations.selectOption();
			break;
		}
		case 4: {
			StudentOperations.exitApp();
			break;
		}
		default:
			System.out.println("Unexpected value: " + choise);
			
		}
		sc.close();
	}
	public static void exitApp() {
		System.out.println("Thanks for using this application!.");
	}
	
}
