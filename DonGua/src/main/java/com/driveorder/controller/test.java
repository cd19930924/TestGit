package com.driveorder.controller;

public class test {
	public static void main(String[] args) {
		String ss = "Heellloo$dddsds";
		
		System.out.println(ss.indexOf("$"));
		
		ss.substring(ss.indexOf("$"),ss.indexOf("$")+1);
		
		StringBuffer s = new StringBuffer(ss);
		
;		s = s.insert(ss.indexOf("$"), "這是我自動插入的");
		
		System.out.print(s.toString());
	}
}
