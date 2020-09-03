package com.treez.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.treez.dao.InventoryDao;
import com.treez.dao.OrderDao;
import com.treez.domainException.ErrorResponse;
import com.treez.domainException.InvalidQuantityException;
import com.treez.model.Inventory;
import com.treez.model.Order;

@RestController
@RequestMapping("/")
public class OrderController {
	
	@Autowired
	private OrderDao oDao;
	@Autowired
	private InventoryDao iDao;
	
	@ExceptionHandler(InvalidQuantityException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleNoRecordFoundException(InvalidQuantityException ex) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("No inventory avaliable for this Order!");
        return errorResponse;
    }
	
	@PostMapping("/orders")
	public String addOrder(@RequestBody Order item) throws InvalidQuantityException{
		
		List<Integer> orderItems = item.getItems();
		
		if(orderItems.isEmpty()) {
			return "Can't add Order with empty Inventory!";
		}else {
			for (Integer integer : orderItems) {
				Optional<Inventory> product = iDao.findById(integer);
				if (product.get().getQuantity() < 1 ) {
					throw new InvalidQuantityException();
				}else {
					product.get().setQuantity(product.get().getQuantity() - 1);
					oDao.save(item);
					return "Order #"+ item.getOrderId() + " saved!";
				}
			}
		}
		
		return null;
		
	}
	
	@GetMapping("/orders")
	public List<Order> getOrders(){
		
		return (List<Order>) oDao.findAll();
	}
	
	@GetMapping("/orders/{id}")
	public Optional<Order> getOrder(@PathVariable Integer id){
		
		return oDao.findById(id);
	}
	
	@PutMapping("/orders/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable Integer id, @RequestBody Order orderDetails){
		
		Optional<Order> item =  oDao.findById(id);
		
		item.get().setCustomer(orderDetails.getCustomer());
		item.get().setDate(orderDetails.getDate());
		item.get().setStatus(orderDetails.getStatus());
		item.get().setItems(orderDetails.getItems());
						
		Order orderUpdated = oDao.save(item.get());
		
		return ResponseEntity.ok(orderUpdated);
	}
	
	@DeleteMapping("/orders/{id}")
	public Map<String, Boolean> deleteOrder(@PathVariable(value = "id") Integer id){
		
		Optional<Order> item =  oDao.findById(id);
		
		List<Integer> orderItems = item.get().getItems();
		
		for (Integer integer : orderItems) {
			
			Optional<Inventory> invetoryRecord = iDao.findById(integer);
			invetoryRecord.get().setQuantity(invetoryRecord.get().getQuantity() + 1);
			iDao.save(invetoryRecord.get());			
		}
		
		oDao.delete(item.get());
			
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
