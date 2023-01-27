package com.naga.dao;

import java.util.List;

import com.naga.model.Emp;

public interface DaoSkelton
{
	public boolean connect();
	public int insert(Emp emp);
	public int delete(int empId);
	public int update(Emp emp);
	public List<Emp> readData();
	public List<Emp> readData(String name);
	public Emp readData(int empid);
	
}
