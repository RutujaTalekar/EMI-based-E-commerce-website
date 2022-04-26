package com.lti.fg.daos;

import java.sql.Date;
import java.util.List;

import com.lti.fg.entities.EmiHistory;

public interface EmiHistoryDao {
	
	public void insertEmiHistoryRecord(EmiHistory emiHistory);
	public List<EmiHistory> getEmiHistoryById(int orderId);
	public List<EmiHistory> getAllEmiRecords();
	public void makePayment(int id, Date today);
}
