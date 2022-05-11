package com.member.model.service;


import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carer.model.dao.CarerDAO;
import com.carer.model.dao.impl.CarerDAOImpl;
import com.google.gson.Gson;
import com.mail.model.service.SendRegistMailService;
import com.member.model.dao.MemberDao;
import com.member.model.dao.impl.MemberDaoImpl;
import com.member.model.vo.MemberRegistVO;

@Service
public class MemberRegistService {
	@Autowired
	private MemberDao dao;
	@Autowired
	private CarerDAO carerDao;
	public int insertMemberRegist(String RegistJson) {
//		MemberDaoImpl dao = new MemberDaoImpl();
//		CarerDAOImpl carerDao = new CarerDAOImpl();
		Gson gson = new Gson();
		MemberRegistVO memberRegist = new MemberRegistVO();
	    memberRegist = gson.fromJson(RegistJson, MemberRegistVO.class);/*將JSON字串塞入memberRegist物件*/

	    String memAcct = memberRegist.getMemAcct();
	    String regex = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
	    if(memAcct== null || memAcct.length()>30 || !(memberRegist.getMemEmail().trim().matches(regex) || memberRegist.getMemEmail().trim()=="")) {
	    	return 2;
	    }
		if (memAcct != null) {
			try {
				boolean isMemberExist = dao.isMemberAcctExist(memAcct);
				boolean isCarerExist = carerDao.isCarerExist(memAcct);
				if (isMemberExist || isCarerExist) {
					return 0;/*表示會員帳號重複*/
				} else {
					dao.insertMember(memberRegist);
					
					new Thread(()->{
						SendRegistMailService srms = new SendRegistMailService();
						MemberRegistVO memberRegist1 = new MemberRegistVO();
					    memberRegist1 = gson.fromJson(RegistJson, MemberRegistVO.class);
						srms.sendEmail(memberRegist1);
					}).start();
					
					return 1;/*表示會員資料新增成功*/
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;/*表示會員註冊資料新增失敗*/
	}
	public void activeMember(String memAcct) {
		MemberDaoImpl dao = new MemberDaoImpl();
		CarerDAOImpl carerDao = new CarerDAOImpl();
		if (memAcct != null) {
			try {
				boolean isMemberExist = dao.isMemberAcctExist(memAcct);
				boolean isCarerExist = carerDao.isCarerExist(memAcct);
				if (isMemberExist || isCarerExist) {
					dao.activeMemberStatus(memAcct);
				} else {
					return;
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
