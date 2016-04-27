package com.github.mobappscourse.mobbank.service.bankops.dao;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import com.github.mobappscourse.mobbank.service.bankops.Account;

@XmlRootElement
public class AccountStore {

	private String name;
	private ArrayList<Account> accountList;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Account> getAccountList() {
		return accountList;
	}
	public void setAccountList(ArrayList<Account> accountList) {
		this.accountList = accountList;
	}
}
