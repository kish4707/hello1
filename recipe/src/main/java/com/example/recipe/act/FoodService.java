package com.example.recipe.act;

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

import com.example.recipe.act.BoardMapper;
import com.example.recipe.act.Attach;
import com.example.recipe.act.Board;
import com.github.pagehelper.PageInfo;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class FoodService 
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
	       
	       System.out.println(mlist.toString());
	       
	       List<Board> list = new ArrayList<>();
	       
	       for(int i = 0; i<mlist.size(); i++)
	       {
	       Map<String, Object> m = mlist.get(i);   // 한행
	       
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
	       if((BigDecimal)m.get("CNT") != null)
	       {
	       big = (BigDecimal)m.get("CNT");
	       board.setCnt(big.intValue());
	       }
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
	       
	       board.getAttList().add(att);   // Board에 Attach 연결
	       if(!found) list.add(board);
	       }
	      return list; 
	   }
	
	public Board boardDetail(Board board)
	{
		List<Map<String, Object>> blist = dao.boardDetail(board);
		List<Board> list = new ArrayList<>();
		Board b = new Board();
		java.math.BigDecimal big = (java.math.BigDecimal)blist.get(0).get("NUM");
		b.setNum(big.intValue());
		b.setTitle((String)blist.get(0).get("TITLE")); 
		b.setAuthor((String)blist.get(0).get("AUTHOR")); 
		b.setContents((String)blist.get(0).get("CONTENTS")); 
		List<Attach> alist = new ArrayList<>();
		
		for(int i = 0; i<blist.size(); i++)
		{
			Map<String, Object> m = blist.get(i);
			Attach a = new Attach();
			big = (java.math.BigDecimal)m.get("FSIZE");
			if(big!=null)
			{
				a.setFsize(big.intValue());
				big = (BigDecimal)m.get("FNUM");
				a.setNum(big.intValue());
				a.setFname((String)m.get("FNAME"));
				alist.add(a);
			}
		}
		b.setAttList(alist);
		
		return b;
	}	
	
	public Map<String,Object> pages(PageInfo pageinfo)
	{
		Map<String,Object> map = new HashMap<>();
		System.out.println();
		int begin = pageinfo.getPageNum()-2;
		
		if(begin<=0)
		{
			begin = 1;
		}
		
		int end = pageinfo.getPageNum()+2;
		if(end>=pageinfo.getNavigateLastPage())
		{
			end = pageinfo.getNavigateLastPage();
		}
		
		map.put("begin", begin);
		map.put("end", end);
		return map;
	}	
	
}
