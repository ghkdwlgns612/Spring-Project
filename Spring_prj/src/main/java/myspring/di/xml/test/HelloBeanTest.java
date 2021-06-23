package myspring.di.xml.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import myspring.di.xml.Hello;
import myspring.di.xml.Printer;


public class HelloBeanTest {

	public static void main(String[] args) {
		// IOC�����̳� ��
		ApplicationContext context = new GenericXmlApplicationContext("config/beans.xml");
		//2.Hello Bean ��������
		Hello hello = (Hello) context.getBean("hello");
		System.out.println(hello.sayHello());
		hello.print();
		//3. StringPrinter beans ��������
		Printer printer = context.getBean("printer",Printer.class);
		System.out.println(printer.toString());
		
		Hello hello2 = context.getBean("hello",Hello.class);
		System.out.println(hello2 == hello);
	}

}