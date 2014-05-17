package to_string;

public class Message {

	String message;

	public Message() {
		System.out.println("The instance was created!");
	}

	public String toString(String mes) {
		message = mes;
		return message;
	}

	public void printMessage() {
		System.out.println("The message is \"" + message + "\"");
	}

	public static void main(String[] args) {

		Message m = new Message();
		m.toString("xxx");
		m.printMessage();

	}

}
