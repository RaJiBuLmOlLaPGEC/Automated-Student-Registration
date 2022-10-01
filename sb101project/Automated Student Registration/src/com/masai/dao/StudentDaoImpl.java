package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masai.Exception.CourseException;
import com.masai.Exception.StudentException;
import com.masai.bean.Course;
import com.masai.bean.Student;
import com.masai.utility.DBUtil;
import com.mysql.cj.protocol.Resultset;

public class StudentDaoImpl implements StudentDao{

	//get all the details of new student and insert student table;
	

	@Override
	public String registerStudent(Student student) {
		String message="Not Registred...";
		
		//get the connection for insertion data to student table
		try (Connection conn=DBUtil.provideConnection()){
			PreparedStatement ps= conn.prepareStatement("insert into student (name,username,password,address,mobile_number) values (?,?,?,?,?)");
			
			ps.setString(1, student.getName());
			ps.setString(2, student.getEmail());
			ps.setString(3, student.getPassword());
			ps.setString(4, student.getAddress());
			ps.setString(5, student.getMobile_number());
			
			int x=ps.executeUpdate();
			
			if(x>0) {
				message="Student Registered Sucessfully !";
			}
			else {
				message="Student Not registred";
			}
			
		} catch (SQLException e) {
			message="Not Registred because of "+e.getMessage();
		}
		
		return message;
	}

	@Override
	public Student getStudentByRoll(int roll) throws StudentException {
		Student student=null;
		
		try (Connection conn=DBUtil.provideConnection()){
			PreparedStatement ps= conn.prepareStatement("select * from student where roll=?");
			
			ps.setInt(1, roll);
			
			ResultSet rs= ps.executeQuery();
			
			if(rs.next()) {
				int r=rs.getInt("roll");
				String n=rs.getString("name");
				String e=rs.getString("username");
				String p=rs.getString("password");
				String a=rs.getString("address");
				String m=rs.getString("mobile_number");
				
				student=new Student();
				
				student.setRoll(r);
				student.setName(n);
				student.setAddress(a);
				student.setEmail(e);
				student.setMobile_number(m);
				student.setPassword(p);
			}
			else {
				throw new StudentException("Student doesnot exist with roll "+roll);
			}
			
		} catch (SQLException e) {
			throw new StudentException(e.getMessage());
		}
		
		
		return student;
	}

	@Override
	public String studentlogin(String email, String password){
		String message="Invalid username or password!";
		try (Connection conn=DBUtil.provideConnection()){
			PreparedStatement ps= conn.prepareStatement("select * from student where username=? AND password=?");
			
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs= ps.executeQuery();
			
			if(rs.next()) {
				message="login successfull!";
			}
			else {
				message="Invalid Username or Password!";
			}
			
		} catch (SQLException e) {
			message="Technical issue please try after some time..";
		}
		
		
		return message;
	}

	@Override
	public List<Student> getAllStudentDetails() throws StudentException {
		List<Student> students=new ArrayList<>();
		
		try (Connection conn= DBUtil.provideConnection()){
			PreparedStatement ps= conn.prepareStatement("select * from student");
			
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()) {
				int r=rs.getInt("roll");
				String n=rs.getString("name");
				String e=rs.getString("username");
				String p=rs.getString("password");
				String a=rs.getString("address");
				String m=rs.getString("mobile_number");
				
				Student student=new Student();
				
				student.setRoll(r);
				student.setName(n);
				student.setAddress(a);
				student.setEmail(e);
				student.setMobile_number(m);
				student.setPassword(p);
				
				students.add(student);
			}
			
			if(students.size()==0) {
				throw new StudentException("No student Found..");
			}
			
			
		} catch (SQLException e) {
			throw new StudentException(e.getMessage());
		}
		
		
		return students;
	}

	@Override
	public String updateStudent(Student student) {
		String message="Not Updated...";
		
		//get the connection for insertion data to student table
		try (Connection conn=DBUtil.provideConnection()){
			PreparedStatement ps= conn.prepareStatement("update student set name=?, username=?,password=?,address=?,mobile_number=? where roll=?;");
			
			ps.setString(1, student.getName());
			ps.setString(2, student.getEmail());
			ps.setString(3, student.getPassword());
			ps.setString(4, student.getAddress());
			ps.setString(5, student.getMobile_number());
			ps.setInt(6, student.getRoll());
			
			int x=ps.executeUpdate();
			
			if(x>0) {
				message="Student details updated Sucessfully !";
			}
			else {
				message="Not updated.";
			}
			
		} catch (SQLException e) {
			message="Not updated because of "+e.getMessage();
		}
		
		return message;
	}

	@Override
	public String registerStudentInsideACourse(int cid, int roll) {
		String message="Not Registered..";
		try {
			Student student= getStudentByRoll(roll);
			CourseDao cdao= new CourseDaoImpl();
			try {
				Course course= cdao.courseDetails(cid);
				
				try (Connection conn= DBUtil.provideConnection()){
					PreparedStatement ps= conn.prepareStatement("insert into course_student values(?,?)");
					ps.setInt(1, roll);
					ps.setInt(2, cid);
					int x=ps.executeUpdate();
					if(x>0) {
						message=course.getCname()+" Course assigned to "+student.getName();
					}
					else {
						message="Technical Error!";
					}
					
				} catch (SQLException e) {
					System.out.println(message+e.getMessage());
				}
				
			} catch (CourseException e) {
				System.out.println(e.getMessage());
			}
			
		} catch (StudentException e) {
			System.out.println(e.getMessage());
		}
		return message;
	}

	@Override
	public Student getStudentByUsername(String username) throws StudentException {
		Student student=null;
		
		try (Connection conn=DBUtil.provideConnection()){
			PreparedStatement ps= conn.prepareStatement("select * from student where username=?");
			
			ps.setString(1, username);
			
			ResultSet rs= ps.executeQuery();
			
			if(rs.next()) {
				int r=rs.getInt("roll");
				String n=rs.getString("name");
				String e=rs.getString("username");
				String p=rs.getString("password");
				String a=rs.getString("address");
				String m=rs.getString("mobile_number");
				
				student=new Student();
				
				student.setRoll(r);
				student.setName(n);
				student.setAddress(a);
				student.setEmail(e);
				student.setMobile_number(m);
				student.setPassword(p);
			}
			else {
				throw new StudentException("Student doesnot exist with username "+username);
			}
			
		} catch (SQLException e) {
			throw new StudentException(e.getMessage());
		}
		
		
		return student;
	}
}
