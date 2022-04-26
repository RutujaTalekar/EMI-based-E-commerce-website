package com.lti.fg.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lti.fg.daos.OrderHistoryDao;
import com.lti.fg.entities.OrderHistory;


@Service
@Transactional(propagation=Propagation.REQUIRED)
public class OrderHistoryServiceImpl implements OrderHistoryService{

	@Resource
	private OrderHistoryDao dao;
	
	@Override
	public void insertOrder(OrderHistory order) {
		dao.insertOrder(order);
		
	}

	@Override
	public List<OrderHistory> getAllOrder() {
		return dao.getAllOrder();
	}

	@Override
	public List<OrderHistory> getOrderById(Integer userId) {
		return dao.getOrderById(userId);
	}

}
