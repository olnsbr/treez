package com.treez.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treez.dao.InventoryDao;
import com.treez.model.Inventory;

@RestController
@RequestMapping("/")
public class InventoryController {
	
	@Autowired
	private InventoryDao dao;
	
	@PostMapping("/inventories")
	public String addInventory(@RequestBody Inventory item) {
		
		dao.save(item);
		return "Item "+ item.getName() + " saved!";
	}
	
	@GetMapping("/inventories")
	public List<Inventory> getInventories(){
		
		return (List<Inventory>) dao.findAll();
	}
	
	@GetMapping("/inventories/{id}")
	public Optional<Inventory> getInventory(@PathVariable Integer id){
		
		return dao.findById(id);
	}
	
	@PutMapping("/inventories/{id}")
	public ResponseEntity<Inventory> updateInventory(@PathVariable Integer id, @RequestBody Inventory itemDetails){
		
		Optional<Inventory> item =  dao.findById(id);
		
		item.get().setName(itemDetails.getName());
		item.get().setDescription(itemDetails.getDescription());
		item.get().setPrice(itemDetails.getPrice());
		item.get().setQuantity(itemDetails.getQuantity());
						
		Inventory itemUpdated = dao.save(item.get());
		
		return ResponseEntity.ok(itemUpdated);
	}
	
	@DeleteMapping("/inventories/{id}")
	public Map<String, Boolean> deleteInventory(@PathVariable(value = "id") Integer id){
		
		Optional<Inventory> item =  dao.findById(id);
		
		dao.delete(item.get());
			
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
		
	}

}
