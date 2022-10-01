package com.masai.application;

import java.util.List;
import java.util.Scanner;

import com.masai.Exception.CourseException;
import com.masai.Exception.StudentException;
import com.masai.bean.Batch;
import com.masai.bean.Course;
import com.masai.bean.Student;
import com.masai.dao.AdminDao;
import com.masai.dao.AdminDaoImpl;
import com.masai.dao.CourseDao;
import com.masai.dao.CourseDaoImpl;
import com.masai.dao.StudentDao;
import com.masai.dao.StudentDaoImpl;

public class AdminOperatins {
	
	static AdminDao adao= new AdminDaoImpl();
	static StudentDao sdao= new StudentDaoImpl();
	static CourseDao cdao= new CourseDaoImpl();
	
	
	public static void selectOption() {
		System.out.println("Please Select the correct option given below..");
		System.out.println("1. Add a new course\n"
				+ "2. Update Fees of course.\n"
				+ "3. Delete a course.\n"
				+ "4. Search information about course.\n"
				+ "5. Create Batch.\n"
				+ "6. Allocate students in a Batch.\n"
				+ "7. Update total seats of a batch.\n"
				+ "8. View the students of every batch.\n"
				+ "9. Exit Application.");
		Scanner sc= new Scanner(System.in);
		int choise=sc.nextInt();
		switch (choise) {
		case 1: {
			//Add a new Course
			System.out.println("Enter Course Name: ");
			String cname=sc.next();
			
			System.out.println("Enter Course Fee: ");
			int fee=sc.nextInt();
			
			System.out.println("Enter course Duration: ");
			String duration=sc.next();
			
			System.out.println("Enter the total seat: ");
			int seat=sc.nextInt();
			
			Course course= new Course();
			
			course.setCname(cname);
			course.setFee(fee);
			course.setDuration(duration);
			course.setSeat(seat);
			
			String result=cdao.addaNewCourse(course);
			System.out.println(result);
			AdminOperatins.selectOption();
			break;
		}
		case 2: {
//			Update Fees of course.
			try {
				List<Course> courses= cdao.availableCourse();
				System.out.println(courses);
				System.out.println("Enter Course Id to update fees:");
				int cid=sc.nextInt();
				System.out.println("Enter fees ammount for update : ");
				int fee=sc.nextInt();
				String result= cdao.updateCourseFee(cid, fee);
				System.out.println(result);
			} catch (CourseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			AdminOperatins.selectOption();
			break;
		}
		case 3: {
//			Delete  a course from any Training session
			List<Course> courses;
			try {
				courses = cdao.availableCourse();
				System.out.println(courses);
			} catch (CourseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Enter Course Id to delete: ");
			int cid=sc.nextInt();
			String result= cdao.deleteCourse(cid);
			System.out.println(result);
			AdminOperatins.selectOption();
			break;
		}
		case 4: {
//			Search information about course.
			List<Course> courses;
			try {
				courses = cdao.availableCourse();
				System.out.println(courses);
				System.out.println("Enter Course Id to show details: ");
				int cid=sc.nextInt();
				Course course= cdao.courseDetails(cid);
				System.out.println(course);
			} catch (CourseException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}
			AdminOperatins.selectOption();
			break;
		}
		case 5: {
//			Create Batch.
			System.out.println("Enter batch no(Should be integer): ");
			int batchNo=sc.nextInt();
			System.out.println("Enter batch capacity: ");
			int seat=sc.nextInt();
			
			Batch batch=new Batch();
			batch.setBatchNo(batchNo);
			batch.setSeat(seat);
			
			String result=adao.createABatch(batch);
			System.out.println(result);
			
			AdminOperatins.selectOption();
			break;
		}
		case 6: {
//			Allocate students in a Batch.
			System.out.println("Enter Student roll: ");
			int roll=sc.nextInt();
			System.out.println("Enter batch No:");
			int batchNo=sc.nextInt();
			
			String result;
			try {
				result = adao.allocateStudentsInBatch(batchNo, roll);
				System.out.println(result);
			} catch (StudentException | CourseException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			
			AdminOperatins.selectOption();
			break;
		}
		case 7: {
//			Update total seats of a batch.
			System.out.println("Enter batch No: ");
			int batchNo=sc.nextInt();
			System.out.println("Enter seat: ");
			int seat=sc.nextInt();
			
			String result=adao.updateBatchSeat(batchNo, seat);
			System.out.println(result);
			AdminOperatins.selectOption();
			break;
		}
		case 8: {
//			View the students of every batch.
			System.out.println("Enter batch No: ");
			int batchNo=sc.nextInt();
			try {
				List<Student>students= adao.studentOfBatch(batchNo);
				for(int i=0;i<students.size();i++) {
					System.out.println("name: "+students.get(i).getName()+",roll: "+students.get(i).getRoll()+",address: "+students.get(i).getAddress()+",mobile: "+students.get(i).getMobile_number());
				}
				if(students.size()==0) {
					System.out.println("invalid batch no");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			AdminOperatins.selectOption();
			break;
		}
		case 9: {
			AdminOperatins.exitApp();
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
