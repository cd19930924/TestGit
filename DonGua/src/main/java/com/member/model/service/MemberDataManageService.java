package com.member.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.member.model.dao.impl.BackendMemberDaoImpl;
import com.member.model.vo.MemberDataManageVO;
@Service
public class MemberDataManageService {
	private BackendMemberDaoImpl dao = new BackendMemberDaoImpl();
	
	public List<MemberDataManageVO> getAllMember()  {
		List<MemberDataManageVO> memberList = new ArrayList<MemberDataManageVO>(); 
		try {
			memberList = dao.getAllMember();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memberList;
	}
	public int updateMemberStatus(String status,long id) {
		return dao.updateMemberStatus(status,id);
	}
}
