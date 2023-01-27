package com.naga.model;

public class Emp
{
	private int empid;
	private int addrid;
	private String firstName,lastName,address;
	
	public Emp()
	{
		
	}
	
	public Emp(int empid, String firstName, String lastName,float salary,int addrid,String address) {
		super();
		this.empid = empid;
		this.addrid = addrid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.salary = salary;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	private float salary;
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public int getAddrid() {
		return addrid;
	}
	public void setAddrid(int addrid) {
		this.addrid = addrid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
}
