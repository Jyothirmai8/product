package com.product.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.product.entity.Product;

@Repository
public class ProductDAO implements ProductDAOInterface{
	
	@Autowired
	private DataSource ds;
	
		
	@Autowired
	private EntityManagerFactory emf;

	@Override
	public List<Product> getdaoalljpa() {
		EntityManager em=emf.createEntityManager();
		Query q=em.createQuery("from com.product.entity.Product e");
		
		return q.getResultList();
	}	

	}
