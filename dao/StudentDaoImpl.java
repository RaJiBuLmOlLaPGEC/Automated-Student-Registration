package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.masai.Exception.StudentException;
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
			
		} catch (SQLException e) {
			message="Not Registred because of "+e.getMessage();
		}
		
		return message;
	}

	@Override
	public Student getStudentBtRoll(int roll) throws StudentException {
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
			e.printStackTrace();
			//get the proper message to client; not return null;
			throw new StudentException(e.getMessage());
		}
		
		
		return student;
	}

}
