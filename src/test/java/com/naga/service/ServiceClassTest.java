package com.naga.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import com.naga.dao.DaoSkelton;
import com.naga.model.Emp;

@ExtendWith(MockitoExtension.class)
class ServiceClassTest {

	@Mock
	DaoSkelton dbClass;
	
	@InjectMocks
	ServiceClass service;

	@Nested
	@DisplayName("When Checking the DB Connection")
	class COnnectionTests{
		@Test
		@DisplayName("When the connection is successfull")
		void connectionTest1()
		{
			when(dbClass.connect()).thenReturn(true);
			assertEquals(true,service.connect());
			verify(dbClass).connect();
		}
		
		@Test
		@DisplayName("When the connection is failed")
		void connectionTest2()
		{
			when(dbClass.connect()).thenReturn(false);
			assertEquals(false,service.connect());
			verify(dbClass).connect();
		}
	}
	@Nested
	@DisplayName("When executing deletion tests")
	class DeletionTests{
		@Test
		@DisplayName("When deletion successfull")
		void deleteTest1()
		{
			when(dbClass.delete(101)).thenReturn(1);
			assertEquals(1,service.delete(101));
			verify(dbClass).delete(101);
		}
		@Test
		@DisplayName("When deletion failed")
		void deleteTest2()
		{
			when(dbClass.delete(101)).thenReturn(0);
			assertEquals(0,service.delete(101));
			verify(dbClass).delete(101);
		}
		@Test
		@DisplayName("When Exception occured")
		void deleteTest()
		{
			when(dbClass.delete(101)).thenThrow(new RuntimeException());
			assertThrows(RuntimeException.class,()->service.delete(101));
			verify(dbClass).delete(101);
		}
	}
	
	@Nested
	@DisplayName("When executing Insertion tests")
	class InsertionTests{
		Emp emp;
		@BeforeEach
		public void beforeEachTest()
		{
			emp=new Emp();
		}
		@Test
		@DisplayName("When insertion successfull")
		void insertTest1() throws SQLException
		{
			when(dbClass.insert(any(Emp.class))).thenReturn(1);
			assertEquals(1,service.insert(emp));
			verify(dbClass).insert(any(Emp.class));
		}
		@Test
		@DisplayName("When insertion failed")
		void insertTest2() throws SQLException
		{
			when(dbClass.insert(emp)).thenReturn(0);
			assertEquals(0,service.insert(emp));
			verify(dbClass).insert(emp);
		}
		@Test
		@DisplayName("When Exception occured")
		void deleteTest()
		{
			when(dbClass.insert(emp)).thenThrow(new RuntimeException());
			assertThrows(RuntimeException.class,()->service.insert(emp));
			verify(dbClass).insert(emp);
		}
	}

	@Nested
	@DisplayName("When reading single record using empid")
	class GetEmployeeTests{
		Emp emp;
		@BeforeEach
		public void beforeEach()
		{
			emp=new Emp(101,"naga","srinu",34575,504,"gpl");
		}
		@Test
		@DisplayName("When reading single record successfully")
		void readTest1()
		{
			when(dbClass.readData(101)).thenReturn(emp);
			assertEquals(emp,service.getEmployee(101));
			verify(dbClass).readData(101);
		}
		@Test
		@DisplayName("When Exception occured")
		void deleteTest()
		{
			when(dbClass.readData(101)).thenThrow(new RuntimeException());
			assertThrows(RuntimeException.class,()->service.getEmployee(101));
			verify(dbClass).readData(101);
		}
	}
	
	@Nested
	@DisplayName("When reading records with similar names")
	class getAllEmpsWithSameNamesTests{
		List<Emp> employees;
		@BeforeEach
		public void beforeEachTest()
		{
			employees=new ArrayList<>();
		}
		@Test
		@DisplayName("When reading records successfully")
		void readTest1() throws SQLException
		{
			when(dbClass.readData(any(String.class))).thenReturn(employees);
			assertEquals(employees,service.getAllEmpsWithSameNames("naga"));
			verify(dbClass).readData(any(String.class));
		}
		@Test
		@DisplayName("When Exception occured")
		void deleteTest()
		{
			when(dbClass.readData(any(String.class))).thenThrow(new RuntimeException());
			assertThrows(Exception.class,()->service.getAllEmpsWithSameNames("naga"));
			verify(dbClass).readData(any(String.class));
		}
	}
	
	@Nested
	@DisplayName("When reading all records")
	class getAllEmployeesTests{
		List<Emp> employees;
		@BeforeEach
		public void beforeEachTest()
		{
			employees=new ArrayList<>();
		}
		@Test
		@DisplayName("When reading records successfully")
		void readTest1() throws SQLException
		{
			when(dbClass.readData()).thenReturn(employees);
			assertEquals(employees,service.getAllEmployees());
			verify(dbClass).readData();
		}
		@Test
		@DisplayName("When Exception occured")
		void deleteTest()
		{
			when(dbClass.readData()).thenThrow(new RuntimeException());
			assertThrows(Exception.class,()->service.getAllEmployees());
			verify(dbClass,times(1)).readData();
		}
	}
}
