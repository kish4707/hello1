package com.ezen.spring.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ezen.spring.web.service.EmpService;
import com.ezen.spring.web.vo.Emp;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/emp")
@Slf4j
public class EmpController 
{
	
	/*
	-List<Emp> -> Serialize ->파일에 저장
	-출력(메모리 -> 파일) : ObjectOutputStream, FileOutputStream
 	-입력(파일 -> 메모리) : ObjectInputStream, FileInputStream
	 */
	
	
	@Autowired
	private EmpService svc;
	@Autowired
	private MybatisEmpController mec;
	
	
	@GetMapping("/add") 
	public String showAddForm()
	{
		log.info("폼 요청됨");
		/*
		 log.info("폼 요청됨");
		 log.debug(null);
		 log.warn();
		 log.error(null);
		*/
		return "/emp/empAdd";	//사번, 이름, 부서, 급여, 입사일
	}
	
	@PostMapping("/add")
	@ResponseBody
	public Map<String, Object> addEmp(Emp emp, @SessionAttribute(name="user_id", required = false) String userid)
	{
		Map<String, Object> map = svc.logincheck(userid,"added");
		if(map.containsKey("msg")) return map;
		
		boolean added = svc.addEmp(emp);
		map.put("added",added);
		return map;
	}
	
	@GetMapping("/list")
	public String empList(Model m)
	{
		m.addAttribute("list", mec.list());
		return "/emp/empList";	//사번, 이름, 부서, 급여, 입사일
	}

	@GetMapping("/deptnolist/{deptno}")
	public String deptnoList(Emp emp, Model m)
	{
		m.addAttribute("list", svc.getDeptnoList(emp));
		return "/emp/empList";	
	}
	
	@GetMapping("/detail/{empno}")
	public String empDetail(Emp emp, Model m)
	{
		
		Emp detail = svc.getEmp(emp);
		m.addAttribute("emp",detail); //request.setAttribute("emp",detail)
		return "/emp/empDetail";
		
	}
	
	@GetMapping("/edit/{empno}")
	public String empEdit(Emp emp, Model m)
	{
		Emp detail = svc.getEmp(emp);
		m.addAttribute("emp", detail);
		return "/emp/empEdit";
	}
	
	@PostMapping("/update")
	@ResponseBody
	public Map<String, Object> updateEmp(Emp emp, @SessionAttribute(name="user_id", required = false) String userid)
	{
		
		Map<String, Object> map = svc.logincheck(userid,"updated");
		if(map.containsKey("msg")) return map;
		
		boolean updated = svc.updateEmp(emp);
		map.put("updated",updated);
		map.put("empno", emp.getEmpno());
		return map;
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public Map<String, Object> deleteEmp(Emp emp, @SessionAttribute(name="user_id", required = false) String userid)
	{
		
		Map<String, Object> map = svc.logincheck(userid,"deleted");
		if(map.containsKey("msg")) return map;
		
		boolean deleted = mec.deleteByEmpno(emp)>0;
		map.put("deleted",deleted);
		return map;
	}
	
	@GetMapping("/find")
	public String empFind(Model m)
	{
		return "/emp/empFind";	
	}
	
	@PostMapping("/search")
	public String search(@RequestBody MultiValueMap<String, Object> mulmap,Model m)	//요청데이터의 바디 부분을 받겠다 form데이터	//같은 파라미터 이름의 여러값을 동시에 받을 수 있다 ex) deptno=10,20,30
	{
		Map<String, Object> map = mulmap.toSingleValueMap();
		log.info("이름={}", map.get("ename"));
		log.info("부서번호={}", map.get("deptno"));
		
		List<Emp> list = mec.search(map);
		m.addAttribute("list", list);
		
		return "/emp/empList";	
	}
	
	@GetMapping("/upload")
	public String upload()
	{
		return "/Files/upload_form";
	}
}
