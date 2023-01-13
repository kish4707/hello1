package com.ezen.spring.web.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController	//모든 메소드가 responsebody를 갖는다
@RequestMapping("/test")
public class TestController 
{
   private Connection conn;
   private Statement stmt;
   private ResultSet rs;
   
   
   @GetMapping("/oracle")
   public void test()
   {
      try {
         Class.forName("oracle.jdbc.OracleDriver");
         conn = DriverManager.getConnection(
                   "jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM emp2");
         
         
         while(rs.next())
         {
            int empno = rs.getInt("EMPNO");
            String ename = rs.getString("ENAME");
            java.sql.Date hiredate = rs.getDate("HIREDATE");
            float salary = rs.getFloat("SAL");
            
            System.out.printf("%d\t%s\t%s\t%f \n", empno,ename,hiredate,salary);
         }
         
         rs.close();
         stmt.close();
         conn.close();
         
        } catch (Exception e) {
            e.printStackTrace();;
        }
      
      
   }
   
}