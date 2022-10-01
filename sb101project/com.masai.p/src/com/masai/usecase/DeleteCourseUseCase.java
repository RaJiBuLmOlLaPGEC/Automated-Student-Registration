package com.masai.usecase;

import java.util.Scanner;

import com.masai.dao.CourseDao;
import com.masai.dao.CourseDaoImpl;

public class DeleteCourseUseCase {

	public static void main() {
		Scanner sc= new Scanner(System.in);
		
		System.out.println("Enter course id to delete course :");
		int cid=sc.nextInt();
		
		CourseDao cdao=new CourseDaoImpl();
		String result=cdao.DeleteCourse(cid);
		System.out.println(result);

		sc.close();
	}

}
