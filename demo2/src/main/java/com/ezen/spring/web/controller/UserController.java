package com.ezen.spring.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ezen.spring.web.service.UserService;
import com.ezen.spring.web.vo.User;


@SessionAttributes("user_id")
@Controller
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	private UserService svc;
	
	@GetMapping("/login")
	public String LoginForm()
	{
		return "/user/login";
	}
	@PostMapping("/login")
	@ResponseBody
	public Map<String,Boolean> login(User user, Model m)
	{
		Map<String,Boolean> map = new HashMap<>();
		boolean login = svc.login(user);
		if(login)
		{
			m.addAttribute("user_id",user.getUser_id());
		}
		map.put("login", login);
		return map;
	}
	
	@PostMapping("/logout")
	@ResponseBody
	public Map<String,Boolean> logout(SessionStatus se)
	{
		Map<String,Boolean> map = new HashMap<>();
		se.setComplete();
		map.put("logout", true);
		return map;
	}
}
