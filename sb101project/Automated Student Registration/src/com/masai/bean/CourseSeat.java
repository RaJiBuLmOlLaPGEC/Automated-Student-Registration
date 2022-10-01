package com.masai.bean;

public class CourseSeat {

	private int cid;
	private String cname;
	
	private int seat;

	public CourseSeat(int cid, String cname, int seat) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.seat = seat;
	}

	public CourseSeat() {
		super();
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	@Override
	public String toString() {
		return "CourseSeat [cid=" + cid + ", cname=" + cname + ", seat=" + seat + "]";
	}
	
	
}
