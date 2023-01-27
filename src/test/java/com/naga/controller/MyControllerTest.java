package com.naga.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import com.naga.model.Emp;
import com.naga.service.ServiceClass;

@ExtendWith(MockitoExtension.class)
class MyControllerTest {

	@Mock 
	ServiceClass service;
	@Mock 
	ModelAndView modelAndView;
	@InjectMocks 
	MyController controller;
	
	@Test
	void getAllEmpsTest() throws IOException, SQLException
	{
		when(service.getAllEmployees()).thenReturn(modelAndView);
		assertEquals(modelAndView,controller.insertIntoTable(new Emp()));
	}

}
