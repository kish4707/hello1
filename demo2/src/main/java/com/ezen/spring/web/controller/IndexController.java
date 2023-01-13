package com.ezen.spring.web.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.spring.web.service.GuguService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class IndexController 
{
	@Autowired
	private GuguService svc;
	
	@GetMapping("/")
	public String index()
	{
		return "index";
	}
	
	@GetMapping("/gugu")
	public String gugu(HttpServletRequest request)
	{
		String sDan = request.getParameter("dan");
		
		int dan=2;
		if(sDan != null)
		{
			dan = Integer.parseInt(sDan);
		}
		request.setAttribute("dan", dan);
		
		return "gugu";
	}
	
	@GetMapping("/gugu2")
	public String gugu2(@RequestParam(value="dan", defaultValue="2") int dan, Model m)	//파라미터 dan을 받아서 int dan에 넣는다 //default값은 2이다
	{
		m.addAttribute("list", svc.createGugu(dan));
		
		return "gugu";
	}
	
	@GetMapping("/gugu/{dan}")
	public String gugu3(@PathVariable int dan, Model m)		//(@PathVariable(name=("dan")) int dan, Model m)
	{
		m.addAttribute("list", svc.createGugu(dan));
		
		return "gugu";
	}
	
	@GetMapping({"/gugu4", "/gugu4/{dan}"})
	public String gugu4(@PathVariable("dan") Optional<Integer> opt, Model m)
	{
		int dan = 2;
		if(opt.isPresent())
		{
			dan = opt.get();
		}
		m.addAttribute("list", svc.createGugu(dan));
		
		return "gugu";
	}
	
	@GetMapping("/add/{a}/{b}")
	@ResponseBody
	public Map<String,String> add(@PathVariable("a") int a, @PathVariable("b") int b)
	{
		String msg = String.format("%d + %d = %d",a,b,a+b);
		Map<String,String> map = new HashMap<>();
		map.put("msg", msg);
		return map;
	}
	
	@GetMapping("/mul/{a}/{b}")
	@ResponseBody
	public Map<String,String> mul(@PathVariable Map<String, String> map)
	{
		int a = Integer.parseInt(map.get("a"));
		int b = Integer.parseInt(map.get("b"));
		
		String msg = String.format("%d + %d = %d",a,b,a*b);
		Map<String,String> map2 = new HashMap<>();
		map2.put("msg", msg);
		return map2;
	}
}
