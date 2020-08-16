package com.product.controller;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.product.dao.ProductDAOInterface;
import com.product.entity.Product;
import com.product.utility.Sender;

@RestController
public class ProductController {
	
	@Autowired
	private ProductDAOInterface proddao;
	
	
	@RequestMapping("allproductjpa")
	@HystrixCommand(fallbackMethod = "userDefaultMethod")
	public List<Product> displayAllManagerjpa(){
		List<Product> el=proddao.getdaoalljpa();
		
		return el;
	}
	public List<Product> userDefaultMethod() {
		System.out.println("-------------------------------product fallback");
		List<Product> el=proddao.getdaoalljpa();
		
		return el;
	}
	
	
	@Autowired
	private Sender ss;
	
	
	public void sendmessage(String message){
		//System.out.println("Jyothi Product service________________" +message);
		ss.sendmessage("mytopic1", "productservice: " + message);
		
		
		//return "message sent successfully";
	}
	
	
private CountDownLatch cc=new CountDownLatch(1);
	
	public CountDownLatch getCc() {
		return cc;
	}
	
	
	
	@KafkaListener(topics = "mytopic")
	public void receive(String m) {
		
		//System.out.println("Product received emp message:  "+m);
		sendmessage(m);
			/*
		 * if(!m.isEmpty() || m != null) {
		 * 
		 * //sendmessage(m); m=""; }
		 */
		cc.countDown();
	}
}
