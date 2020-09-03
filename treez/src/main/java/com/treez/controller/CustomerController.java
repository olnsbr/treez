package com.treez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treez.dao.CustomerDao;
import com.treez.model.Customer;

@RestController
@RequestMapping("/")
public class CustomerController {
	
	@Autowired
	private CustomerDao dao;
	
	@PostMapping("/customers")
	public String addOrder(@RequestBody Customer item) {
		
		dao.save(item);
		return "Cusotomer "+ item.getName() + " saved!";
	}

}
