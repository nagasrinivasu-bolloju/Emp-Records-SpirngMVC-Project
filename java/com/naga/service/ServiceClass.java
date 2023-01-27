package com.naga.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.naga.aspect.LoggingService;
import com.naga.dao.DaoSkelton;
import com.naga.model.Emp;

@Service
public class ServiceClass
{
	@Autowired
	DaoSkelton dbClass;
	
	@LoggingService
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
	public void insert(Emp emp) throws SQLException {
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
		dbClass.insert(emp);
		
	}
	
	@LoggingService
	public void update(Emp emp)
	{
		dbClass.update(emp);
	}
	
	//ModelAndView mv=new ModelAndView(); can be mocked when testing.
	@LoggingService
	public ModelAndView delete(int empid) {
		ModelAndView mv=new ModelAndView(); //cant be mocked when testing.
		if(dbClass.delete(empid)==0)
		{
			
			mv.addObject("msg","Deletion failed!!!");
			mv.setViewName("error");
			return mv;
		}
		mv.setViewName("home");
		return mv;
	}

	public ModelAndView getEmployee(int empid) {
		System.out.println("empid at edit:"+empid);
		Emp emp=dbClass.readData(empid);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("employee-form");
		mv.addObject("emp", emp);
		return mv;
	}

	public ModelAndView setActionToEmpIdForm(int action) {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("empid-form");
		mv.addObject("action",action);
		return mv;
	}

	public ModelAndView getAllEmpsWithSameNames(String name,int action) {
		// TODO Auto-generated method stub
		List<Emp> employees=dbClass.readData(name);
		System.out.println("printing selected employees through names:");
		
		ModelAndView mv=new ModelAndView();
		mv.setViewName("emp-names");
		mv.addObject("employees",employees);
		mv.addObject("length",employees.size());
		mv.addObject("action",action);
		return mv;
	}
	
	@LoggingService
	public ModelAndView getAllEmployees() {
		// TODO Auto-generated method stub
		List<Emp> employees=dbClass.readData();		
		ModelAndView mv=new ModelAndView();
		mv.setViewName("employee-list");
		mv.addObject("employees",employees);
		return mv;
	}

}
