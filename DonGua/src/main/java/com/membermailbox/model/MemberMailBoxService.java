package com.membermailbox.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberMailBoxService {
	
	@Autowired
	private MemberMailBoxDAO dao;
	
	// 依收件者ID 瀏覽所有信件、瀏覽單一信件
	public List<MemberMailBoxVO> getMemberMaillBoxVO(Integer receiveMemId) {
		return dao.getMailByReceiveMemId(receiveMemId);
	}
	
	// 寄信 存入信件(寄件者ID,收件者ID,信件狀態,內文,標題)
	public void sendMail(Integer sendMemId, Integer receiveMemId, String status, String msgContent, String msgTitle) {
		MemberMailBoxVO vo = new MemberMailBoxVO();
		
		vo.setSendMemId(sendMemId);
		vo.setReceiveMemId(receiveMemId);
		vo.setStatus(status);
		vo.setMsgContent(msgContent);
		vo.setMsgTitle(msgTitle);
		dao.insert(vo);
	}
	
	// 修改信件狀態(成"1"已讀)
	public void update(Integer mailId, String status) {
		dao.update(mailId, status);
	}
}

