package com.masai.dao;

import java.sql.SQLException;
import java.util.List;

import com.masai.Exception.CourseException;
import com.masai.bean.Course;
import com.masai.bean.CourseSeat;

public interface CourseDao {

	public String addaNewCourse(Course course);
	
	public String updateCourseFee(int cid,int fee);
	
	public String deleteCourse(int cid);
	
	public Course courseDetails(int cid) throws CourseException;
	
	public List<Course> availableCourse() throws CourseException;
	
	public CourseSeat courseSeat(int cid) throws CourseException;
}
