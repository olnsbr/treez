package com.treez.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "Inventory")
public class Inventory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="inventory_id")
	private int inventoryId;
	
	private String name;
	private String description;
	private Double price;
	private int quantity;
	
	public Inventory() {
		super();
	}
	
	public Inventory(int inventoryId) {
		super();
		this.inventoryId = inventoryId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getInventoryId() {
		return inventoryId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + inventoryId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventory other = (Inventory) obj;
		if (inventoryId != other.inventoryId)
			return false;
		return true;
	}
}
