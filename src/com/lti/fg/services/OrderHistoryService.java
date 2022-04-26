package com.lti.fg.services;

import java.util.List;

import com.lti.fg.entities.OrderHistory;

public interface OrderHistoryService
{
	public void insertOrder(OrderHistory order);
	public List<OrderHistory> getAllOrder();
	public List<OrderHistory> getOrderById(Integer userId);
}
