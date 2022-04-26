package com.lti.fg.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lti.fg.entities.Cart;
import com.lti.fg.entities.OrderHistory;
import com.lti.fg.entities.Product;
import com.lti.fg.entities.User;
import com.lti.fg.exceptions.UsersException;

@Repository
public class UserDaoImpl implements UserDao{
	
	@PersistenceContext
	 private EntityManager manager;
	   
	    @Override
	    public List<User> getAllUsers() throws UsersException
	    {
	        Query query = manager.createQuery("from User");
	        List<User> usersList = query.getResultList();
	        return usersList;
	    }

	    @Override
	    public int insertUser(User user) throws UsersException
	    {
	    	Query query = manager.createQuery("select MAX(userId) from User");
	    	Query queryVerify = manager.createQuery("from User where userEmail= :userEmail and userSavingsAccNumber= :userSavingsAccNumber");
	    	Integer maxId = (Integer)query.getSingleResult();
	    	if(maxId==null)
	    		maxId = 1;
	    	else
	    		maxId += 1;
	    	user.setUserId(maxId);
	    	queryVerify.setParameter("userEmail", user.getUserEmail());
	    	queryVerify.setParameter("userSavingsAccNumber", user.getUserSavingsAccNumber());
	    	List<User> existingUser = queryVerify.getResultList();
	    	if(existingUser.size()==0)
	    		{
	    		manager.persist(user);
	    		return user.getUserId();
	    		}
	        return -1;
	    }

	    @Override
	    public List<Product> viewCartDetailsByUserId(int userId) throws UsersException
	    {
	            Query cartQuery = manager.createQuery("From Cart where userId = :id");
	            cartQuery.setParameter("id", userId);
	            List<Cart> cartList = cartQuery.getResultList();
	            List<Product> productsList = new ArrayList<>();
	            for (Cart cart : cartList) {
	                int productId = cart.getProductId();
	                Product product = manager.find(Product.class, productId);
	                productsList.add(product);
	            }
	            
	           return productsList;
	    }

		@Override
		public int logIn(User user) throws UsersException {

			 Query query = manager.createQuery("select userId from User where userEmail= :userEmail and userPassword= :userPassword");
			 query.setParameter("userEmail", user.getUserEmail());
			 query.setParameter("userPassword", user.getUserPassword());

			 List<Integer> userIdList = query.getResultList();
			 User newUser = new User();
			 if(userIdList.size()>0)
			 {
				 
				 return userIdList.get(0);
			 }
			 return -1;
		}

		@Override
		public User getUserById(int userId) throws UsersException {
			User user= manager.find(User.class, userId);
			return user;
		}

		@Override
		public boolean deleteUser(int userId) throws UsersException {
			User user= manager.find(User.class, userId);
			manager.remove(user);
			return true;
		}

		@Override
		public List<Product> viewCartHistoryByUserId(int userId) throws UsersException {
			Query cartQuery = manager.createQuery("From OrderHistory where userId = :id");
            cartQuery.setParameter("id", userId);
            List<OrderHistory> cartList = cartQuery.getResultList();
            List<Product> productsList = new ArrayList<>();
            for (OrderHistory cart : cartList) {
                int productId = cart.getProductId();
                Product product = manager.find(Product.class, productId);
                productsList.add(product);
            }
            
           return productsList;
		}

		@Override
		public boolean updatePassword(String email, String password) throws UsersException {
			Query query = manager.createQuery("select userId from User where userEmail=:email");
			query.setParameter("email", email);
			List<Integer> userList = query.getResultList();
			if(userList.size()>0)
			{
				int userId = userList.get(0);
				User user = manager.find(User.class, userId);
				user.setUserPassword(password);
				return true;
			}
			
			return false;
		}
}
