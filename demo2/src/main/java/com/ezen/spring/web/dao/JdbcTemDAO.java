package com.ezen.spring.web.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ezen.spring.web.vo.Emp;

@Repository
public class JdbcTemDAO 
{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	List<Emp> getList()
	{
		//List<Emp> list = jdbcTemplate.query(sql, RowMapper);	// select
		//int rows = jdbcTemplate.update();	// insert, update, delete
		
		String sql  = "SELECT * FROM emp2";
		
		List<Emp> list = jdbcTemplate.query(sql, (rs,i)->{
		//rs
		Emp emp = new Emp();
		emp.setEmpno(rs.getInt("EMPNO"));
		emp.setEname(rs.getString("ENAME"));
		return emp;
		}
		//i
		);
		return list;
	}
	
}
