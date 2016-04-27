package com.example.one2nine.game;

public class Number {
	int value;
	String label;
	
	public Number(int v, String l) {
		value = v;
		label = l;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}
