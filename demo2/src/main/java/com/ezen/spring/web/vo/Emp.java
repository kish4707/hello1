package com.ezen.spring.web.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data //get set
@ToString
@EqualsAndHashCode(exclude = {"ename","deptno","sal","hiredate"})
@AllArgsConstructor
@NoArgsConstructor
public class Emp implements Serializable
{
	private int empno;
	private String ename;
	private int deptno;
	private float sal;
	private java.sql.Date hiredate;
}
