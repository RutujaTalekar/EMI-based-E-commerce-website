package com.lti.fg.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lti.fg.entities.OrderHistory;

@Repository
public class OrderHistoryDaoImpl implements OrderHistoryDao{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void insertOrder(OrderHistory order) {
		
		Query query = manager.createQuery("select MAX(orderHistoryId) from OrderHistory");
    	Integer maxId = (Integer)query.getSingleResult();
    	if(maxId==null)
    		maxId = 1;
    	else
    		maxId += 1;
    	order.setOrderHistoryId(maxId);
		manager.persist(order);
	}

	@Override
	public List<OrderHistory> getAllOrder() {
		Query query = manager.createQuery("from OrderHistory");
		List<OrderHistory> orderHistoryList = query.getResultList();
		return orderHistoryList;
	}

	@Override
	public List<OrderHistory> getOrderById(Integer userId) {
		Query query = manager.createQuery("select orderHistoryId from OrderHistory where userId = " + userId);
		List<Integer> orderIdList = query.getResultList();
		List<OrderHistory> orderHistoryList = new ArrayList<>();
		for(Integer historyId : orderIdList)
		{
			orderHistoryList.add(manager.find(OrderHistory.class, historyId));
			
		}
		return orderHistoryList;
	}

}
