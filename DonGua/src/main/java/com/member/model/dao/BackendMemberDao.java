package com.member.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.member.model.vo.MemberDataManageVO;

public interface BackendMemberDao {
	List<MemberDataManageVO> getAllMember() throws SQLException;
	int updateMemberStatus(String status,long id);
}
