package message_printer;

public class Message {

	String message;
	static Message m1;
	static Message m2;
	static Message m3;

	public Message(String m) {
		message = m;
	}

	public void printMessage() {

		System.out.println(message);
	}
}
