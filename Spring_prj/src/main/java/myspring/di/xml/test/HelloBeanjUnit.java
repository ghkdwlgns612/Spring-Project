package myspring.di.xml.test;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


import myspring.di.xml.Hello;
import myspring.di.xml.Printer;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.List;

public class HelloBeanjUnit {
	static ApplicationContext context;
	
	@Before
	public void init() {
		// IOC컨테이너 생
		context = new GenericXmlApplicationContext("config/beans.xml");
	}
	
	@Test @Ignore
	public void test2() {
		Hello hello = (Hello) context.getBean("hello");
		
		Hello hello2 = (Hello) context.getBean("hello");
		assertSame(hello, hello2);
	}
	
	@Test
	public void test1() {
		//2.Hello Bean 가져오기
		Hello hello = (Hello) context.getBean("hello2");
		assertEquals("HelloSpring",hello.sayHello());
		hello.print();
		
		
		assertEquals(3, hello.getNames().size());
		List<String> list = hello.getNames();
		for (String value: list)
		{
			System.out.println(value);
		}
		//3. StringPrinter beans 가져오기
		Printer printer = context.getBean("printer",Printer.class);
		assertEquals("HelloSpring",printer.toString());
	}

}