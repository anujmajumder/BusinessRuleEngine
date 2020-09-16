package com.anuj.maven.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anuj.maven.orders.Order;
import com.anuj.maven.orders.dao.OrdersDao;

@Service
public class OrderService {
	

	@Autowired
	OrdersDao orders;

	public boolean createOrder(Order order)
	{
		return orders.create(order);
		
	}

}
