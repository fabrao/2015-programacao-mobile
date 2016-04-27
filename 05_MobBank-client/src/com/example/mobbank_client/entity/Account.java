package com.example.mobbank_client.entity;

public class Account {
	private String name;
	private long number;
	private double balance;
	
	public Account(String name, long number, double balance) {
		this.name = name;
		this.number = number;
		this.balance = balance;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return number + " : " + name;
	}
}
