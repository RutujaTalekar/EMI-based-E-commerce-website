package com.lti.fg.daos;

import java.util.List;

import com.lti.fg.entities.OrderHistory;

public interface OrderHistoryDao {

	public void insertOrder(OrderHistory order);
	public List<OrderHistory> getAllOrder();
	public List<OrderHistory> getOrderById(Integer userId);	
}
