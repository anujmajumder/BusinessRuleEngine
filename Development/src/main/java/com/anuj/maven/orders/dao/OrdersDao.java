package com.anuj.maven.orders.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.anuj.maven.orders.Order;

@Component
public class OrdersDao {
	
private NamedParameterJdbcTemplate jdbc;
	
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	public  boolean create(Order order) {
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(order);
	
		return jdbc.update("INSERT into orders (entity,email,payment) VALUES (:entity,:email,:payment)", params) == 1;
	}

}
