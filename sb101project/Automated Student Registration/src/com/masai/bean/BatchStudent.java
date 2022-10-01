package com.masai.bean;

public class BatchStudent {

	private int roll;
	
	private int batchNo;

	public BatchStudent(int roll, int batchNo) {
		super();
		this.roll = roll;
		this.batchNo = batchNo;
	}

	public BatchStudent() {
		super();
	}

	public int getRoll() {
		return roll;
	}

	public void setRoll(int roll) {
		this.roll = roll;
	}

	public int getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(int batchNo) {
		this.batchNo = batchNo;
	}

	@Override
	public String toString() {
		return "BatchStudent [roll=" + roll + ", batchNo=" + batchNo + "]";
	}
	
	
	
}
