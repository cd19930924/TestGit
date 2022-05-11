package com.common.model.dao;

import java.util.List;

import com.common.model.vo.CountyVO;
import com.common.model.vo.DistrictVO;

public interface DistrictDAO {
	List<DistrictVO> getDistrictList(String category);
	CountyVO getParentCountyAndDistrictName(String distNo);
}
