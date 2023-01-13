package com.ezen.spring.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.util.MultiValueMap;

import com.ezen.spring.web.vo.Emp;

@Mapper
public interface EmpMapper
{
	public List<Emp> list();
	
	public List<Emp> listByDeptno(int deptno);
	
	public int deleteByEmpno(Emp emp);
	
	public int updateByDeptno(Map map);
	
	public List<Map<String, Object>> getListWithDname();
	
	public List<Emp> search(Map map);
	
	public int addBoard(Map map);
}

