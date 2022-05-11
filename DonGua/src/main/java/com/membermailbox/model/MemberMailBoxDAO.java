package com.membermailbox.model;

import java.util.List;

public interface MemberMailBoxDAO {
	public List<MemberMailBoxVO> getMailByReceiveMemId(Integer receiveMemId); // 依收件者ID 瀏覽所有信件
	
	public void insert(MemberMailBoxVO memberMailBoxVO); // 新增信件(寄信)
	
	public void update(Integer mailId, String status ); // 修改信件狀態
	
}
