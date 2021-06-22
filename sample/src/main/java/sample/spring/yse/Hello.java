package sample.spring.yse;

public class Hello {
	private String name;
	private Printer printer;

	public void setName(String name) {
		this.name = name;
	}
	public void setPrinter(Printer printer) {
		this.printer = printer;
	}
	
	public String sayHello() {
		return "Hello" + name;
	}
	public void print() {
		this.printer.print(sayHello());
	}
}
