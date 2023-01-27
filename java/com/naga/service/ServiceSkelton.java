package com.naga.service;

import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ServiceSkelton
{
	public boolean connect();
	public void insert(HttpServletRequest request, HttpServletResponse response);
	public void update(HttpServletRequest request, HttpServletResponse response);
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response);
	public ModelAndView getEmployee(HttpServletRequest request, HttpServletResponse response);
	public ModelAndView setActionToEmpIdForm(HttpServletRequest request, HttpServletResponse response);
	public ModelAndView getAllEmpsWithSameNames(HttpServletRequest request, HttpServletResponse response);
	public ModelAndView getAllEmployees(HttpServletRequest request, HttpServletResponse response);
}
