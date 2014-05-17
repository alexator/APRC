/**
 * @author Alex Georgantas
 * 
 * This program implements a simple class creation with two methods the append()
 * and the printMessage(). The append() method combines strings in order to produce
 * a single large string. The printMethod() prints the new string in console.
 *
 */

package append;

public class Message {

	String message;		//variable that holds a message

	/**
	 * Constructor of Message class
	 * @param mes
	 */
	public Message(String mes) {
		message = mes;
	}
	
	/**
	 * Method that adds to String message the content of text
	 * @param text
	 * @return
	 */
	public Message append(String text) {

		message = message + text;
		return this;
	}

	/**
	 * Method that prints the content of String message
	 */
	public void printMessage() {

		System.out.println(message);
	}

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {

		Message m = new Message("test");	//Creates an Message object and passes a variable to its constructor
		m.append(" in").append(" progress").append(" ...");
		m.printMessage();

	}

}
