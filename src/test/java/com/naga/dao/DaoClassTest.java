package com.naga.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.jdbc.core.JdbcTemplate;

import com.naga.dao.DaoClass.ClassMapper;
import com.naga.model.Emp;

@DisplayName("Executing Tests on DaoLayer!!")
@ExtendWith(MockitoExtension.class)
class DaoClassTest {
	@Mock
	JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	DaoClass dao;
	
//	@BeforeEach
//	public void beforeEveryTest()
//	{
//		dao=new DaoClass(jdbcTemplate);
//	}
	
	@Test
	@DisplayName("When reading single employee record")
	public void readSingleEmptest() throws SQLException {
		System.out.println("readSingleEmployeecTest() execution started..");
		int empid=101;
		String sql="select * from employee inner join address on employee.addrId=address.addrId where empid="+empid+";";
		Emp preEmployee=new Emp(101,"Ganesh","Varma",23455.5f,503,"Gollaprolu");
		when(jdbcTemplate.queryForObject(Mockito.anyString(),Mockito.any(ClassMapper.class))).thenReturn(preEmployee);
		assertEquals(preEmployee,dao.readData(101));
		verify(jdbcTemplate).queryForObject(eq(sql),Mockito.any(ClassMapper.class));
	}
	
	@Test
	@DisplayName("When inserting employee record")
	public void insertionTest()
	{
		String sql="select count(*) from address where addrid=503;";
		when(jdbcTemplate.queryForObject(sql,int.class)).thenReturn(1);
		//verify(jdbcTemplate).queryForObject(sql,int.class);
		Emp emp=new Emp(101,"Ganesh","Varma",23056.45f,503,"Gollaprolu");
		String sql2="insert into employee(firstName,lastName,addrid,salary) values(?,?,?,?);";
		when(jdbcTemplate.update(sql2,new Object[] {emp.getFirstName(),emp.getLastName(),emp.getAddrid(),emp.getSalary()})).thenReturn(1);
		assertEquals(1,dao.insert(emp));
		verify(jdbcTemplate).update(sql2,new Object[] {emp.getFirstName(),emp.getLastName(),emp.getAddrid(),emp.getSalary()});
	}
	
	@Test
	@DisplayName("When updating employee record")
	public void updationTest()
	{
		String sql="select count(*) from address where addrid=505;";
		when(jdbcTemplate.queryForObject(sql,int.class)).thenReturn(1);
		//verify(jdbcTemplate).queryForObject(sql,int.class);
		Emp emp=new Emp(101,"Ganesh","varma",23056.45f,505,"Ananthapur");
		String sql2="update employee set firstName=?,lastName=?,addrid=?,salary=? where empid=?";
		when(jdbcTemplate.update(sql2,new Object[] {emp.getFirstName(),emp.getLastName(),emp.getAddrid(),emp.getSalary(),101})).thenReturn(1);
		assertEquals(1,dao.update(emp));
		verify(jdbcTemplate,times(1)).update(sql2,new Object[] {emp.getFirstName(),emp.getLastName(),emp.getAddrid(),emp.getSalary(),emp.getEmpid()});
	}
	
	@Test
	@DisplayName("When deleting employee record")
	public void deletionTest()
	{
		when(jdbcTemplate.update(anyString())).thenReturn(1);
		assertEquals(1,dao.delete(101));
		verify(jdbcTemplate,times(1)).update("delete from employee where empid=101");
		
	}
	
	@Test
	@DisplayName("When reading multiple records with same name.")
	public void readMultiRecordsWithNameTest()
	{
		List<Emp> empsWithSimNames=new ArrayList<>();
		String sql="select * from employee inner join address on employee.addrId=address.addrId where firstName="+"'naga'"+" collate case_insensitive;";
		when(jdbcTemplate.query(eq(sql),any(ClassMapper.class))).thenReturn(empsWithSimNames);
		assertEquals(empsWithSimNames,dao.readData("naga"));
		verify(jdbcTemplate).query(eq(sql),any(ClassMapper.class));
	}
	
	@Test
	@DisplayName("When reading all records.m")
	public void readAllRecordsTest()
	{
		List<Emp> empsWithSimNames=new ArrayList<>();
		String sql="select * from employee inner join address on employee.addrId=address.addrId;";
		when(jdbcTemplate.query(eq(sql),any(ClassMapper.class))).thenReturn(empsWithSimNames);
		assertEquals(empsWithSimNames,dao.readData());
		verify(jdbcTemplate).query(eq(sql),any(ClassMapper.class));
	}
}
