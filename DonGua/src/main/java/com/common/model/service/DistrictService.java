package com.common.model.service;

import java.util.List;
import java.util.stream.Collectors;

import com.common.model.dao.impl.DistrictDAOImpl;
import com.common.model.vo.DistrictVO;
import com.common.model.vo.SelectOptionVO;
import com.common.model.vo.SelectOptionVO.Option;

public class DistrictService {
	public SelectOptionVO getCountySelectOption(String countyNo) {
		DistrictDAOImpl dao = new DistrictDAOImpl();
		List<DistrictVO> districtList =  dao.getDistrictList(countyNo);
		
		SelectOptionVO svo = new SelectOptionVO();
		List<Option> list = districtList.stream()//stream-->轉為物件流
				.map(district -> {//map-->轉換
					return new Option(district.getDistNO(),district.getDistName());//個別把district轉為Option
				}).collect(Collectors.toList());//打包成為一個列表
		svo.setCategory("district");
		svo.setList(list);
		System.out.println(svo.getList().toString());
		return svo;
	}
}
