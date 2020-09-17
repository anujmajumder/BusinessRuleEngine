package com.anuj.maven.orders.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.anuj.maven.orders.Order;
import com.anuj.maven.orders.PackingSlip;
import com.anuj.maven.orders.dao.PackingSlipDao;
import com.anuj.maven.orders.dao.UsersDao;
import com.anuj.maven.orders.mail.Mail;

@Component
public class BusinessRules {
	
private NamedParameterJdbcTemplate jdbc;
	
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	@Autowired
	PackingSlip pslip;
	
	@Autowired
	PackingSlipDao slip;
	
	@Autowired
	UsersDao user;
	
	@Autowired
	Mail mail;

	public String process(String entity) {
		
		try
		{

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("entity", entity);
		System.out.println("the entity is :"+entity + params.getValue("entity"));
		
		
		String action = jdbc.queryForObject("Select action from business_rules where  entity = :entity ",params,String.class);
		
		System.out.println("action  is :"+action);
		
		return action;
		}
		catch(EmptyResultDataAccessException e)
		{
			return null;
		}
		catch(Exception e)
		{
			return null;
		}
		
	}

	public boolean actionProcess(Order order, String actions) {

		List<String> actionsList = new ArrayList<String>();
		String[] items = actions.split(",");
		
		actionsList = Arrays.asList(items);
		
		
		System.out.println(actionsList.toString());
		
		List<Boolean> result = actionsList.stream().map(act -> resolver(order,act)).collect(Collectors.toList());
		
		if(result.contains(false))
		{
		 return false;	
		}
		
		
		return true;
	}


	public boolean resolver(Order order,String act) {
		
		boolean post=false;
		
		if(act.equals("shipping")|| act.equals("royalty department"))
		{
			pslip.generateSlip(act,order.getId());
			 post = slip.postSlip(pslip);
			
		}
		if(act.equals("activateUpgrade"))
		{
			post = user.membershipUpgrade(order.getEmail());
			if(post)
			mail.sendMail(order.getEmail(), "MEMBERSHIP UPGRADE", "Your membership has been upgraded");	
		}
		if(act.equals("activateCreate"))
		{
			post = user.membershipCreate(order.getEmail());
			if(post)
			mail.sendMail(order.getEmail(), "MEMBERSHIP CREATE", "Your membership has been created");
		}
		if(act.equals("video"))
		{
			pslip.generateSlip(act,order.getId());
			 post = slip.postSlip(pslip);
			
		}
		

		
		return post;
	}

}
