package com.ezen.spring.web.dao;

import java.net.http.HttpRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.ezen.spring.web.vo.Emp;

@Repository
public class EmpDAO 
{
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private HttpRequest request;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	private Connection getConn()
	{
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(
	                "jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			this.conn = conn;
			return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}

	public List<Emp> getList() 
	{
		/*
		getConn();
		try {
			String sql = "SELECT * FROM emp2";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<Emp> list = new ArrayList<>();
			while(rs.next())
			{
				Emp emp = new Emp();
				emp.setEmpno(rs.getInt("empno"));
				emp.setDeptno(rs.getInt("deptno"));
				emp.setEname(rs.getString("ename"));
				emp.setSal(rs.getFloat("sal"));
				emp.setHiredate(rs.getDate("hiredate"));
				list.add(emp);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return null;
		*/
		
		String sql  = "SELECT * FROM emp2";
		List<Emp> list = jdbcTemplate.query(sql, (rs,i)->{
		//rs
		Emp emp = new Emp();
		emp.setEmpno(rs.getInt("empno"));
		emp.setDeptno(rs.getInt("deptno"));
		emp.setEname(rs.getString("ename"));
		emp.setSal(rs.getFloat("sal"));
		emp.setHiredate(rs.getDate("hiredate"));
		return emp;
		}
		//i
		);
		
		return list;
	}
	
	public List<Emp> getDeptnoList(Emp deptno) 
	{
		String sql  = "SELECT * FROM emp2 WHERE deptno=?";
		List<Emp> list = jdbcTemplate.query(sql, (rs,i)->{
		//rs
		Emp emp = new Emp();
		emp.setEmpno(rs.getInt("empno"));
		emp.setDeptno(rs.getInt("deptno"));
		emp.setEname(rs.getString("ename"));
		emp.setSal(rs.getFloat("sal"));
		emp.setHiredate(rs.getDate("hiredate"));
		return emp;
		}
		//i
		,deptno.getDeptno());
		
		return list;
	}
	
	public Map<String,Object> getListII(int page)
	{
		getConn();
		try {
			// String sql = "SELECT * FROM get_board CROSS JOIN( SELECT CEIL(COUNT(*)/3) allpage FROM get_board)"+"WHERE page=?";
			String sql = "SELECT * FROM board CROSS JOIN(SELECT CEIL(COUNT(*)/3) allpage FROM board)"+"WHERE page=?";		
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,page);
			rs = pstmt.executeQuery();
			List<Emp> list = new ArrayList<>();
			Map<String,Object> map = new HashMap<>();
			while(rs.next())
			{
				Emp emp = new Emp();
				list.add(emp);
				map.put("page", rs.getString("ALLPAGE"));
			}
			
			map.put("list",list);
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return null;
	}
	
	public List<Integer> getPage()
	{
		getConn();
		
		try {
			String sql = "SELECT DISTINCT page FROM get_board ORDER BY page";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<Integer> list = new ArrayList<>();
			while(rs.next())
			{
				list.add(rs.getInt("page"));
			}
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return null;
	}
	
	public boolean addAndGetPK(Emp emp)
	{
	GeneratedKeyHolder kh = new GeneratedKeyHolder();
	jdbcTemplate.update(
	(conn)->
	{
	PreparedStatement pstmt;
	String sql = "INSERT INTO emp2 (empno, ename, deptno, sal, hiredate) VALUES(?,?,?,?,?)";	//empno의 값이 ?가 아닌 시퀀스(EMP_SEQ_NEXTVAL)일때
	pstmt = conn.prepareStatement(sql, new String[]{"empno"});	 								//자동으로 증가하여 DB에 들어간 empno값을 가져옴
	//empno == primary key의 칼럼명

	pstmt.setInt(1,emp.getEmpno());
	pstmt.setString(2,emp.getEname());
	pstmt.setInt(3,emp.getDeptno());
	pstmt.setFloat(4,emp.getSal());
	pstmt.setDate(5,emp.getHiredate());

	return pstmt;
	}
	,kh);
	//kh = "empno"에 해당하는 값 저장
	int insertedEmpno = kh.getKey().intValue();
	// 두번째 sql에 위의 숫자를 사용하여 FK로 저장할 수 있다
	System.out.println(insertedEmpno);
	return insertedEmpno>0;
	}
	
	public boolean add(Emp emp) 
	{
		/*
		getConn();
		String sql = "INSERT INTO emp2 (empno, ename, deptno, sal, hiredate) " +
					 "VALUES(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp.getEmpno());
			pstmt.setString(2, emp.getEname());
			pstmt.setInt(3, emp.getDeptno());
			pstmt.setFloat(4, emp.getSal());
			pstmt.setDate(5, emp.getHiredate());
			
			int rows = pstmt.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		*/
		
		String sql  = "INSERT INTO emp2(empno, ename, deptno, sal, hiredate) VALUES(?,?,?,?,?)";
		int rows = jdbcTemplate.update(sql,emp.getEmpno(),emp.getEname(),emp.getDeptno(),emp.getSal(),emp.getHiredate());
		return rows > 0;
	}
	

	public Emp getEmp(Emp emp) 
	{
		/*
		getConn();
		try {
			String sql = "SELECT * FROM emp2 WHERE empno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp.getEmpno());
			rs = pstmt.executeQuery();
			
			Emp detail = new Emp();
			if(rs.next())
			{  
				detail.setEmpno(rs.getInt("empno"));
				detail.setDeptno(rs.getInt("deptno"));
				detail.setEname(rs.getString("ename"));
				detail.setSal(rs.getFloat("sal"));
				detail.setHiredate(rs.getDate("hiredate"));
			}

			return detail;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return null;
		*/
		String sql  = "SELECT * FROM emp2 WHERE empno=?";
		List<Emp> list = jdbcTemplate.query(sql, (rs,i)->{
		//rs
		Emp detail = new Emp();
		detail.setEmpno(rs.getInt("empno"));
		detail.setDeptno(rs.getInt("deptno"));
		detail.setEname(rs.getString("ename"));
		detail.setSal(rs.getFloat("sal"));
		detail.setHiredate(rs.getDate("hiredate"));
		return detail;
		}
		//i
		,emp.getEmpno());
		
		return list.get(0);
	}	
	

	public boolean update(Emp emp) 
	{
		/*
		getConn();
		String sql = "UPDATE emp2 SET sal=?, deptno=? WHERE empno=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, emp.getSal());
			pstmt.setInt(2, emp.getDeptno());
			pstmt.setInt(3, emp.getEmpno());
			int rows = pstmt.executeUpdate();
			
			return rows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		*/
		String sql  = "UPDATE emp2 SET sal=?, deptno=? WHERE empno=?";
		int rows = jdbcTemplate.update(sql,emp.getSal(),emp.getDeptno(),emp.getEmpno());
		return rows > 0;
	}


	public boolean delete(Emp emp) 
	{
		/*
		getConn();
		String sql = "DELETE FROM emp2 WHERE empno=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp.getEmpno());
			int rows = pstmt.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		*/
		String sql  = "DELETE FROM emp2 WHERE empno=?";
		int rows = jdbcTemplate.update(sql,emp.getEmpno());
		return rows > 0;
	}
	
	private void closeAll()
	{
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
