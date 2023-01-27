package com.naga.model;

public class Address {
	private int addrid;
	private String address;
	
	public Address() {
		super();
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
	public Address(int addrid, String address) {
		super();
		this.addrid = addrid;
		this.address = address;
	}
}
