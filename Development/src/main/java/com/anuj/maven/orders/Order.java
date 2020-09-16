package com.anuj.maven.orders;

import org.springframework.stereotype.Component;

@Component
public class Order {
	
	int id;
	String entity;
	String email;
	double payment;
	
	public Order() {
		super();
	}
	
	public Order(int id, String entity, String email, Double payment) {
		
		this.id=id;
		this.entity=entity;
		this.email=email;
		this.payment=payment;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getPayment() {
		return payment;
	}
	public void setPayment(double payment) {
		this.payment = payment;
	}
	

}
