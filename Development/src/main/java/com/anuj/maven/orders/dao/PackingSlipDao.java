package com.anuj.maven.orders.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.anuj.maven.orders.Order;
import com.anuj.maven.orders.PackingSlip;

@Component
public class PackingSlipDao {
	
private NamedParameterJdbcTemplate jdbc;
	
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	
public  boolean postSlip(PackingSlip slip) {
	
	
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(slip);
	
		return jdbc.update("INSERT into packing_slip (message,orders_id) VALUES (:message,:id)", params) == 1;
	}


}
