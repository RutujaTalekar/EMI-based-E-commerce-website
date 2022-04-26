package com.lti.fg.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lti.fg.daos.OrderDetailsDao;
import com.lti.fg.entities.OrderDetails;
import com.lti.fg.exceptions.OrderException;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class OrderDetailsServiceImpl implements OrderDetailsService{
	
	@Resource
	OrderDetailsDao dao;

	
	@Override
	public int placeOrder(OrderDetails order) throws OrderException {
		
		return dao.addOrderRecord(order);
		
	}


	@Override
	public List<OrderDetails> getOrderDetailsById(int userId) {
		return dao.getOrderDetailsById(userId);
	}

}
