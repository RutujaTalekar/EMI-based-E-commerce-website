package com.lti.fg.services;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lti.fg.daos.EmiHistoryDao;
import com.lti.fg.entities.EmiHistory;


@Service
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class EmiHistoryServiceImpl implements EmiHistoryService{

	@Resource
	private EmiHistoryDao dao;
	
	@Override
	public void insertEmiHistoryRecord(EmiHistory emiHistory) {
		dao.insertEmiHistoryRecord(emiHistory);
		
	}

	@Override
	public List<EmiHistory> getEmiHistoryById(int orderId) {
		return dao.getEmiHistoryById(orderId);
	}

	@Override
	public List<EmiHistory> getAllEmiRecords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void makePayment(int id, Date today) {
		dao.makePayment(id, today);
		
	}

}
