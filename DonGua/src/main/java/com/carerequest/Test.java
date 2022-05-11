package com.carerequest;

import com.carerequest.model.*;

public class Test {

	public static void main(String[] args) {
		CareRequestService svc = new CareRequestService();
		CareRequestDAO dao = new CareRequestDAOImpl();

//		AAA();

		svc.getRequestList(26);
//		svc.cancelRequest(1259);
//		dao.updateStatus(1259, 0);
//		dao.selectAll(3344);

	}

}
