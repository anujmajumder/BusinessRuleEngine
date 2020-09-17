package com.anuj.maven.orders.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.anuj.maven.orders.Users;

@Component
public class UsersDao {
	
private NamedParameterJdbcTemplate jdbc;
	
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	public boolean userInsert(Users user)
	{
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		
		return jdbc.update("INSERT into users (id,name,email,membership) VALUES (:id,:name,:email,:membership)", params) == 1;
	
	}

	public boolean membershipUpgrade(String email) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("email", email);
		
		System.out.println("in  upgrade memebership");
		
		
		return jdbc.update("UPDATE users SET membership = 'UPGRADED' where email=:email ", params) == 1;
		
		
		
		
	}
	
	public boolean membershipCreate(String email) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("email", email);
		
		System.out.println("in create memebership");
		
		
		return jdbc.update("UPDATE users SET membership = 'CREATED' where email=:email ", params) == 1;
		
		
		
		
	}

}
