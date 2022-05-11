package com.systemnotification.model;

import java.util.List;

public interface SystemNotificationDAO {

	void insert(SystemNotificationVO vo);

	// (會員ID)
	List<SystemNotificationPVO> selectAll(Integer id);
	
}
