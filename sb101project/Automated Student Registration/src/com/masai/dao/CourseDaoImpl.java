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
import com.masai.bean.CourseSeat;
import com.masai.utility.DBUtil;
import com.mysql.cj.protocol.Resultset;

public class CourseDaoImpl implements CourseDao {

	@Override
	public String addaNewCourse(Course course) {
		String message="Not Added the Course: "+course.getCname();
		
		try (Connection conn= DBUtil.provideConnection()){
			
			PreparedStatement ps= conn.prepareStatement("insert into course (cname,fee,duration,seat) values(?,?,?,?)");
			
			ps.setString(1, course.getCname());
			ps.setInt(2, course.getFee());
			ps.setString(3, course.getDuration());
			ps.setInt(4, course.getSeat());
			
			int x=ps.executeUpdate();
			
			if(x>0) {
				message="Added the Course: "+course.getCname();
			}
			else {
				message="Duplicate Course found "+course.getCname();
			}
			
		} catch (SQLException e) {
			message="Not Added the Course because of "+e.getMessage();
		}
		
		
		return message;
	}

	@Override
	public String updateCourseFee(int cid, int fee) {
		String message="Id not matched to the course.";
		
		try (Connection conn= DBUtil.provideConnection()){
			PreparedStatement ps= conn.prepareStatement("update course set fee = ? where cid = ?");
			
			ps.setInt(1, fee);
			ps.setInt(2, cid);
			
			int x=ps.executeUpdate();
			
			if(x>0) {
				message="Course fees updated.";
			}
			else {
				message="Course not found with id: "+cid;
			}
			
			
		} catch (SQLException e) {
			message="Not updated for "+e.getMessage();
		}
		
		return message;
	}

	@Override
	public String deleteCourse(int cid) {
		String message="Course Not deleted.";
		
		try (Connection conn= DBUtil.provideConnection()){
			
			PreparedStatement ps= conn.prepareStatement("delete from course where cid = ?");
			
			ps.setInt(1, cid);
			
			int x=ps.executeUpdate();
			
			if(x>0) {
				message="Course deleted.";
			}
			else {
				message="course not found with id: "+cid;
			}
			
		} catch (SQLException e) {
			message="Student are enrolled this course You can't delete it";
		}
		
		
		return message;
	}

	@Override
	public Course courseDetails(int cid) throws CourseException{
		Course course=null;
		
		try (Connection conn= DBUtil.provideConnection()){
			
			PreparedStatement ps= conn.prepareStatement("Select * from course where cid=?");
			ps.setInt(1, cid);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int i=rs.getInt("cid");
				String cn=rs.getString("cname");
				int f=rs.getInt("fee");
				String d=rs.getString("duration");
				
				course=new Course();
				
				course.setCid(i);
				course.setCname(cn);
				course.setFee(f);
				course.setDuration(d);
			}
			else {
				throw new CourseException("Course doesnot exist with id "+cid);
			}
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			//get the proper message to client; not return null;
			throw new CourseException(e.getMessage());
		}
		
		
		return course;
	}

	@Override
	public List<Course> availableCourse() throws CourseException {
		List<Course> courses=new ArrayList<>();
		try (Connection conn= DBUtil.provideConnection()){
			PreparedStatement ps= conn.prepareStatement("select * from course");
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				String cname=rs.getString("cname");
				int cid=rs.getInt("cid");
				int fee=rs.getInt("fee");
				String duration=rs.getString("duration");
				int seat=rs.getInt("seat");
				
				Course course=new Course();
				
				course.setCid(cid);
				course.setCname(cname);
				course.setFee(fee);
				course.setDuration(duration);
				course.setSeat(seat);
				
				courses.add(course);
			}
			if(courses.size() == 0)
				throw new CourseException("No Course available..");
			
		} catch (SQLException e) {
			throw new CourseException("Course table not available or technical issue"); 
		}
		return courses;
	}

	@Override
	public CourseSeat courseSeat(int cid) throws CourseException{
		CourseSeat courseseat=null;
		
		try (Connection conn= DBUtil.provideConnection()){
			PreparedStatement ps= conn.prepareStatement("select c.cname,s.cid  ,s.seat"
					+ "from  course c INNER JOIN course_seat s"
					+ "ON c.cid = s.cid AND s.cid= ?");
			ps.setInt(1, cid);
			ResultSet rs= ps.executeQuery();
			if(rs.next()) {
				String cname=rs.getString("cname");
				int fee=rs.getInt("fee");
				int seat=rs.getInt("seat");
				
				courseseat=new CourseSeat();
				
				courseseat.setCid(cid);
				courseseat.setCname(cname);
				courseseat.setSeat(seat);
				
			}
			else {
				throw new CourseException("No course found with this id");
			}
			
		} catch (SQLException e) {
			throw new CourseException(e.getMessage());
		}
		return courseseat;
	}

}
