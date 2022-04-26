package com.lti.fg.services;

import java.util.List;

import com.lti.fg.entities.OrderDetails;
import com.lti.fg.exceptions.OrderException;

public interface OrderDetailsService {

	public int placeOrder(OrderDetails order) throws OrderException; 
	public List<OrderDetails> getOrderDetailsById(int userId);
}
