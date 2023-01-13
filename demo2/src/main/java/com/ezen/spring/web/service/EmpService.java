package com.ezen.spring.web.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spring.web.dao.EmpDAO;
import com.ezen.spring.web.dao.JdbcTemDAO;
import com.ezen.spring.web.vo.Emp;

import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
public class EmpService
{
	@Autowired
	private EmpDAO dao;
	@Autowired
	private JdbcTemDAO jdao;
	
	private String fpath = "D:/test/emp.ser";
	
	public EmpService() {}
	
	public Map<String, Object> logincheck(String userid, String key)
	{
		log.info("서비스, 로그인 아이디:{}",userid);
		Map<String, Object> map = new HashMap();
		if(userid==null)
		{
			map.put(key, false);
			map.put("msg", "로그인 후에 사용 가능");
			map.put("url", "/user/login");
		}
		return map;
	}
	
	public boolean addEmp(Emp emp)
	{
		
		return dao.addAndGetPK(emp);
	}
	
	public boolean updateEmp(Emp emp)
	{
		return dao.update(emp);
	}
	
	public boolean deleteEmp(Emp emp)
	{
		return dao.delete(emp);
	}
	
	public Emp getEmp(Emp emp)
	{
		return dao.getEmp(emp);
	}
	
	public List<Emp> showlist()
	{
		return dao.getList();
	}
	
	public List<Emp> getDeptnoList(Emp emp)
	{
		return dao.getDeptnoList(emp);
	}
	/*
	public boolean add(Emp emp)
	{
		if(emp!=null || emp.getEmpno()>0)
		{
			List<Emp> list = deserialize();
			list.add(emp);
			return serialize(list);
		}
		return false;
	}
	*/
	
	private boolean serialize(List<Emp> list)
	{
		File f = new File(fpath);
		try
		{
			ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(f));
			out.writeObject(list);
			out.close();
			return true;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return false;
	}
	
	private List<Emp> deserialize()
	{
		File f = new File(fpath);
		List<Emp> list = null;
		if(!f.exists())
		{
			list = new ArrayList<Emp>();
		}
		else
		{
			try
			{
				ObjectInputStream oin = new ObjectInputStream(new FileInputStream(f));
				list = (List<Emp>) oin.readObject();
				oin.close();
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return list;
	}
	
}
