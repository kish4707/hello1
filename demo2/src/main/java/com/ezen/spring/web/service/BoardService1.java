package com.ezen.spring.web.service;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.spring.web.mapper.BoardMapper;
import com.ezen.spring.web.vo.Attach;
import com.ezen.spring.web.vo.Board;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class BoardService1 
{
	@Autowired
	BoardMapper dao;
	
	@Transactional
	public String addBA(Map map)
	{
		
		MultipartFile[] mfiles = (MultipartFile[]) map.get("mfiles");
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		Board board = (Board) map.get("board");
		
		ServletContext context = request.getServletContext();
	      String savePath = context.getRealPath("/WEB-INF/files");
	      
	      List<Attach> list = new ArrayList<>();
	      
	    	  
	      try 
	      {
	    	  int a = dao.saveBoard(board);
	    	  
	    	  if(mfiles.length != 0)
	    	  {
	    		  
	         for(int i=0;i<mfiles.length;i++) 
	         {
	            mfiles[i].transferTo(
	            new File(savePath+"/"+mfiles[i].getOriginalFilename()));
	            
	            String fname = mfiles[i].getOriginalFilename();
	            long fsize = mfiles[i].getSize();
	            
	            //Attach att = new Attach(fname,fsize);
	            Attach att = new Attach();
	            att.setFname(mfiles[i].getOriginalFilename());
	            att.setFsize(mfiles[i].getSize());
	            
	            list.add(att);
	            /* MultipartFile 주요 메소드
	            String cType = mfiles[i].getContentType();
	            String pName = mfiles[i].getName();
	            Resource res = mfiles[i].getResource();
	            long fSize = mfiles[i].getSize();
	            boolean empty = mfiles[i].isEmpty();
	            */
	         }
	         int b = dao.saveAttach(list);
	         }
	    	  
	         
	         
	         String msg = String.format("보드%d att", a);
	         return msg;
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         return "파일 저장 실패:";
	      }
	}
	
	public List<Board> boardList()
	{
		//dao.boardList();
		
		 
		 // Map 한개에 포함된 게시글 정보, 첨부파일 정보를 Board, Attach에 저장
		 List<Map<String,Object>> mlist = dao.boardList();
		 List<Board> list = new ArrayList<>();
		 
		 for(int i = 0; i<mlist.size(); i++)
		 {
		 Map<String, Object> m = mlist.get(i);	// 한행
		 
		 BigDecimal big = (java.math.BigDecimal) m.get("NUM");
		 Board board = new Board(big.intValue());
		 
		 boolean found = false;
		 
		 if(list.contains(board))
		 {
		 board = list.get(list.indexOf(board));
		 found = true;
		 }
		 board.setTitle((String)m.get("TITLE"));
		 board.setAuthor((String)m.get("AUTHOR"));
		 
		 Object objFname = m.get("FNAME");
		 if(objFname==null) 
		 {
		 	if(!found) list.add(board);
		 	continue;
		 }
		 
		 Attach att = new Attach();
		 att.setFname((String) objFname);
		 big = (BigDecimal)m.get("FNUM");
		 att.setNum(big.intValue());
		 big = (BigDecimal)m.get("FSIZE");
		 att.setFsize(big.intValue());
		 
		 board.getAttList().add(att);	// Board에 Attach 연결
		 if(!found) list.add(board);
		 }
		 
		return list; 
 		
	}
	
	public List<Map<String, Object>> boardDetail(Board board)
	{
		List<Map<String, Object>> blist = dao.boardDetail(board);
		
		List<Map<String, Object>> list = new ArrayList<>();
		
		
		for(int i = 0; i<blist.size(); i++)
		{
			Map<String, Object> m = blist.get(i);
			
			Map<String, Object> map = new HashMap<>();
			
			Attach a = new Attach();
			
			a.setFname((String)m.get("FNAME"));
			
			java.math.BigDecimal big = (java.math.BigDecimal)m.get("FSIZE");
			a.setFsize(big.intValue());
			
			big = (BigDecimal)m.get("NUM");
			a.setNum(big.intValue());
			
			big = (java.math.BigDecimal)m.get("BNUM");
			a.setBnum(big.intValue());
			
			map.put("att", a);
			
			
			Board b = new Board();
			b.setTitle((String)m.get("TITLE"));
			b.setAuthor((String)m.get("AUTHOR"));
			b.setContents((String)m.get("CONTENTS"));
			map.put("board", b);
			
			list.add(map);
		}
		
		return list;
	}
	
}
