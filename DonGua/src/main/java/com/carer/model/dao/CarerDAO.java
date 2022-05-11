package com.carer.model.dao;

public interface CarerDAO {
	long findCarerByAcctAndPwd(String account,String password);
	boolean isCarerExist(String account);
}
