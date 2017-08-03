package com.mh.ta.guice;

public class Information {
	private String phone, age, job, address;
	

	public Information(String phone, String age, String job, String address) {
		this.phone = phone;
		this.age = age;
		this.job = job;
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
