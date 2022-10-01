package com.masai.dao;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masai.Exception.CourseException;
import com.masai.Exception.StudentException;
import com.masai.bean.Batch;
import com.masai.bean.Student;
import com.masai.utility.DBUtil;

public class AdminDaoImpl implements AdminDao{

	@Override
	public String logInAsAdmin(String ausername, String apassword) {
		String message="Invalid username or password";
		
		try (Connection conn= DBUtil.provideConnection()){
			PreparedStatement ps= conn.prepareStatement("select * from admin where username=? AND password=?");
			
			ps.setString(1, ausername);
			ps.setString(2, apassword);
			
			ResultSet rs= ps.executeQuery();
			if(rs.next()) {
				message="Log in Sucessfull.";
				
			}
			else {
				message="Invalid username or Password.";
			}
			
		} catch (SQLException e) {
			message="Technical issue please try after some time..";
		}
		
		return message;
	}

	@Override
	public String createABatch(Batch batch) {
		String message="Batch not created.";
		try (Connection conn =DBUtil.provideConnection()){
			PreparedStatement ps= conn.prepareStatement("insert into batch values(?,?)");
			ps.setInt(1, batch.getBatchNo());
			ps.setInt(2, batch.getSeat());
			
			int x=ps.executeUpdate();
			if(x>0) {
				message="Batch created with seat"+batch.getSeat();
			}
			
			
		} catch (SQLException e) {
			message="Error!";
		}
		
		return message;
	}

	@Override
	public String allocateStudentsInBatch(int batchNo, int roll) throws StudentException,CourseException {
		String message="Not Allocated in batch";
		try (Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps= conn.prepareStatement("select * from student where roll=?");
			
			ps.setInt(1, roll);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				PreparedStatement ps2=conn.prepareStatement("select * from batch where batchNo=?");
				
				ps2.setInt(1, batchNo);
				ResultSet rs2=ps2.executeQuery();
				if(rs2.next()) {
					PreparedStatement ps3=conn.prepareStatement("insert into batch_student values(?,?)");
					ps3.setInt(1, batchNo);
					ps3.setInt(2, roll);
					
					int x= ps3.executeUpdate();
					
					if(x>0) {
						message="student Registered sucessfully inside a batch";
						PreparedStatement ps4=conn.prepareStatement("update batch set seat=seat-1 where batchNo=?");
						ps4.setInt(1, batchNo);
						int x1=ps4.executeUpdate();
						
						if(x1>0) {
							System.out.println("seat updated....");
						}
						else {
							throw new StudentException("Technical Error..");
						}
					}
					else {
						throw new CourseException("Invalid batch");
					}
				}
				else {
					throw new StudentException("Invalid Student");
				}
			}
			
			
			
		} catch (SQLException e) {
			throw new StudentException(e.getMessage());
		}
		
		return message;
	}

	@Override
	public String updateBatchSeat(int batchNo, int seat) {
		String message="Not updated";
		try (Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps= conn.prepareStatement("update batch set seat=? where batchNo=?");
			
			
			ps.setInt(1, seat);
			ps.setInt(2, batchNo);
			
			int x= ps.executeUpdate();
			
			if(x>0) {
				message="Seat of the batch updated to "+seat;
			}
			
			
		} catch (SQLException e) {
			message=e.getMessage();
		}
		
		
		return message;
	}

	@Override
	public List<Student> studentOfBatch(int batchNo) throws Exception{
		List<Student> students=new ArrayList<>();
		try (Connection conn =DBUtil.provideConnection()){
			PreparedStatement ps= conn.prepareStatement("select s.name, s.address,s.mobile_number,s.roll from student s ,batch b,batch_student bs where s.roll=bs.roll AND b.batchNo=bs.batchNo And bs.batchNo = ?");
			ps.setInt(1, batchNo);
			
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				String sname=rs.getString("name");
				int roll= rs.getInt("roll");
				String add=rs.getString("address");
				String m=rs.getString("mobile_number");
				
				
				
				Student student=new Student();
				student.setRoll(roll);
				student.setName(sname);
				student.setMobile_number(m);
				student.setAddress(add);
				
				students.add(student);
			}
			
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return students;
	}

}
