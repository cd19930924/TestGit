package com.requesttab.model;

import java.util.List;

public interface RequestTabDAO {

	// (需求單id)
	List<RequestTabPVO> selectAll(Integer id);

	void insert(RequestTabVO vo);

	// (需求單id)
	void deleteAll(Integer id);

	// 後台用
	List<RequestTabVO> selectAll();

	// 以需求單ID查詢
	public RequestTabVO findByRequestId(Integer requestId);

}
