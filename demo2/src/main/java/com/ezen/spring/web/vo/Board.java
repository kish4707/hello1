package com.ezen.spring.web.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Component
@Data //get set
@EqualsAndHashCode(exclude={"title","contents","author","attList"})
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Board implements Serializable
{
	private int num;
	private String title; 
	private String contents; 
	private String author; 
	private int cnt;
	private List<Attach> attList = new ArrayList<>();
	
	public Board(int num)
	{
		this.num = num;
	}
	
}

