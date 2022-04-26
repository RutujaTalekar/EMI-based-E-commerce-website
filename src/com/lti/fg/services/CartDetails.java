package com.lti.fg.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.lti.fg.entities.Product;
import com.lti.fg.exceptions.CartException;
import com.lti.fg.exceptions.UsersException;


@Service
public class CartDetails {
	
	@Resource
	private UserService userService;
	
	@Resource
	private CartService cartService;
	public ModelAndView getCartDetails(int userId) throws CartException, UsersException
	{
		
		List<Integer> quant = new ArrayList<>(); 
		
		List<Product> productList = userService.viewCartDetailsByUserId(userId);
		double cartCost = 0.0;
		for(Product product:productList) {
			int quantt = cartService.getQuantity(userId,product.getProductId());
			
			cartCost+=(product.getProductCost()*quantt);
			
			quant.add(quantt);
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("productList", productList);
		mv.addObject("Quantity",quant);

		mv.addObject("cartCost", cartCost);
		return mv;	
	}

}
