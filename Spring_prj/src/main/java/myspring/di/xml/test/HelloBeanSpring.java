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
	
	@Test @Ignore
	public void test2() {
		Hello hello = (Hello) context.getBean("hello");
		
		Hello hello2 = (Hello) context.getBean("hello");
		assertSame(hello, hello2);
	}
	
	@Test
	public void test1() {
		Hello hello = (Hello) context.getBean("hello");
		assertEquals("HelloSpring",hello.sayHello());
		hello.print();
		Printer printer = context.getBean("printer",Printer.class);
		assertEquals("HelloSpring",printer.toString());
		
	}

}