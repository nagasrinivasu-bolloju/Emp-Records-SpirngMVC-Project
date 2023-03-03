package com.naga.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.naga.model.Emp;
import com.naga.service.ServiceClass;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MyController
{
	private static Logger log=Logger.getLogger(MyController.class);
	
	@Autowired
	ServiceClass service;
	

	public MyController(ServiceClass service)
	{
		log.info("Controller class created.parametrized");
		this.service=service;
	}
	
//	public MyController()
//	{
//		log.info("Controller class created.default");
//	}
	
	 
	
	@RequestMapping("/new")					
	private String redirectToForm() throws IOException
	{
		log.info("Redirecting to employee-form.");
		return "employee-form";
	}
	
	@RequestMapping("/insert")
	public String insertIntoTable(Emp emp) throws IOException, SQLException 
	{
		System.out.println("emp id:"+emp.getEmpid());
		System.out.println("emp name:"+emp.getFirstName());
		int rows=service.insert(emp);
		return "redirect:/list";
	}
	
	@RequestMapping("/update")
	private String updateTable(Emp emp) throws IOException 
	{
		service.update(emp);
		return "home";
	}
	
	@RequestMapping("/delete")
	private ModelAndView deleteEmployee(@RequestParam int id) throws ServletException, IOException 
	{
		ModelAndView mv=new ModelAndView(); //cant be mocked when testing.
		if(service.delete(id)==0)
		{
			
			mv.addObject("msg","Deletion failed!!!");
			mv.setViewName("error");
			return mv;
		}
		mv.setViewName("home");
		return mv;
	}
	
	@RequestMapping("/edit")
	private ModelAndView redirectToFormWithEmp(@RequestParam int id) throws ServletException, IOException 
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("employee-form");
		mv.addObject("emp",service.getEmployee(id));
		return mv;
	}
	
	@RequestMapping("/temp")
	private ModelAndView reDirectToEmpidForm(@RequestParam int action) throws ServletException, IOException {
		 ModelAndView mv=new ModelAndView();
		 mv.setViewName("empid-form");
		 mv.addObject("action",action);
		 return mv;
	}
	
	@PostMapping("/read-employees-with-similar-names")
	private ModelAndView readEmployeeNames(@RequestParam String name,@RequestParam int actionState) throws ServletException, IOException 
	{
		ModelAndView mv=new ModelAndView();
		List<Emp> employees=service.getAllEmpsWithSameNames(name);
		
		mv.setViewName("emp-names");
		mv.addObject("employees",employees);
		mv.addObject("length",employees.size());
		mv.addObject("action",actionState);
		return mv;
	}
	
	@RequestMapping("/list")
	public ModelAndView list() throws ServletException, IOException, SQLException 
	{
		ModelAndView mv=new ModelAndView();
		List<Emp> employees=service.getAllEmployees();
		mv.setViewName("employee-list");
		mv.addObject("employees",employees);
		return mv;
	}
	
	@PostMapping("/login")
	private ModelAndView loginAdmin(HttpServletRequest request) throws IOException
	{
		boolean connection=service.connect();
		String name=request.getParameter("admin");
		String pass=request.getParameter("pass");
		ModelAndView mv=new ModelAndView();
		if(!connection)
		{
			mv.setViewName("index");
			mv.addObject("msg",0);
		}
		else if(name.equals("naga") && pass.equals("123"))
		{
			HttpSession session=request.getSession();
			session.setAttribute("admin","activeState");
			mv.setViewName("home");
		}
		else
		{
			mv.addObject("msg", 1);
			mv.setViewName("index");
		}
		return mv;
	}
	
	@RequestMapping("/logout")
	private String logoutAdmin(HttpServletRequest request) throws IOException {
		 
		HttpSession session=request.getSession();
		session.removeAttribute("admin");
		session.invalidate();
		return "index";
	}
	@RequestMapping("/")
	private String startMethod()
	{
		return "index";
	}
	@RequestMapping("return-to-home")
	private String returnHome()
	{
		return "home";
	}
}
