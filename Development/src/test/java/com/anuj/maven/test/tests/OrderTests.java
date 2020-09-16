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
import com.anuj.maven.orders.Users;
import com.anuj.maven.orders.dao.OrdersDao;
import com.anuj.maven.orders.dao.UsersDao;
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
	UsersDao userdao;

	@Autowired
	private DataSource datasource;
	
	@Autowired
	BusinessRules br;
	
	
	@Before
	public void init()
	{
		JdbcTemplate jdbc = new JdbcTemplate(datasource);
		
		jdbc.execute("delete from packing_slip");
		jdbc.execute("delete from orders");
		jdbc.execute("delete from users");
		
		
	
		
	}
	
	@Test
	public void testCreateOrder()
	{
		//assertEquals("Dummy", 1,1);
		
		Order order = new Order(123,"book","anuj.majumder@gmail.com",new Double(26600));
		
		Order order1 = new Order(123,"create membership","anuj.majumder@gmail.com",new Double(26600));
		
		Users user = new Users(1,"Anuj","anuj.majumder@gmail.com",null);
		
		//assertTrue("method should return true", orderdao.create(order));
		assertTrue("should be equal", userdao.userInsert(user) );
		
		assertTrue("method should return true", orderservice.createOrder(order1));
		
		
		
		//assertEquals("should be equal", "packing slip", br.process(order.getEntity()));
		
		String action = br.process(order.getEntity());
		
		//assertTrue("should be true",br.actionProcess(order,action ));
		
		//assertTrue("should return true",br.resolver(order, "shipping") );
		
		//assertTrue("should return true",br.resolver(order, "shipping") );

}
	
}
