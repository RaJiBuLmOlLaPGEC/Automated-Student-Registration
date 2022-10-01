package com.masai.bean;

public class Student {

	private int roll;
	private String name;
	private String email;
	private String password;
	private String address;
	private String mobile_number;
	public Student(int roll, String name, String email, String password, String address, String mobile_number) {
		super();
		this.roll = roll;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.mobile_number = mobile_number;
	}
	public Student() {
		super();
	}
	public int getRoll() {
		return roll;
	}
	public void setRoll(int roll) {
		this.roll = roll;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	@Override
	public String toString() {
		return "Student [roll=" + roll + ", name=" + name + ", email=" + email + ", password=" + password + ", address="
				+ address + ", mobile_number=" + mobile_number + "]";
	}
	
}
