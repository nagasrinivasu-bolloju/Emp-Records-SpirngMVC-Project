package com.naga.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.naga.model.Emp;

@Component
public class DaoClass implements DaoSkelton
{
//	@Autowired
//	private DataSource datasource;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	public DaoClass(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate=jdbcTemplate;
	}
	public DaoClass() {
		
	}
	
	static final class ClassMapper implements RowMapper<Emp>
	{

		public Emp mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Emp emp=new Emp();
			emp.setEmpid(rs.getInt(1));
    		emp.setFirstName(rs.getString(2));
    		emp.setLastName(rs.getString(3)); 
    		emp.setAddrid(rs.getInt(4));
    		emp.setSalary(rs.getFloat(5));
    		emp.setAddress(rs.getString(7));
			return emp;
		}
	}
	
	public boolean connect()
	{
		//return datasource!=null && jdbcTemplate!=null;
		return jdbcTemplate!=null;
	}
	public int insert(Emp emp)
	{
		//log.info("inserting into employees.");
		int rows=0;
		if(!isAddressFound(emp.getAddrid()) && !addToAddress(emp.getAddrid(),emp.getAddress()))	 //if addrid not present in addresstable or addtoaddress() fails then return false.
			return 0; //unable to insert
		
		String sql="insert into employee(firstName,lastName,addrid,salary) values(?,?,?,?);";
		rows=jdbcTemplate.update(sql,new Object[] {emp.getFirstName(),emp.getLastName(),emp.getAddrid(),emp.getSalary()});
		return rows;
	}
	@SuppressWarnings("deprecation")
	private boolean addToAddress(int addrId,String address)
    {
    	int rows=0;
		rows=jdbcTemplate.queryForObject("insert into address values(?,?);",new Object[] {addrId,address},Integer.class);
		//rows=jdbcTemplate.queryForObject("insert into address values("+addrId+","+address+");", int.class);
    	if(rows>0)
    		return true;
		return false;
    }
	private boolean isAddressFound(int addrId)
	{
		System.out.println(" isAddressFOund called.");
		String sql="select count(*) from address where addrid="+addrId+";";
		int rows=jdbcTemplate.queryForObject(sql,int.class);
		if(rows>0)
		{
			System.out.println("rows:"+rows);
			return true;
		}
		else
			System.out.println("rows are null");
		return false;
	}
	public int delete(int empId)
	{
		String sql="delete from employee where empid="+empId;
		return jdbcTemplate.update(sql);
	}
	public int update(Emp emp)
	{
		if(!isAddressFound(emp.getAddrid()))
			addToAddress(emp.getAddrid(),emp.getAddress());
		int rows=0;
		String sql="update employee set firstName=?,lastName=?,addrid=?,salary=? where empid=?";
		Object[] objects=new Object[] {emp.getFirstName(),emp.getLastName(),emp.getAddrid(),emp.getSalary(),emp.getEmpid()};
		rows=jdbcTemplate.update(sql,objects);
		return rows;
	}
	public List<Emp> readData()
	{
		String sql="select * from employee inner join address on employee.addrId=address.addrId;";
		List<Emp> employees=jdbcTemplate.query(sql, new ClassMapper());
		System.out.println("size of employees: "+employees.size());
		return employees;
	}
	
	public List<Emp> readData(String name)
	{
		System.out.println("In readData method name is:"+name);
    	String sql="select * from employee inner join address on employee.addrId=address.addrId where firstName='"+name+"' collate case_insensitive;";
    	List<Emp> employees=jdbcTemplate.query(sql,new ClassMapper());
    	System.out.println("List of employees"+employees);
    	return employees;
	}

	public Emp readData(int empid)
	{
    	String sql="select * from employee inner join address on employee.addrId=address.addrId where empid="+empid+";";
    	Emp emp=jdbcTemplate.queryForObject(sql,new ClassMapper());
    	return emp;
	}
	
}
