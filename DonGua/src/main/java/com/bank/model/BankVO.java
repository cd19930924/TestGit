package com.bank.model;

import java.io.Serializable;

public class BankVO implements Serializable {

	private static final long serialVersionUID = 2225818874603967284L;

	// 銀行
	private String bankCode;
	private String bankName;

	public BankVO() {
		super();
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
}
