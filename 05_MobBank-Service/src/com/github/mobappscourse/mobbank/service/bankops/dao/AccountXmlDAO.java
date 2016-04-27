package com.github.mobappscourse.mobbank.service.bankops.dao;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.github.mobappscourse.mobbank.service.bankops.Account;

public class AccountXmlDAO {
	private static final String XML_FILE = "./accountstore.xml";
	private AccountStore accountStore;
	
	public AccountXmlDAO() {
		
		try {
			JAXBContext context = JAXBContext.newInstance(AccountStore.class);
			Unmarshaller um = context.createUnmarshaller();
			accountStore = (AccountStore) um.unmarshal(new FileReader(XML_FILE));
			
		} catch (Exception e) {
			//empty store
			accountStore = new AccountStore();
			accountStore.setName("store");
			accountStore.setAccountList( new ArrayList<Account>() );
		}
	}
	
	private void saveXML() {
		try {
			JAXBContext context = JAXBContext.newInstance(AccountStore.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(accountStore, new File(XML_FILE));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save(Account account) {
		long newNumber = System.currentTimeMillis();
		account.setNumber( newNumber );
		accountStore.getAccountList().add(account);
		saveXML();
	}



	public Account getById(long acNumber) {
		for(Account c : accountStore.getAccountList())
			if(c.getNumber() == acNumber)
				return c;
		return null;
	}

	public String remove(long acNumber) {
		Account c = getById(acNumber);
		if(c == null)
			return "no account";
		
		accountStore.getAccountList().remove(c);
		saveXML();
		return "account removed";
	}

	public String withdraw(long acNumber, double value) {
		Account c = getById(acNumber);
		if(c == null)
			return "no account";
		
		if(value > c.getBalance())
			return "no money";
		
		double newBalance = c.getBalance() - value;
		c.setBalance( newBalance );
		saveXML();
		return "withdraw achieved";
	}

	public String deposit(long acNumber, double value) {
		Account c = getById(acNumber);
		if(c == null)
			return "no account";
	
		double newBalance = c.getBalance() + value;
		c.setBalance( newBalance );
		saveXML();
		return "deposit achieved";
	}

	public List<Account> getAll() {
		return accountStore.getAccountList();
	}
}
