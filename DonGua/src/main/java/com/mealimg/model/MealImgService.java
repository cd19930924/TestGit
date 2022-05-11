package com.mealimg.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealImgService {
	@Autowired
	private MealImgDAO dao;
	
	public List<MealImgVO> getAll(){
		return dao.getAll();
	}

}
