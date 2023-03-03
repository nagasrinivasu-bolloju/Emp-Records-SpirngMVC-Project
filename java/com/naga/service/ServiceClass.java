package com.naga.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naga.aspect.LoggingService;
import com.naga.dao.DaoSkelton;
import com.naga.model.Emp;

@Service
public class ServiceClass
{
	@Autowired
	DaoSkelton dbClass;
	
	@LoggingService			//Logging service is an custom aspect annotation
	public ServiceClass() throws SQLException
	{
//		log.info("ServiceClass Object Created.");
	}
	@LoggingService
	public boolean connect()
	{
		return dbClass.connect();	
	}
	@LoggingService
	public int insert(Emp emp) throws SQLException {
		// TODO Auto-generated method stub
//		log.info("inside service class.");
//		String firstName=request.getParameter("firstName");
//		String lastName=request.getParameter("lastName");
//		String address=request.getParameter("address");
//		float salary=Float.parseFloat(request.getParameter("salary"));
//		int addrid=Integer.parseInt(request.getParameter("addrid"));
		
//		String firstName=emp.getFirstName();
//		String lastName=emp.getLastName();
//		String address=emp.getAddress();
//		float salary=emp.getSalary();
//		int addrid=emp.getAddrid();
		 
//		Emp emp2=new Emp(0,firstName,lastName,salary,addrid,address);
		return dbClass.insert(emp);
		
	}
	
	@LoggingService
	public void update(Emp emp)
	{
		dbClass.update(emp);
	}
	
	//ModelAndView mv=new ModelAndView(); can be mocked when testing.
	@LoggingService
	public int delete(int empid){
		return dbClass.delete(empid);
	}

	public Emp getEmployee(int empid) {
		return dbClass.readData(empid);
	}

	public List<Emp> getAllEmpsWithSameNames(String name) {
		// TODO Auto-generated method stub
		return dbClass.readData(name);
	}
	
	//@LoggingService
	public List<Emp> getAllEmployees() {
		// TODO Auto-generated method stub
		List<Emp> employees=dbClass.readData();		
		return employees;
	}

}
