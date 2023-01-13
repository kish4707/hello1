package com.ezen.spring.web.dao;

import java.net.http.HttpRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ezen.spring.web.vo.User;

import jakarta.servlet.http.HttpSession;

@Repository
public class UserDAO 
{
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private HttpRequest request;
	
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
	
	public boolean login(User user) 
	{
		getConn();
		
		String sql = "SELECT * FROM user_table WHERE user_id=? AND user_pwd=?";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getUser_id());
			pstmt.setInt(2, user.getUser_pwd());
			
			rs = pstmt.executeQuery();
			return rs.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
