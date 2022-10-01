package com.masai.bean;

public class Batch {

	private int batchNo;
	private int seat;
	public int getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(int batchNo) {
		this.batchNo = batchNo;
	}
	public int getSeat() {
		return seat;
	}
	public void setSeat(int seat) {
		this.seat = seat;
	}
	public Batch(int batchNo, int seat) {
		super();
		this.batchNo = batchNo;
		this.seat = seat;
	}
	public Batch() {
		super();
	}
	@Override
	public String toString() {
		return "Batch [batchNo=" + batchNo + ", seat=" + seat + "]";
	}
	
}
