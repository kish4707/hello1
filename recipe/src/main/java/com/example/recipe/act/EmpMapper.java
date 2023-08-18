package com.example.recipe.act;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.util.MultiValueMap;

import com.example.recipe.act.FoodVO;

@Mapper
public interface EmpMapper
{
	public List<FoodVO> list();
	
	public List<FoodVO> listByDeptno(int deptno);
	
	public int deleteByEmpno(FoodVO emp);
	
	public int updateByDeptno(Map map);
	
	public List<Map<String, Object>> getListWithDname();
	
	public List<FoodVO> search(Map map);
	
	public int addBoard(Map map);
}

