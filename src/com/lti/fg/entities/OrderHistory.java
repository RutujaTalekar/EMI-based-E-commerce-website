package com.lti.fg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ORDERHISTORY")
public class OrderHistory 
{
	@Id
	@Column(name="HISTORYID")
	private int orderHistoryId;
	@Column(name="ORDERID")
	private int orderId;
	@Column(name="USERID")
	private int userId;
	@Column(name="PRODUCTID")
	private int productId;
	@Column(name="QUANTITY")
	private int quantity;
	
	
	public OrderHistory() {
		super();
		// TODO Auto-generated constructor stub
	}


	public OrderHistory(int orderId, int userId, int productId, int quantity) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
	}

	

	public int getOrderHistoryId() {
		return orderHistoryId;
	}


	public void setOrderHistoryId(int orderHistoryId) {
		this.orderHistoryId = orderHistoryId;
	}


	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	@Override
	public String toString() {
		return "OrderHistory [orderHistoryId=" + orderHistoryId + ", orderId=" + orderId + ", userId=" + userId
				+ ", productId=" + productId + ", quantity=" + quantity + "]";
	}


	
	
}
