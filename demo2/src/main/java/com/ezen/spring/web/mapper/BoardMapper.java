package com.ezen.spring.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.spring.web.vo.Attach;
import com.ezen.spring.web.vo.Board;

@Mapper
public interface BoardMapper 
{
	public int saveBoard(Board board);
	
	public int saveAttach(List<Attach> list);
	
	//public List<Board> boardList();
	List<Map<String, Object>> boardList();
	
	public List<Map<String, Object>> boardDetail(Board board);
}