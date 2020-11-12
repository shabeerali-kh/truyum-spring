package com.cognizant.truyum;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cognizant.truyum.model.MenuItem;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext  ctx=new ClassPathXmlApplicationContext("Spring-config.xml");
        List<MenuItem> list=(List<MenuItem>) ctx.getBean("menuItems");
        for(MenuItem m:list) {
        	System.out.println(m);
        }
    }
}
