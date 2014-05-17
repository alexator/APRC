package message_printer;

import java.util.ArrayList;

public class MessagePrinter {

	ArrayList<Message> ref = new ArrayList<Message>();

	public void addMessage(Message m) {
		ref.add(m);
	}

	public void printAllMessages() {
		for (int i = 0; i < ref.size(); i++) {
			ref.get(i).printMessage();
		}
	}

	public static void main(String[] args) {

		Message m1 = new Message("test1");
		Message m2 = new Message("test2");
		Message m3 = new Message("test3");
		MessagePrinter p = new MessagePrinter();
		p.addMessage(m1);
		p.addMessage(m2);
		p.addMessage(m3);
		p.printAllMessages();

	}

}
