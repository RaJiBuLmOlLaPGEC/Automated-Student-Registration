package com.masai.usecase;

import java.util.Scanner;


import com.masai.dao.CourseDao;
import com.masai.dao.CourseDaoImpl;

public class UpdateFeesUseCase {

	public static void main() {
		Scanner sc= new Scanner(System.in);
		
		System.out.println("Enter Course Id to update fees : ");
		int cid=sc.nextInt();
		
		System.out.println("Enter Updated Course Fee: ");
		int fee=sc.nextInt();
		
		CourseDao cdao=new CourseDaoImpl();
		
		String result=cdao.UpdateCourseFee(cid, fee);
		
		System.out.println(result);
		
		sc.close();

	}

}
