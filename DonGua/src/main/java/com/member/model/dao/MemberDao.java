package com.member.model.dao;

import java.util.List;

import com.member.model.vo.MemberDataSettingVO;
import com.member.model.vo.MemberRegistVO;
import com.member.model.vo.MemberVO;

public interface MemberDao {
	public long isMemberExist(String memberAccount,String password) throws ClassNotFoundException;
	public boolean isMemberIdExist(long memberId) throws ClassNotFoundException;
	public boolean activeMemberStatus(String memberAccount) throws ClassNotFoundException;
	public boolean isMemberAcctExist(String memberAccount) throws ClassNotFoundException;
	public int insertMember(MemberRegistVO registVO) throws ClassNotFoundException;
	public MemberVO getMemberData(long memberId) throws ClassNotFoundException;
	public List<MemberVO> getAllMember() throws ClassNotFoundException;
	int updateMember(MemberDataSettingVO memberData);
	MemberDataSettingVO getMemberDataByToken(long memberId) throws ClassNotFoundException;
}
