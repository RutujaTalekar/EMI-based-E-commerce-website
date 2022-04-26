package com.lti.fg.daos;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lti.fg.entities.ActiveUserCard;
import com.lti.fg.exceptions.ActiveUserCardException;
import com.lti.fg.services.OtpGenerator;

@Repository
public class ActiveUserCardDaoImpl implements ActiveUserCardDao {

	@PersistenceContext
	private EntityManager manager;
	
	@Resource
	private OtpGenerator otpGenerator;
	
	@Override
	public long insertRecord(ActiveUserCard card) throws ActiveUserCardException {
		
		long cardId = Long.parseLong(otpGenerator.generatorOTP(8));
    	card.setCardId(cardId);
		manager.persist(card);
		return cardId;
	}

	@Override
	public boolean verifyUser(int userId) throws ActiveUserCardException {
		
		Query findCard= manager.createQuery("select cardId from ActiveUserCard where userId="+userId);
		List<Long> cardId = findCard.getResultList();
		ActiveUserCard userCard = manager.find(ActiveUserCard.class, cardId.get(0));
		userCard.setUserStatus("Verified");
		return true;
	}

	@Override
	public boolean rejectUser(int userId) throws ActiveUserCardException {
		Query findCard= manager.createQuery("select cardId from ActiveUserCard where userId="+userId);
		List<Long> cardId = findCard.getResultList();
		ActiveUserCard userCard = manager.find(ActiveUserCard.class, cardId.get(0));
		userCard.setUserStatus("Rejected");
		return true;
	}

	@Override
	public String showUserStatus(int userId) throws ActiveUserCardException {
		Query findStatus= manager.createQuery("select userStatus from ActiveUserCard where userId="+userId);
		List<String> userStatus = findStatus.getResultList();
		if(userStatus.size()>0)
			return userStatus.get(0);
		return null;
	}
	
	@Override
	public String showCardStatus(int userId) throws ActiveUserCardException {
		Query findStatus= manager.createQuery("select cardStatus from ActiveUserCard where userId="+userId);
		List<String> cardStatus = findStatus.getResultList();
		if(cardStatus.size()>0)
			return cardStatus.get(0);
		return null;
	}

	@Override
	public double showCardBalance(int userId) throws ActiveUserCardException {
		Query findBalance = manager.createQuery("select cardBalance from ActiveUserCard where userId="+userId);
		List<Double> cardBalance = findBalance.getResultList();
		System.out.println("CardBalance = "+cardBalance);
		if(cardBalance.size()==0)
			return -1;
		return cardBalance.get(0);
	}

	@Override
	public void deductCardBalance(double balance, int userId) throws ActiveUserCardException {
		
		Query findCard= manager.createQuery("select cardId from ActiveUserCard where userId="+userId);
		List<Long> cardId = findCard.getResultList();
		ActiveUserCard userCard = manager.find(ActiveUserCard.class, cardId.get(0));
		userCard.setCardBalance(balance);
	}

	@Override
	public void verifyPayment(long cardId) throws ActiveUserCardException {
		ActiveUserCard userCard = manager.find(ActiveUserCard.class, cardId);
		userCard.setCardStatus("Paid");
		
	}

	@Override
	public ActiveUserCard getUserById(long cardId) throws ActiveUserCardException {
		ActiveUserCard user = manager.find(ActiveUserCard.class, cardId);
		return user;
	}

	@Override
	public List<ActiveUserCard> getAllUsers() throws ActiveUserCardException {
		Query query = manager.createQuery("from ActiveUserCard");
		List<ActiveUserCard> aucList = query.getResultList();
		return aucList;
	}

	@Override
	public boolean deleteUser(int userId) throws ActiveUserCardException {
		Query findCard= manager.createQuery("select cardId from ActiveUserCard where userId="+userId);
		List<Long> cardId = findCard.getResultList();
		ActiveUserCard userCard = manager.find(ActiveUserCard.class, cardId.get(0));
		manager.remove(userCard);
		return true;
	}

	@Override
	public void insertFile(int userId, String name) throws ActiveUserCardException {
		Query findCard= manager.createQuery("select cardId from ActiveUserCard where userId="+userId);
		List<Long> cardId = findCard.getResultList();
		ActiveUserCard userCard = manager.find(ActiveUserCard.class, cardId.get(0));
		userCard.setLinkToPdf(name);
	}

	@Override
	public ActiveUserCard getUserById(int userId) throws ActiveUserCardException {
		Query findCard= manager.createQuery("select cardId from ActiveUserCard where userId="+userId);
		List<Long> cardId = findCard.getResultList();
		ActiveUserCard userCard = manager.find(ActiveUserCard.class, cardId.get(0));
		return userCard;
	}

	@Override
	public void payEmi(double emiAmount, int userId) throws ActiveUserCardException {
		Query findCard= manager.createQuery("select cardId from ActiveUserCard where userId="+userId);
		List<Long> cardId = findCard.getResultList();
		ActiveUserCard userCard = manager.find(ActiveUserCard.class, cardId.get(0));
		double balance = userCard.getCardBalance() + emiAmount;
		userCard.setCardBalance(balance);
	}

}
