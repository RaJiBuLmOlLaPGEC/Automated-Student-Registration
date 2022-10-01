package com.masai.usecase;

import java.util.Scanner;

import com.masai.Exception.CourseException;
import com.masai.bean.Course;
import com.masai.dao.CourseDao;
import com.masai.dao.CourseDaoImpl;

public class CourseDetailsUseCase {

	public static void main() {
		Scanner sc= new Scanner(System.in);
		
		System.out.println("Enter Course Id to get details:");
		int cid=sc.nextInt();
		
		CourseDao cdao= new CourseDaoImpl();
		try {
			Course course=cdao.CourseDetails(cid);
			System.out.println(course);
			
		} catch (CourseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		sc.close();
	}

}
