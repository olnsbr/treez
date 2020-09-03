package com.treez.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.treez.dao.InventoryDao;
import com.treez.dao.OrderDao;
import com.treez.domainException.ErrorResponse;
import com.treez.domainException.InvalidQuantityException;
import com.treez.model.Inventory;
import com.treez.model.Order;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class OrderControllerTest {
	
	@MockBean
	private OrderDao oDao;
	
	@MockBean
	private InventoryDao iDao;
	
	@Autowired
	private OrderController oCont;

	@Test
	void testHandleNoRecordFoundException() {
		
		InvalidQuantityException invaEx = new InvalidQuantityException();
		
		ErrorResponse resp = oCont.handleNoRecordFoundException(invaEx);
		
		assertThat(resp.getMessage().equals("No inventory avaliable for this Order!"));
		
	}

	@Test
	void testAddOrder() {
		
		Order testOrder = new Order();
		Inventory testInventory = new Inventory();
		List<Integer> orderItens = new ArrayList<>();
		
		orderItens.add(1);
		testOrder.setItems(orderItens);
		testInventory.setQuantity(1);
		
		Mockito.when(iDao.findById(anyInt())).thenReturn(Optional.of(testInventory));
		Mockito.when(oDao.save(any(Order.class))).thenReturn(testOrder);
		
		String result = oCont.addOrder(testOrder);
		
		assertEquals(result, "Order #"+ null + " saved!");
		
	}

	@Test
	void testGetOrders() {
		
		Mockito.when(oDao.findAll()).thenReturn(new ArrayList<Order>());
		
		final ArrayList<Order> result = (ArrayList<Order>) oCont.getOrders();
		
		assertThat(result != null);
		
	}

	@Test
	void testGetOrder() {
		
		Mockito.when(oDao.findById(anyInt())).thenReturn(Optional.of(new Order()));
		
		Optional<Order> testInventory = oCont.getOrder(anyInt());
		
		assertThat(testInventory != null);
		
	}

	@Test
	void testUpdateOrder() {

		Order testOrder = new Order();
		
		testOrder.setItems(new ArrayList<Integer>());
		
		testOrder.getItems().add(0); //can't update order without inventory
		
		Mockito.when(oDao.findById(anyInt())).thenReturn(Optional.of(testOrder));
		
		Mockito.when(oDao.save(testOrder)).thenReturn(testOrder);
		
		Mockito.when(iDao.findById(anyInt())).thenReturn(Optional.of(new Inventory()));
		
		ResponseEntity<Order> response = oCont.updateOrder(anyInt(), testOrder);
		
		assertEquals(response, ResponseEntity.ok(testOrder));
		
	}

	@Test
	void testDeleteOrder() {
		
		Mockito.when(oDao.findById(anyInt())).thenReturn(Optional.of(new Order()));
		
		Map<String, Boolean> response = oCont.deleteOrder(anyInt());
		
		assertEquals(response.get("deleted"), Boolean.TRUE);
		
	}

}
