package com.naga.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import com.naga.dao.DaoSkelton;

@ExtendWith(MockitoExtension.class)
class ServiceClassTest {

	@Mock
	DaoSkelton dbClass;
	
	@Mock
	ModelAndView model;
	
	@InjectMocks
	ServiceClass service;
	
	@Test
	void connectionTest()
	{
		when(dbClass.connect()).thenReturn(true);
		assertEquals(true,service.connect());
		verify(dbClass).connect();
	}
	
	@Nested
	class DeletionTests{
		@Test
		void deleteTest()
		{
			//when(mock.get(any(ModelAndView.class))).thenReturn(model);
			when(dbClass.delete(101)).thenReturn(1);
			assertEquals(model,service.delete(101));
			verify(dbClass).delete(101);
			//verify(model).setViewName("home");
			
		}
	}

}
