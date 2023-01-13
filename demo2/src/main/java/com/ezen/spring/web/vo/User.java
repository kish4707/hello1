package com.ezen.spring.web.vo;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Component
@Data //get set
@ToString
//@EqualsAndHashCode(exclude = {"ename","deptno","sal","hiredate"})
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable
{
	private String user_id;
	private int user_pwd;
}
