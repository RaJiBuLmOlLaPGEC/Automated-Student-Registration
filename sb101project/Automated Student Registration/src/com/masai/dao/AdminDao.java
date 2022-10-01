package com.masai.dao;

import java.util.List;

import com.masai.Exception.CourseException;
import com.masai.Exception.StudentException;
import com.masai.bean.Batch;
import com.masai.bean.Student;

public interface AdminDao {
	public String logInAsAdmin(String ausername,String apassword);
	
	public String createABatch(Batch batch);
	
	public String allocateStudentsInBatch(int batchNo,int roll)throws StudentException,CourseException;
	
	public String updateBatchSeat(int batchNo,int seat);
	
	public List<Student> studentOfBatch(int batchNo) throws Exception;
}
