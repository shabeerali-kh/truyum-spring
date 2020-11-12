package com.cognizant.truyum.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cognizant.truyum.dao.CartEmptyException;
import com.cognizant.truyum.model.MenuItem;

public class CartServiceTest {
	
	CartService cartService;
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Before
	public void initializeService() {

		ApplicationContext context=new AnnotationConfigApplicationContext();
		((AnnotationConfigApplicationContext) context).scan("com.cognizant.truyum");
		((AnnotationConfigApplicationContext) context).refresh();
		cartService=(CartService) context.getBean("cartService");
	}
	
	@Test
	public void testGetAllCartItemsException () throws CartEmptyException {
		exceptionRule.expect(CartEmptyException.class);
		cartService.getAllCartItems(3);
	}
	@Test
	public void testAddCartItem() throws CartEmptyException{
		cartService.addCartItem(2,3);
		List<MenuItem> list=cartService.getAllCartItems(1);
		int flag=0;
		for(MenuItem item:list) {
			if(item.getName().equalsIgnoreCase("Pizza")) {
				flag=1;
			}
		}
		assertEquals(1,flag);
	}
	
}
