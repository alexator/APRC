package simple_class;

public class FixedMessage {

	String message;

	public FixedMessage(String mes) {
		message = mes;
	}

	public void printMessage() {

		System.out.println(message);
	}

	public static void main(String[] args) {

		FixedMessage m = new FixedMessage("This is a test");
		m.printMessage();

	}

}
