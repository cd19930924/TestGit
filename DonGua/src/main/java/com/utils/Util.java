package com.utils;

public class Util {
	
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	
	
	public static final String URL = 
			"jdbc:mysql://localhost:3306/CFA104G6?"
//			+ "useSSL=false&"                   
			+ "rewriteBatchedStatements=true&"  
			+ "serverTimezone=Asia/Taipei";   
//			+ "allowPublicKeyRetrieval=true&"   
//			+ "useUnicode=true&"                
//			+ "characterEncoding=utf-8";       
	
	public static final String USER = "root";
	
	public static final String PASSWORD = "123456";
}