package com.careapply;

import com.careapply.model.*;

public class Test {

	public static void main(String[] args) {
		CareApplyService svc = new CareApplyService();
		CareApplyDAO dao = new CareApplyDAOImpl();

		svc.refuseApply(1234, 1);
		
//		dao.selectAll(1234);
//		dao.deleteAll(1259);
//		dao.delete(1234, 1);
	}

}
