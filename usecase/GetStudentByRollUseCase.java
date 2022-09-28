package com.masai.usecase;

import java.util.Scanner;

import com.masai.Exception.StudentException;
import com.masai.bean.Student;
import com.masai.dao.StudentDao;
import com.masai.dao.StudentDaoImpl;

public class GetStudentByRollUseCase {

	public static void main(String[] args) {
		//taking student roll by user
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter Student Roll:");
		int roll=sc.nextInt();
		
		
		StudentDao dao=new StudentDaoImpl();
		
		try {
			Student student=dao.getStudentBtRoll(roll);
			System.out.println(student);
		} catch (StudentException se) {
			// TODO Auto-generated catch block
			System.out.println(se.getMessage());
		}
		
		sc.close();
	}
}
