package com.lti.fg.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lti.fg.entities.OrderDetails;
import com.lti.fg.entities.OrderHistory;
import com.lti.fg.exceptions.OrderException;

@Repository
public class OrderDetailsDaoImpl implements OrderDetailsDao{
	@PersistenceContext
	EntityManager manager;
	
	@Override
	public int addOrderRecord(OrderDetails order) throws OrderException {
		Query query = manager.createQuery("select MAX(orderId) from OrderDetails");
    	Integer orderId = (Integer)query.getSingleResult();
    	if(orderId == null)
    		orderId=0;
    	orderId+=1;
    	order.setOrderId(orderId);
		manager.persist(order);
		return orderId;
	}

	@Override
	public List<OrderDetails> getOrderDetailsById(int userId) {
		Query query = manager.createQuery("select orderId from OrderDetails where userId = " + userId+" ORDER BY orderId DESC");
		List<Integer> orderIdList = query.getResultList(); 		
		List<OrderDetails> orderDetailsList = new ArrayList<>();
		for(Integer orderId : orderIdList)
			orderDetailsList.add(manager.find(OrderDetails.class, orderId));
		return orderDetailsList;
	}
	
	

}
