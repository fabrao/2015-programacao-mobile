package com.example.one2nine.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GameConfig {
	ArrayList<List<Number>> configurations;
	int current;
	
	public GameConfig() {
		configurations = new ArrayList<List<Number>>();
		configurations.add(getNumberNumber());
		configurations.add(getNumberRoman());
		configurations.add(getNumberEnglish());
		
		current = 0;
	}
	
	private List<Number> getNumberNumber() {
		ArrayList<Number> ret = new ArrayList<Number>();
		ret.add(new Number(1, "1"));
		ret.add(new Number(2, "2"));
		ret.add(new Number(3, "3"));
		ret.add(new Number(4, "4"));
		ret.add(new Number(5, "5"));
		ret.add(new Number(6, "6"));
		ret.add(new Number(7, "7"));
		ret.add(new Number(8, "8"));
		ret.add(new Number(9, "9"));
		return ret;
	}

	private List<Number> getNumberRoman() {
		ArrayList<Number> ret = new ArrayList<Number>();
		ret.add(new Number(1, "I"));
		ret.add(new Number(2, "II"));
		ret.add(new Number(3, "III"));
		ret.add(new Number(4, "IV"));
		ret.add(new Number(5, "V"));
		ret.add(new Number(6, "VI"));
		ret.add(new Number(7, "VII"));
		ret.add(new Number(8, "VIII"));
		ret.add(new Number(9, "IX"));
		return ret;
	}
	
	private List<Number> getNumberEnglish() {
		ArrayList<Number> ret = new ArrayList<Number>();
		ret.add(new Number(1, "one"));
		ret.add(new Number(2, "two"));
		ret.add(new Number(3, "three"));
		ret.add(new Number(4, "four"));
		ret.add(new Number(5, "five"));
		ret.add(new Number(6, "six"));
		ret.add(new Number(7, "seven"));
		ret.add(new Number(8, "eight"));
		ret.add(new Number(9, "nine"));
		return ret;
	}	
	
	public List<Number> getNextConfiguration() {
		List<Number> ret = configurations.get(current);
		Collections.shuffle(ret); 	//embaralha a list
		current++;
		return ret;
	}
	
	public boolean hasNext() {
		return (current < configurations.size());
	}
}
