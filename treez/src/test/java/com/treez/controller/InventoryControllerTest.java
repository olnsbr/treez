package com.treez.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.ArrayList;
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
import com.treez.model.Inventory;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class InventoryControllerTest {
	
	@MockBean
	private InventoryDao iDao;
	
	@Autowired	
	private InventoryController iCont;
	
	@Test
	void testAddInventory() throws Exception {
		
		Inventory item = new Inventory();
		item.setDescription("some Description");
		item.setName("someName");
		item.setPrice(10D);
		item.setQuantity(100);
		
		Mockito.when(iDao.save(item)).thenReturn(item);
		
		final String result = iCont.addInventory(item);
		
		assertEquals(result, "Item "+ item.getName() + " saved!");
	}

	@Test
	void testGetInventories() {
		
		Mockito.when(iDao.findAll()).thenReturn(new ArrayList<Inventory>());
		
		final ArrayList<Inventory> allInventory = (ArrayList<Inventory>) iCont.getInventories();
		
		assertThat(allInventory != null);		
	}

	@Test
	void testGetInventory() {
		
		Mockito.when(iDao.findById(anyInt())).thenReturn(Optional.of(new Inventory()));
		
		Optional<Inventory> testInventory = iCont.getInventory(anyInt());
		
		assertThat(testInventory != null);		
		
	}

	@Test
	void testUpdateInventory() {
		
		Inventory item = new Inventory();
		item.setDescription("some Description");
		item.setName("someName");
		item.setPrice(10D);
		item.setQuantity(100);
		
		Mockito.when(iDao.findById(anyInt())).thenReturn(Optional.of(item));
		
		Mockito.when(iDao.save(item)).thenReturn(item);
		
		ResponseEntity<Inventory> response = iCont.updateInventory(anyInt(), item);
		
		assertEquals(response, ResponseEntity.ok(item));
		
	}

	@Test
	void testDeleteInventory() {
		
		Mockito.when(iDao.findById(anyInt())).thenReturn(Optional.of(new Inventory()));
		
		Map<String, Boolean> response = iCont.deleteInventory(anyInt());
		
		assertEquals(response.get("deleted"), Boolean.TRUE);
		
	}

}
