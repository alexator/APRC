package classes_and_objects;

public class Message {

	static String test1;
	static String test2;
	String message;

	public Message(String mes) {
		message = mes;
	}

	public void printMessage() {

		System.out.println(message);
	}

	public static void main(String[] args) {

		test1 = "Test 1";
		test2 = "Test 2";

		Message m1 = new Message(test1);
		m1.printMessage();
		Message m2 = new Message(test2);
		m2.printMessage();
		m1.printMessage();

	}

}
