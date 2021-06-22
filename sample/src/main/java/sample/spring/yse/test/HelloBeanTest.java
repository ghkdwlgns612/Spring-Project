package sample.spring.yse.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import sample.spring.yse.Hello;
import sample.spring.yse.Printer;

public class HelloBeanTest {

	public static void main(String[] args) {
		// IOC컨테이너 생성
		ApplicationContext context = new GenericXmlApplicationContext("config/beans.xml");
		//2.Hello Bean 가져오기
		Hello hello = (Hello) context.getBean("hello");
		System.out.println(hello.sayHello());
		hello.print();
		//3. StringPrinter beans 가져오기
		Printer printer = context.getBean("printer",Printer.class);
		System.out.println(printer.toString());
	}

}
