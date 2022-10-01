package com.masai.bean;

public class Course {

	private int fee;
	private String cname;
	private String duration;
	private int cid;
	private int seat;
	public Course(int fee, String cname, String duration, int cid,int seat) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.fee = fee;
		this.duration = duration;
		this.seat=seat;
	}
	public int getSeat() {
		return seat;
	}
	public void setSeat(int seat) {
		this.seat = seat;
	}
	public Course() {
		super();
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	@Override
	public String toString() {
		return "Course [fee=" + fee + ", cname=" + cname + ", duration=" + duration + ", cid=" + cid + ", seat=" + seat
				+ "]";
	}
	
	
	
	
}
