package com.lti.fg.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lti.fg.daos.ActiveUserCardDao;
import com.lti.fg.entities.ActiveUserCard;
import com.lti.fg.exceptions.ActiveUserCardException;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ActiveUserCardServiceImpl implements ActiveUserCardService{

	@Resource
	private ActiveUserCardDao dao;
	
	@Override
	public long insertRecord(ActiveUserCard card) throws ActiveUserCardException {
		return dao.insertRecord(card);
	}

	@Override
	public boolean verifyUser(int userId) throws ActiveUserCardException {
		return dao.verifyUser(userId);
	}

	@Override
	public boolean rejectUser(int userId) throws ActiveUserCardException {
		return dao.rejectUser(userId);
	}

	@Override
	public String showUserStatus(int userId) throws ActiveUserCardException {
		return dao.showUserStatus(userId);
	}
	
	@Override
	public String showCardStatus(int userId) throws ActiveUserCardException {
		return dao.showCardStatus(userId);
	}

	@Override
	public double showCardBalance(int userId) throws ActiveUserCardException {
		return dao.showCardBalance(userId);
	}

	@Override
	public void deductCardBalance(double balance, int userId) throws ActiveUserCardException {
		dao.deductCardBalance(balance, userId);
		
	}

	@Override
	public void verifyPayment(long cardId) throws ActiveUserCardException {
		dao.verifyPayment(cardId);
		
	}

	@Override
	public ActiveUserCard getUserById(long cardId) throws ActiveUserCardException {
		return dao.getUserById(cardId);
	}

	@Override
	public List<ActiveUserCard> getAllUsers() throws ActiveUserCardException {
		return dao.getAllUsers();
	}

	@Override
	public boolean deleteUser(int userId) throws ActiveUserCardException {
		return dao.deleteUser(userId);
	}

	@Override
	public void insertFile(int userId, String name) throws ActiveUserCardException {
		dao.insertFile(userId, name);
		
	}

	@Override
	public ActiveUserCard getUserById(int userId) throws ActiveUserCardException {
		return dao.getUserById(userId);
	}

	@Override
	public void payEmi(double emiAmount, int userId) throws ActiveUserCardException {
		dao.payEmi(emiAmount, userId);
		
	}

}
