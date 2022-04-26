package com.lti.fg.daos;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lti.fg.entities.EmiHistory;
@Repository
public class EmiHistoryDaoImpl implements EmiHistoryDao{

	@PersistenceContext
	private EntityManager manager;
	

	@Override
	public void insertEmiHistoryRecord(EmiHistory emiHistory) {
		Query query = manager.createQuery("select MAX(emiHistoryId) from EmiHistory");
    	Integer maxId = (Integer)query.getSingleResult();
    	if(maxId==null)
    		maxId = 1;
    	else
    		maxId += 1;
    	emiHistory.setEmiHistoryId(maxId);
		manager.persist(emiHistory);
		
	}

	@Override
	public List<EmiHistory> getEmiHistoryById(int orderId) {
		
		Query findEmiHistory = manager.createQuery("from EmiHistory where orderId = "+ orderId);
		List<EmiHistory> emiHistoryIdList = findEmiHistory.getResultList();
		//System.out.println(emiHistoryIdList);
		return emiHistoryIdList;
	}

	@Override
	public List<EmiHistory> getAllEmiRecords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void makePayment(int id, Date today) {
		EmiHistory emi = manager.find(EmiHistory.class, id);
		emi.setDateOfPayment(today);
		
	}

}
