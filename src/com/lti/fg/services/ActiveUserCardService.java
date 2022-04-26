package com.lti.fg.services;

import java.util.List;

import com.lti.fg.entities.ActiveUserCard;
import com.lti.fg.exceptions.ActiveUserCardException;

public interface ActiveUserCardService {

	public long insertRecord(ActiveUserCard card) throws ActiveUserCardException;
	public boolean verifyUser(int userId) throws ActiveUserCardException;
	public boolean rejectUser(int userId) throws ActiveUserCardException;
	public boolean deleteUser(int userId) throws ActiveUserCardException;
	public void verifyPayment(long cardId) throws ActiveUserCardException;
	public String showUserStatus(int userId) throws ActiveUserCardException;
	public String showCardStatus(int userId) throws ActiveUserCardException;
	public double showCardBalance(int userId) throws ActiveUserCardException;
	public void deductCardBalance(double balance, int userId) throws ActiveUserCardException;
	public ActiveUserCard getUserById(long cardId) throws ActiveUserCardException;
	public List<ActiveUserCard> getAllUsers() throws ActiveUserCardException;
	public void insertFile(int userId, String name) throws ActiveUserCardException;
	public ActiveUserCard getUserById(int userId) throws ActiveUserCardException;
	public void payEmi(double emiAmount, int userId) throws ActiveUserCardException;
}
