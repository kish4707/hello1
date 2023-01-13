package com.ezen.spring.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ezen.spring.web.dao.UserDAO;
import com.ezen.spring.web.vo.User;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService 
{
	@Autowired
	private UserDAO dao;
	
	//@Autowired
	private HttpSession session;

	@Autowired
	public void setSession(HttpSession session)
	{
		this.session = session;
	}
	
	public boolean login(User user)
	{
		boolean login = dao.login(user);
		return login;
	}
	
	
}
 