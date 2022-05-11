package com.common.model.service;


import com.common.model.dao.impl.MemberDataDAOImpl;
import com.common.model.vo.MemberDataListVO;

public class MemberDataService {
	public MemberDataListVO getOneMember(Long memberId) {/*用於取得當前戶會員資料*/
		MemberDataDAOImpl memberData = new MemberDataDAOImpl();
		MemberDataListVO memberList = new MemberDataListVO();
		memberList.setMemberList(memberData.getOneMember(memberId));		
		return memberList;
	}
	public MemberDataListVO getServerlMember() {/*用於取得1~N筆會員資料*/
		MemberDataDAOImpl memberData = new MemberDataDAOImpl();
		MemberDataListVO memberList = new MemberDataListVO();
		memberList.setMemberList(memberData.getAllMember());		
		
		return memberList;
	}
	public MemberDataListVO getAllMember() {/*用於取得全部會員資料*/
		MemberDataDAOImpl memberData = new MemberDataDAOImpl();
		MemberDataListVO memberList = new MemberDataListVO();
		memberList.setMemberList(memberData.getAllMember());		
		return memberList;
	}
}
