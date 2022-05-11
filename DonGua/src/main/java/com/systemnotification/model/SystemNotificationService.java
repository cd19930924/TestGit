package com.systemnotification.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemNotificationService {
	@Autowired
	SystemNotificationDAO dao;

//	public SystemNotificationService() {
//		dao = new SystemNotificationJDBCDAO();
//	}

	// 存入通知(通知者ID, 通知種類編號)
	public void saveNotification(Integer notifyId, Integer orderId, String no) {
		SystemNotificationVO vo = new SystemNotificationVO();

		vo.setMemId(notifyId);
		vo.setNotTypeNo(no);
		vo.setOrderId(orderId);

		dao.insert(vo);
	}

	// 取得通知(會員ID)
	public List<SystemNotificationPVO> getNotificationList(Integer id) {
		List<SystemNotificationPVO> list = dao.selectAll(id);
		
		for(SystemNotificationPVO pvo : list) {
			String text = pvo.getNotTextcontent();
			int index = text.indexOf("$");
			
			if (index != -1) {
				StringBuffer rs = new StringBuffer(text.replace("$", ""));
				rs = rs.insert(index, pvo.getOrderId().toString());
				System.out.println(rs.toString());
				pvo.setNotTextcontent(rs.toString());
			}
		}
		
		return list;
	}
	
	public static void main(String[] args) {
	}

}
