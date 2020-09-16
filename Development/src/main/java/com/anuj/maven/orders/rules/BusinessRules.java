package com.anuj.maven.orders.rules;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BusinessRules {
	
private NamedParameterJdbcTemplate jdbc;
	
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public String process(String entity) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("entity", entity);
		System.out.println("the entity is :"+entity + params.getValue("entity"));
		
		
		String action = jdbc.queryForObject("Select action from business_rules where  entity = :entity ",params,String.class);
		
		System.out.println("action  is :"+action);
		
		return action;
		
	}

}
