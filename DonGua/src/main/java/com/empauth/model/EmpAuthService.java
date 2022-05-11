package com.empauth.model;

public class EmpAuthService {
	private EmpAuthDAO dao;

	public EmpAuthService() {
		dao = new EmpAuthDAOImpl();
	}

	public void editAuthNo(Integer id, String no) {
		EmpAuthVO vo = new EmpAuthVO();

		vo.setEmpId(id);
		vo.setEmpAuthNo(no);

		dao.update(vo);
	}
}
