package com.utils;

public class SQLUtils {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = 
			"jdbc:mysql://localhost:3306/CFA104G6?"
//			+ "useSSL=false&"                   // ���ϥΥ[�K�s�u (�ݦ����Ҥ~��)
			+ "rewriteBatchedStatements=true&"  // �妸��s�ݭn����T
			+ "serverTimezone=Asia/Taipei";     // �]�w�ɰϸ�T
	public static final String USER = "root";
	public static final String PASSWORD = "123456";
	
}
