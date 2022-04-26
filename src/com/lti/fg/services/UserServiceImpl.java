package com.lti.fg.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lti.fg.daos.UserDao;
import com.lti.fg.entities.Product;
import com.lti.fg.entities.User;
import com.lti.fg.exceptions.UsersException;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao dao;
    
    @Override
    public List<User> getAllUsers() throws UsersException {
        return dao.getAllUsers();
    }

    @Override
    public int insertUser(User user) throws UsersException {
        return dao.insertUser(user);
    }

    @Override
    public List<Product> viewCartDetailsByUserId(int userId) throws UsersException {
        return dao.viewCartDetailsByUserId(userId);
    }

	@Override
	public int logIn(User user) throws UsersException {
		
		return dao.logIn(user);
	}

	@Override
	public User getUserById(int userId) throws UsersException {
		return dao.getUserById(userId);
	}

	@Override
	public boolean deleteUser(int userId) throws UsersException {
		return dao.deleteUser(userId);
	}

	@Override
	public List<Product> viewCartHistoryByUserId(int userId) throws UsersException {
		return dao.viewCartHistoryByUserId(userId);
	}

	@Override
	public boolean updatePassword(String email, String password) throws UsersException {
		return dao.updatePassword(email, password);
	}

	
}
