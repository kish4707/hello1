package com.example.recipe.act;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.recipe.act.BoardMapper;
import com.example.recipe.act.FoodService;
import com.example.recipe.act.MybatisEmpController;
import com.example.recipe.act.FoodController;
import com.example.recipe.act.Board;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/food")
@Slf4j
public class FoodController 
{
	@Autowired
	ResourceLoader resourceLoader;
	@Autowired
	private MybatisEmpController mec;
	@Autowired
	private FoodService svc;
	@Autowired
	BoardMapper dao;	
	
	// 나도요리사 메인 페이지
	@GetMapping("/main")
	public String foodMain()
	{
		return "foodMain.jsp";
	}		
	
	// 나도요리사 글작성페이지
	@GetMapping("/board")
	public String foodBoard()
	{
		return "foodBoard.jsp";
	}	
	
	// 나도요리사 글작성페이지
	@PostMapping("/board")
	   public String upload
	   (@RequestParam("files")MultipartFile[] mfiles,
	                     HttpServletRequest request,
	                     Board board) 
	   {
	   Map<String, Object> map = new HashMap<>();
	   map.put("mfiles", mfiles);
	   map.put("request", request);
	   map.put("board", board);
	   svc.addBA(map);
	   return "/foodList.jsp";
	   }
	
	// 나도요리사 글목록페이지
	 @GetMapping("/list/{page}/{row}")
	 public String list(Model model,@PathVariable("page") int page,@PathVariable("row") int row) {
		 
		 PageHelper.startPage(page,row);	
		 PageInfo <Board> pageinfo = new PageInfo<>(svc.boardList());	// List<Board>
		 log.info(pageinfo.toString());

		 model.addAttribute("pageinfo", pageinfo);
		 Map<String,Object> map = new HashMap<>();
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
		
		
		 model.addAttribute("pages", map);
		 return "/board/boardList";
	   }
	 
	// 나도요리사 상세페이지
	@GetMapping("/detail/{num}")
	 public String detail(Board board, Model m) {
		 // List<Map<String, Object>> map = svc.boardDetail(board);
		 Board map = svc.boardDetail(board);
		 m.addAttribute("board", map);
		 return "foodDetail.jsp";
	 }

	 @GetMapping("/download/{filename}")	//		/files/download/파일명
	   public ResponseEntity<Resource> download(HttpServletRequest request, @PathVariable String filename) {
	      Resource resource = resourceLoader.getResource("WEB-INF/files/"+filename);
	      System.out.println("파일명:"+resource.getFilename());
	        String contentType = null;
	        try {
	            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 
	        if(contentType == null) {
	            contentType = "application/octet-stream";
	        }
	 
	        try {
				filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
			} 
	        
	        catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
	        
	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
	   }	 
	 
	 // 나도요리사 레시피메뉴페이지
	@GetMapping("menu")
	public String foodMenu()
	{
		return "foodMenu.jsp";
	}
	
}
