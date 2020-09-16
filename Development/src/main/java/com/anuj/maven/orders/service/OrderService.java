package com.anuj.maven.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anuj.maven.orders.Order;
import com.anuj.maven.orders.dao.OrdersDao;
import com.anuj.maven.orders.rules.BusinessRules;

@Service
public class OrderService {
	

	@Autowired
	OrdersDao orders;
	
	@Autowired
	BusinessRules bizrules;

	public boolean createOrder(Order order)
	{
		boolean createResponse =  orders.create(order);
		
		if(createResponse == true)
		{
			String actions = bizrules.process(order.getEntity());
			return true;
		}
		
		return false;
	}

}
