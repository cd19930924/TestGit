package com.common.model.service;

import com.common.model.dao.impl.VerifyIdentityDAOImpl;

public class VerifyIdentityService {
	public boolean carerIdentity(String token) {	
		
		long  carerId = JWTokenUtils.getTokenId(token);	/*在token中取得創建此token的ID*/
		VerifyIdentityDAOImpl dao = new VerifyIdentityDAOImpl();
		Boolean isCarerExist = dao.isCarerExist(carerId);/*以ID判斷此照護員是否存在*/
		
		return isCarerExist;
	}
}
