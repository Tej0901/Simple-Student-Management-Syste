package com.login.teja;

public class Student 
{
	
	private String ID;
	private String name;
	private String std;
	private int age;
	
	public Student(String ID,String name,String std,int age)
	{
		setID(ID);
		setName(name);
		setStd(std);
		setAge(age);
	}

	public String getID() {
		return ID;
	}
	public void setID(String ID) {
		this.ID = ID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStd() {
		return std;
	}
	public void setStd(String std) {
		this.std = std;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}