package com.naga.controller;

import static com.naga.webTestConfiguration.WebTestConfig.exceptionResolver;
import static com.naga.webTestConfiguration.WebTestConfig.fixedLocaleResolver;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assume.assumeNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.naga.model.Emp;
import com.naga.service.ServiceClass;

@ExtendWith(MockitoExtension.class)
@DisplayName("When executing Controller Tests ")
class MyControllerTest {
	@Mock 
	ServiceClass service;
	
	@InjectMocks   
	MyController controller;
	
	MockMvc mockMvc;

	@BeforeEach
	public void setUp()
	{
		mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setHandlerExceptionResolvers(exceptionResolver())
                .setLocaleResolver(fixedLocaleResolver())
//                .setViewResolvers(jspViewResolver())
                .build();
	}
	
	@Test
	void isMockMvcObjectCreatedTest() throws IOException, SQLException
	{
		assumeNotNull(mockMvc);
		assumeNotNull(service);
	}
	
	@Test
	public void redirectToFormTest() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/new"))
			.andExpect(status().isOk()).andExpect(view().name("employee-form"));
	}
	

	@Test
	public void listOfEmpsTest() throws Exception
	{
		
		List<Emp> employees=new ArrayList<>();
		when(service.getAllEmployees()).thenReturn(employees);
		mockMvc.perform(MockMvcRequestBuilders.get("/list"))
			.andExpect(status().isOk()).andExpect(view().name("employee-list"));
	}
	
	@Nested
	@DisplayName("When Executing Insertion Tests")
	class InsertionTestingClass{
		Emp emp;
		@BeforeEach
		public void beforeAll() throws SQLException
		{
			emp=new Emp(101,"srinu","vasu",345,504,"gpl");
		}
		@Nested
		@DisplayName("When Executing Positive Tests")
		class PositiveTests{
			
			@BeforeEach
			public void beforeInsertTest() throws SQLException
			{
				when(service.insert(any(Emp.class))).thenReturn(1);
			}
			@Test
			@DisplayName("Should return the HTTP status code found (302)")
			public void insertionTest1() throws Exception
			{
				mockMvc.perform(
						post("/insert")
						.param("empid",emp.getEmpid()+"")
						.param("firstName",emp.getFirstName())
						.param("lastName",emp.getLastName())
						.param("salary",emp.getSalary()+"")
						.param("addrid",emp.getAddrid()+"")
						.param("address",emp.getAddress()))
				.andExpect(status().isFound());
			}
			
			@Test
			@DisplayName("Should redirect the HTTP request to the 'redirect:/list' and return the correct Model Object 'emp'")
			public void insertionTest2() throws Exception
			{
				mockMvc.perform(
						post("/insert")
						.param("empid",emp.getEmpid()+"")
						.param("firstName",emp.getFirstName())
						.param("lastName",emp.getLastName())
						.param("salary",emp.getSalary()+"")
						.param("addrid",emp.getAddrid()+"")
						.param("address",emp.getAddress()))
				.andExpect(view().name("redirect:/list"));
			}
			
			@Test
			@Disabled	//insert method doesnt send any model object
			@DisplayName("Should Return the correct Model Object 'emp'")
			public void insertionTest3() throws Exception
			{
				mockMvc.perform(
						post("/insert")
						.param("empid",emp.getEmpid()+"")
						.param("firstName",emp.getFirstName())
						.param("lastName",emp.getLastName())
						.param("salary",emp.getSalary()+"")
						.param("addrid",emp.getAddrid()+"")
						.param("address",emp.getAddress()))
				.andExpect(model().attribute(
                        "emp", 
                        hasItem(allOf(
                                hasProperty("empid", equalTo(101)),
                                hasProperty("firstName", equalTo("srinu")),
                                hasProperty("lastName",equalTo("vasu"))
                               ))
                        ));
			}
			
		}
	
		@Nested
		@DisplayName("When Executing Negitive Tests(404)")
		class NegitiveTests{
			@BeforeEach
			public void before() throws SQLException
			{
				when(service.insert(any(Emp.class))).thenThrow(new RuntimeException());
			}
			
			@Test
			@DisplayName("Should return Internal server error(500)")
			public void negInsertionTest1() throws Exception
			{
				mockMvc.perform(
						post("/insert")
						.param("empid",emp.getEmpid()+"")
						.param("firstName",emp.getFirstName())
						.param("lastName",emp.getLastName())
						.param("salary",emp.getSalary()+"")
						.param("addrid",emp.getAddrid()+"")
						.param("address",emp.getAddress()))
				.andExpect(status().is5xxServerError())
				.andExpect(status().is(500));
			}
		}
	}

	@Nested
	@DisplayName("When Executing deletion tests")
	class DeletionTestingClass{
		
		@Test
		@DisplayName("Deletion successfull. Should return home page with zero model arguments")
		public void deleteTest1() throws Exception
		{
			int id=101;
			when(service.delete(id)).thenReturn(1);
			mockMvc.perform(
					get("/delete")
					.param("id",id+""))
					.andExpect(status().isOk())
					.andExpect(view().name("home"))
					.andExpect(model().size(0));
		}
		@Test
		@DisplayName("Deletion failed. Should return error page with 'msg' argument")
		public void deleteTest2() throws Exception
		{
			int id=101;
			when(service.delete(id)).thenReturn(0);
			mockMvc.perform(
					get("/delete")
					.param("id",id+""))
					.andExpect(status().isOk())
					.andExpect(view().name("error"))
					.andExpect(model().attribute("msg",equalTo("Deletion failed!!!")))
					.andExpect(model().size(1));
		}
		
		@Test
		@DisplayName("should return bad request error")
		public void deletetest3() throws Exception
		{
			mockMvc.perform(
					get("/delete"))
					.andExpect(status().is(400))
					.andExpect(status().isBadRequest());
		}
		
		@Test
		@DisplayName("Should return Internal server error")
		public void deleteTest4() throws Exception
		{
			int id=101;
			when(service.delete(id)).thenThrow(new RuntimeException());
			mockMvc.perform(
					get("/delete")
					.param("id",id+""))
					.andExpect(status().is5xxServerError());
		}
	}

	@Nested
	@DisplayName("When executing tests on form redirection with Emp")
	class redirectToFormWithEmpTestingClass{
		Emp emp;
		@BeforeEach
		public void beforeAll()
		{
			emp=new Emp(101,"naga","srinu",34575,504,"gpl");
		}
		@Nested
		@DisplayName("When Executing Positive Tests")
		class PositiveTests{
			
			@BeforeEach
			public void beforeInsertTest() throws SQLException
			{
				when(service.getEmployee(anyInt())).thenReturn(emp);
			}
			
			@Test
			@DisplayName("Should return the HTTP status code found (200)")
			public void redirectionTest1() throws Exception
			{
				mockMvc.perform(
						post("/edit")
						.param("id",emp.getEmpid()+""))
				.andExpect(status().isOk());
			}
			
			@Test
			@DisplayName("Should return correct model object along with view")
			public void redirectionTest2() throws Exception
			{
				mockMvc.perform(
						post("/edit")
						.param("id",emp.getEmpid()+""))
				.andExpect(status().isOk())
				.andExpect(view().name("employee-form"))
				.andExpect(model().attribute("emp",
						allOf(
								hasProperty("empid",equalTo(101)),
								hasProperty("firstName",equalTo("naga")),
								hasProperty("lastName",equalTo("srinu")),
								hasProperty("salary",equalTo(34575.0f)),
								hasProperty("addrid",equalTo(504)),
								hasProperty("address",equalTo("gpl"))
								)
						))
				.andExpect(model().size(1));
			}
		}
		
		@Nested
		class NegitiveTests{
			@Test
			@DisplayName("Should return the Bad request Error(400)")
			public void redirectionTest1() throws Exception
			{
				mockMvc.perform(
						post("/edit"))
				.andExpect(status().is4xxClientError());
			}
			
			@Test
			@DisplayName("Should return Runtime Exception(500)")
			public void redirectionTest2() throws Exception
			{
				when(service.getEmployee(101)).thenThrow(new RuntimeException());
				mockMvc.perform(
						post("/edit")
						.param("id",101+""))
				.andExpect(status().is5xxServerError());
				verify(service,times(1)).getEmployee(101);
			}
			
			@Test
			@DisplayName("Should return page not found error(404)")
			public void redirectionTest3() throws Exception
			{
				mockMvc.perform(
						post("/editee")
						.param("id",101+""))
				.andExpect(status().isNotFound());
			}
		}
	}

	@Nested
	@DisplayName("When executing tests on retrieving employee records with same names")
	class readEmployeeNamesTestingClass{
		List<Emp> employees=new ArrayList<>();
		private final String NAME="naga";
		private final int ACTION_STATE=1;
		@BeforeEach
		public void beforeAll()
		{
			employees.add(new Emp(101,"naga","srinu",34575,504,"gpl"));
		}
		@Nested
		@DisplayName("When Executing Positive Tests")
		class PositiveTests{
			
			@BeforeEach
			public void beforeTest() throws SQLException
			{
				when(service.getAllEmpsWithSameNames(NAME)).thenReturn(employees);
			}
			
			@Test
			@DisplayName("Should return the HTTP status code found (200)")
			public void readDataTest1() throws Exception
			{
				mockMvc.perform(
						post("/read-employees-with-similar-names")
						.param("name",NAME)
						.param("actionState",ACTION_STATE+""))
				.andExpect(status().isOk());
			}
			
			@Test
			@DisplayName("Should return correct model object along with view")
			public void readDataTest2() throws Exception
			{
				mockMvc.perform(
						post("/read-employees-with-similar-names")
						.param("name",NAME)
						.param("actionState",ACTION_STATE+""))
				.andExpect(status().isOk())
				.andExpect(view().name("emp-names"))
				.andExpect(model().attribute("employees",
						hasItem(
						allOf(
								hasProperty("empid",equalTo(101)),
								hasProperty("firstName",equalTo("naga")),
								hasProperty("lastName",equalTo("srinu")),
								hasProperty("salary",equalTo(34575.0f)),
								hasProperty("addrid",equalTo(504)),
								hasProperty("address",equalTo("gpl"))
								)
						)))
				.andExpect(model().attribute("length",1))
				.andExpect(model().attribute("action",1))
				.andExpect(model().size(3));
			}
		}
		
		@Nested
		class NegitiveTests{
			@Test
			@DisplayName("Should return the Bad request Error(400)")
			public void readDataTest1() throws Exception
			{
				mockMvc.perform(
						post("/read-employees-with-similar-names"))
				.andExpect(status().is4xxClientError());
			}
			@Test
			@DisplayName("Should return the Bad request Error(400) because of incorrect MockMvcRequestBuilder's method(given get instead of post)")
			public void readDataTest2() throws Exception
			{
				mockMvc.perform(
						get("/read-employees-with-similar-names")
						.param("name",NAME)
						.param("actionState",ACTION_STATE+""))
				.andExpect(status().is4xxClientError());
			}
		}
	}

	@Nested
	@DisplayName("When executing login tests")
	class LoginTests{
		@Test
		@DisplayName("When DB Connection failed")
		public void loginTest1() throws Exception
		{
			when(service.connect()).thenReturn(false);
			mockMvc.perform(post("/login")
					.param("admin","naga")
					.param("pass","123"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(view().name("index"))
				.andExpect(model().size(1))
				.andExpect(model().attribute("msg",0));
		}
		
		@Test
		@DisplayName("When DB Connection successfull and login details are also correct.")
		public void loginTest2() throws Exception
		{
			when(service.connect()).thenReturn(true);
			mockMvc.perform(post("/login")
					.param("admin","naga")
					.param("pass","123"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(view().name("home"))
				.andExpect(model().size(0));
		}
		
		@Test
		@DisplayName("When DB Connection successfull but login details are incorrect.")
		public void loginTest3() throws Exception
		{
			when(service.connect()).thenReturn(true);
			mockMvc.perform(post("/login")
					.param("admin","srinu")
					.param("pass","123"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(view().name("index"))
				.andExpect(model().size(1))
				.andExpect(model().attribute("msg",1));
		}
	}
	
	@Nested
	@DisplayName("When executing Logout Tests")
	class LogoutTests{
		@Test
		@DisplayName("When logging out..")
		public void logoutTest1() throws Exception
		{
			mockMvc.perform(get("/logout"))
			.andExpect(view().name("index"));
		}
	}
	//NOTE: DONT FORGET TO ADD VERIFICATION METHODS TO ALL TESTS.
}
