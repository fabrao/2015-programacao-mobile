package com.github.mobappscourse.mobbank.service.bankops;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Account {
    private Long number;
    private String clientName; 
    private Double balance;
        
    public Long getNumber() {
        return number;
    }

    public String getClientName() {
        return clientName;
    }

    public Double getBalance() {
        return balance;
    }
    
    public void setNumber(Long number) {
		this.number = number;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

}
