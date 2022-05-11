package com.common.model.dao;

import java.util.List;

import com.member.model.vo.MemberVO;

public interface MemberDataDAO {
	List<MemberVO> getOneMember(Long memberId);
	List<MemberVO> getSeveralMember();
	List<MemberVO> getAllMember();
}
