package sample.spring.yse;

public class ConsolePrint implements Printer {

	@Override
	public void print(String message) {
		System.out.println(message);
	}

}
