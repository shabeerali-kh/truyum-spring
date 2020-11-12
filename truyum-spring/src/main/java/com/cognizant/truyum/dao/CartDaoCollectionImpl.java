package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;
@Component
@ImportResource("classpath:spring-config.xml")
public class CartDaoCollectionImpl implements CartDao {
		@Autowired
		@Qualifier("userCarts")
		private  Map<Long, Cart> userCarts;
		/*public CartDaoCollectionImpl() {
			if(userCarts==null) {
				userCarts=new HashMap<Long,Cart>();
			}
		 }*/
		public Map<Long, Cart> getUserCarts() {
			return userCarts;
		}
		public void setUserCarts(Map<Long, Cart> userCarts) {
			this.userCarts = userCarts;
		}
		
	public void addCartItem(long id,long menuItemId) {
		
		MenuItemDao menuItemDao= new  MenuItemDaoCollectionImpl();
		MenuItem mItem= menuItemDao.getMenuItem(menuItemId);
		
		if(userCarts.containsKey(id)) {
			userCarts.get(id).getMenuItemList().add(mItem);
		}
		else {
			List<MenuItem> tList=new ArrayList<MenuItem>();
			tList.add(mItem);
			Cart cObj=new Cart(tList);
			userCarts.put(id, cObj);
		}
	}
	
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException{
		
		List<MenuItem> menuList=new ArrayList<MenuItem>();
		Cart c=userCarts.get(userId);
		menuList=c.getMenuItemList();
		
		if(menuList==null||menuList.isEmpty())
				throw new CartEmptyException();
		
		else {
			double total=0;
			for(MenuItem m:menuList) {
				
				total+=m.getPrice();	
			}
			c.setTotal(total);
		}		
		return menuList;
	}
	
	public void removeCartItem(long userId, long menuItemId) {
		
		List<MenuItem> menuList=userCarts.get(userId).getMenuItemList();
		List<MenuItem> removeList=new ArrayList<MenuItem>();
		for(MenuItem m:menuList) {
			if(m.getId()==menuItemId) {
				removeList.add(m);
			}
		}
		menuList.removeAll(removeList);
		userCarts.get(userId).setMenuItemList(menuList);

	}

	
}