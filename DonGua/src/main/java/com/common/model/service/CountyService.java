package com.common.model.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.common.model.dao.impl.CountyDAOImpl;
import com.common.model.vo.CountyVO;
import com.common.model.vo.SelectOptionVO;
import com.common.model.vo.SelectOptionVO.Option;

public class CountyService {
	public SelectOptionVO getCountySelectOption() {
		CountyDAOImpl dao = new CountyDAOImpl();
		List<CountyVO> countyList =  dao.getCountyList();
		SelectOptionVO svo = new SelectOptionVO();
		List<Option> list = countyList.stream()//stream-->轉為物件流
				.map(county -> {//map-->轉換
					return new Option(county.getCtyNo(),county.getCtyName());//個別把County轉為Option
				}).collect(Collectors.toList());//打包成為一個列表
		svo.setCategory("county");
		svo.setList(list);
		System.out.println(svo.getList().toString());
		return svo;
	}
}
