package com.lti.fg.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lti.fg.daos.CartDao;
import com.lti.fg.entities.Cart;
import com.lti.fg.exceptions.CartException;


@Service
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class CartServiceImpl implements CartService{
	
	@Resource
	private CartDao dao;
	    
	 
	    @Override
	    public List<Cart> veiwCart(int id) throws CartException {
	        return dao.veiwCart(id);
	    }

	    @Override
	    public boolean addToCart(Cart cart) throws CartException {
	        return dao.insertIntoCart(cart);
	    }

		@Override
		public int getQuantity(int userId, int productId) throws CartException {
			return dao.getQuantity(userId, productId);
		}

		@Override
		public boolean deleteRecordById(int userId) throws CartException {
			return dao.deleteRecordById(userId);
		}

		@Override
		public boolean deleteRecordByProduct(int productId) throws CartException {
			return dao.deleteRecordByProduct(productId);
		}

		@Override
		public int getQuantityFromHistory(int userId, int productId) throws CartException {
			return dao.getQuantityFromHistory(userId, productId);
		}
}
