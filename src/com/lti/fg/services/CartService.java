package com.lti.fg.services;

import java.util.List;

import com.lti.fg.entities.Cart;
import com.lti.fg.exceptions.CartException;

public interface CartService {

	public List<Cart> veiwCart(int id) throws CartException;
    public boolean addToCart(Cart cart) throws CartException;
    public int getQuantity(int userId, int productId)throws CartException;
    public boolean deleteRecordById(int userId) throws CartException;
    public boolean deleteRecordByProduct(int productId) throws CartException;
    public int getQuantityFromHistory(int userId, int productId) throws CartException;
}
