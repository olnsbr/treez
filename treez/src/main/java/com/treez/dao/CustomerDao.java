package com.treez.dao;

import org.springframework.data.repository.CrudRepository;

import com.treez.model.Customer;

public interface CustomerDao extends CrudRepository<Customer, Integer> {

}
