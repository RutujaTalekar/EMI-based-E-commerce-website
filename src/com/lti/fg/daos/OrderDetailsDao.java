package com.lti.fg.daos;

import java.util.List;

import com.lti.fg.entities.OrderDetails;
import com.lti.fg.exceptions.OrderException;

public interface OrderDetailsDao {
	
	public int addOrderRecord(OrderDetails order) throws OrderException; 
	
	public List<OrderDetails> getOrderDetailsById(int userId);
}
