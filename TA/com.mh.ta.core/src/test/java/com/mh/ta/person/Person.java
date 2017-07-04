package com.mh.ta.person;

public class Person {
	private String name, address;

	@Override
	public String toString() {
		String personInfo = String.format("Person info with name %s address %s, %d age and married %b", this.getName(),
				this.getAddress(), this.getAge(), this.getMarried());
		return personInfo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Boolean getMarried() {
		return married;
	}

	public void setMarried(Boolean married) {
		this.married = married;
	}

	public Person(String name, String address, int age, Boolean married) {
		super();
		this.name = name;
		this.address = address;
		this.age = age;
		this.married = married;
	}

	private int age;
	private Boolean married;

}
