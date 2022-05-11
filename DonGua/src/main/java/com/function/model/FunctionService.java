package com.function.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FunctionService {
	
	@Autowired
	private FunctionJDBCDAO dao;
		
	public List<FunctionVO> getAllFunction(){
		return dao.getAll();
	}
}
