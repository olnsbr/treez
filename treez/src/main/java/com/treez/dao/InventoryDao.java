package com.treez.dao;

import org.springframework.data.repository.CrudRepository;

import com.treez.model.Inventory;

public interface InventoryDao extends CrudRepository<Inventory, Integer>{

}
