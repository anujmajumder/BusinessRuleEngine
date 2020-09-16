package com.anuj.maven.orders;

import org.springframework.stereotype.Component;

@Component
public class PackingSlip {

	String message;
	int id;
	
	public PackingSlip()
	{
		
	}
	
	public void generateSlip(String message, int id) {
		
		this.message = message;
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
