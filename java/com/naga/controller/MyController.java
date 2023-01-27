package com.naga.controller;

import java.io.IOException;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	public MyController()
	{
		log.info("Controller class created.");
	}
	
	 
	
	@RequestMapping("/new")
	private String redirectToForm() throws IOException
	{
		log.info("Redirecting to employee-form.");
		return "employee-form";
	}
	
	@RequestMapping("/insert")
	public ModelAndView insertIntoTable(Emp emp) throws IOException, SQLException 
	{
//		service.insert(request);
//		response.sendRedirect("list");
		service.insert(emp);
		return service.getAllEmployees();
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
		return service.delete(id);
	}
	
	@RequestMapping("/edit")
	private ModelAndView redirectToFormWithEmp(@RequestParam int id) throws ServletException, IOException 
	{
		return service.getEmployee(id);
	}
	
	@RequestMapping("/temp")
	private ModelAndView reDirectToEmpidForm(@RequestParam int action) throws ServletException, IOException {
		 return service.setActionToEmpIdForm(action);
	}
	
	@PostMapping("/read-employees-with-similar-names")
	private ModelAndView readEmployeeNames(@RequestParam String name,@RequestParam int actionState) throws ServletException, IOException 
	{
		return service.getAllEmpsWithSameNames(name,actionState);
	}
	
	@RequestMapping("/list")
	private ModelAndView list() throws ServletException, IOException, SQLException 
	{
		return service.getAllEmployees();
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
