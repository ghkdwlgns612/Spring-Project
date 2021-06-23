package myspring.di.xml;

public class ConsolePrint implements Printer {

	@Override
	public void print(String message) {
		System.out.println(message);
	}

}