package com.treez.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


enum Status 
{ 
    COMFIRMED, DELETED; 
} 

@Entity
@Table(name= "TreezOrder")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	
	@JoinColumn(name = "customer_id")
    @ManyToOne(cascade = CascadeType.DETACH)
	private Customer customer;
	
	private Date date;
	
	@Enumerated(EnumType.ORDINAL)
	private Status status;
	
	@ElementCollection
	private List<Integer> items = new ArrayList<>();
	
	
	public Order() {
		super();
	}
	

	public Customer getCustomer() {
		return customer;
	}



	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	public Integer getOrderId() {
		return orderId;
	}



	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}

	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}
	
	public List<Integer> getItems() {
		return items;
	}


	public void setItems(List<Integer> items) {
		this.items = items;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
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
		Order other = (Order) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		return true;
	}

}
