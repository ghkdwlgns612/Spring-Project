package myspring.di.annot;

import org.springframework.stereotype.Component;

@Component("consolePrinter")
public class ConsolePrint implements Printer {

	@Override
	public void print(String message) {
		System.out.println(message);
	}

}