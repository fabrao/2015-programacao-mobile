package com.example.activity_forresult;

import java.io.Serializable;

public class Task implements Serializable {
	private static final long serialVersionUID = 8775063956985148674L;

	String name, description;
	
	public Task(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
