package com.example.recipe.act;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.recipe.act.EmpMapper;
import com.example.recipe.act.Emp;


@Controller
@RequestMapping("/mybatis/emp")
public class MybatisEmpController 
{
	@Autowired
	private EmpMapper dao;
}
