package com.anuj.maven.test.tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.anuj.maven.orders.Order;
import com.anuj.maven.orders.dao.OrdersDao;
import com.anuj.maven.orders.rules.BusinessRules;
import com.anuj.maven.orders.service.OrderService;

@ContextConfiguration(locations = { "classpath:com/anuj/maven/orders/beans/bean-context.xml",
		"classpath:com/anuj/maven/test/config/datasource.xml" })



@RunWith(SpringJUnit4ClassRunner.class)
public class OrderTests {
	
	@Autowired
	OrderService orderservice;
	
	@Autowired
	OrdersDao orderdao;

	@Autowired
	private DataSource datasource;
	
	@Autowired
	BusinessRules br;
	
	
	@Before
	public void init()
	{
		JdbcTemplate jdbc = new JdbcTemplate(datasource);
		
		jdbc.execute("delete from orders");
		
	
		
	}
	
	@Test
	public void testCreateOrder()
	{
		//assertEquals("Dummy", 1,1);
		
		Order order = new Order("produc","anuj.majumde@gmail.com",new Double(26600));
		
		assertTrue("method should return true", orderdao.create(order));
		
		assertTrue("method should return true", orderservice.createOrder(order));
		
		assertEquals("should be equal", "packing slip", br.process(order.getEntity()));

}
	
}
