package com.carermemapply.model;

import java.util.List;

public interface CarerMemApplyDAO {

		// 新增申請者的照護資料
		public void insertApplyData(CarerMemApplyVO carerMemApplyVO);
		
		// 查詢
		public List<CarerMemApplyVO> getAll();
		
	}
