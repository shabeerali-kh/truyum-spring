package com.cognizant.truyum.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemServiceTest {
	
	MenuItemService menuItemService;
	@Before
	public void initializeService() {
		/*ApplicationContext  ctx=new ClassPathXmlApplicationContext("Spring-config.xml");
		menuItemService = ctx.getBean(MenuItemService.class);*/
		ApplicationContext context=new AnnotationConfigApplicationContext();
		((AnnotationConfigApplicationContext) context).scan("com.cognizant.truyum");
		((AnnotationConfigApplicationContext) context).refresh();
		menuItemService=(MenuItemService) context.getBean("menuItemService");
	}
	 
	@Test
	public void testGetMenuItemListCustomerSize() {
		assertEquals(5,menuItemService.getMenuItemListAdmin().size());
	}
	@Test
	public void testGetMenuItemListAdminContainsSandwich() {
		String checkSandwich=null;
		for(MenuItem m:menuItemService.getMenuItemListAdmin()) {
			if(m.getName().equalsIgnoreCase("sandwich")) {
				checkSandwich="sandwich";
			}
		}
		assertEquals("sandwich",checkSandwich);
	}
	@Test
	public void testGetMenuItemListAdminSize() {
		assertEquals(3,menuItemService.getMenuItemListCustomer().size());
	}
	@Test
	public void testGetMenuItemListCustomerNotContainsFrenchFries() {
		boolean checkFrenchFries =false;
		for(MenuItem m:menuItemService.getMenuItemListAdmin()) {
			if(m.getName().equalsIgnoreCase("French Fries")) {
				checkFrenchFries=true;
			}
		}
		assertTrue(checkFrenchFries);
	}
	@Test
	public void testGetMenuItem() {
		MenuItem item=menuItemService.getMenuItem(1);
		assertTrue(item.getName().equalsIgnoreCase("Sandwich"));
	}@Test
	public void testModifyMenuItem() {
		MenuItem item= new MenuItem(3, "Pizza", 250.0f, true, DateUtil.convertToDate("21/08/2018"),"Main Course", false);
		menuItemService.modifyMenuItem(item);
		assertTrue(250.0==menuItemService.getMenuItem(3).getPrice());
		
		
	}
}
