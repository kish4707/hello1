package com.example.recipe.act;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data //get set
@ToString
@EqualsAndHashCode(exclude = {"num","title","contents","author"})
@AllArgsConstructor
@NoArgsConstructor
public class FoodVO implements Serializable
{
	private int num;
	private String title;
	private String contents;
	private String author;
}
