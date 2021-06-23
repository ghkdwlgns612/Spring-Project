package myspring.di.xml.test;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import myspring.di.xml.Hello;
import myspring.di.xml.Printer;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:config/beans.xml")
public class HelloBeanSpring {
	@Autowired
	ApplicationContext context;

	@Test
	public void test2() {
		Hello hello = (Hello) context.getBean("hello");
		
		Hello hello2 = (Hello) context.getBean("hello");
		assertSame(hello, hello2);
	}
	
	@Test @Ignore
	public void test1() {
		//2.Hello Bean 가져오기
		Hello hello = (Hello) context.getBean("hello");
		assertEquals("HelloSpring",hello.sayHello());
		hello.print();
		//3. StringPrinter beans 가져오기
		Printer printer = context.getBean("printer",Printer.class);
		assertEquals("HelloSpring",printer.toString());
		
	}

}