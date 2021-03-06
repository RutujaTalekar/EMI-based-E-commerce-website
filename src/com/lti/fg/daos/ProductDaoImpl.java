package com.lti.fg.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lti.fg.entities.Product;
import com.lti.fg.exceptions.ProductException;

@Repository
public class ProductDaoImpl implements ProductDao {
	
	@PersistenceContext
	private EntityManager manager ;
	   
	    @Override
	    public List<Product> getAllProducts() throws ProductException{
	        Query query = manager.createQuery("from Product");
	        List<Product> productsList = query.getResultList();
	        return productsList;
	    }

	    @Override
	    public boolean insertNewProduct(Product product) throws ProductException {
	        Product existingProduct = manager.find(Product.class, product.getProductId());
	        if (existingProduct==null)
	        {
	            manager.persist(product);
	            return true;
	        }
	        return false;
	    }

		@Override
		public Product findProductById(int id) throws ProductException {
			
			Product product = manager.find(Product.class, id);
			return product;
		}
}
