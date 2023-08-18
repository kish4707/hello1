package com.example.recipe.act;

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

import com.example.recipe.act.FoodVO;

@Repository
public class FoodDAO 
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

	public List<FoodVO> getList() 
	{
		String sql  = "SELECT * FROM board2";
		List<FoodVO> list = jdbcTemplate.query(sql, (rs,i)->{
		FoodVO fd = new FoodVO();
		fd.setNum(rs.getInt("num"));
		fd.setTitle(rs.getString("title"));
		fd.setContents(rs.getString("contents"));
		fd.setAuthor(rs.getString("author"));
		return fd;
		}
		
		);
		
		return list;
	}
	
	public boolean add(FoodVO fd) 
	{
		String sql 
			= "INSERT INTO board2(num, title, contents, author) VALUES(?,?,?,?)";
		int rows = jdbcTemplate.update
			(sql,fd.getNum(), fd.getTitle(), fd.getContents(), fd.getAuthor());
		return rows > 0;
	}

	public FoodVO getEmp(FoodVO emp) 
	{
		String sql  = "SELECT * FROM board2 WHERE num=?";
		List<FoodVO> list = jdbcTemplate.query(sql, (rs,i)->{
		FoodVO detail = new FoodVO();
		detail.setNum(rs.getInt("num"));
		detail.setTitle(rs.getString("title"));
		detail.setContents(rs.getString("contents"));
		detail.setAuthor(rs.getString("author"));
		return detail;
		}
		,emp.getNum());
		
		return list.get(0);
	}	

	public boolean update(FoodVO fd) 
	{
		String sql  = "UPDATE board2 SET title=?, contents=?, author=? WHERE num=?";
		int rows = jdbcTemplate.update(sql,fd.getTitle(),fd.getContents(),fd.getAuthor());
		return rows > 0;
	}

	public boolean delete(FoodVO fd) 
	{
		String sql  = "DELETE FROM board2 WHERE empno=?";
		int rows = jdbcTemplate.update(sql, fd.getNum());
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
