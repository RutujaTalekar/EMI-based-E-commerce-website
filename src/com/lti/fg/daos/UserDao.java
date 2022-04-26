package com.lti.fg.daos;

import java.util.List;

import com.lti.fg.entities.Product;
import com.lti.fg.entities.User;
import com.lti.fg.exceptions.UsersException;

public interface UserDao {
	 public List<User> getAllUsers() throws UsersException;

	 public int insertUser(User user) throws UsersException;
	    public List<Product> viewCartDetailsByUserId(int userId) throws UsersException;
	    public List<Product> viewCartHistoryByUserId(int userId) throws UsersException;
	    public int logIn(User user)throws UsersException;
	    public boolean deleteUser(int userId)throws UsersException;
	    public User getUserById(int userId) throws UsersException;
	    public boolean updatePassword(String email, String password) throws UsersException;
}
