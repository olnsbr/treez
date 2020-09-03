package com.treez.dao;

import org.springframework.data.repository.CrudRepository;

import com.treez.model.Order;

public interface OrderDao extends CrudRepository<Order, Integer>{

}
