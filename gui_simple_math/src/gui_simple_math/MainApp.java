package gui_simple_math;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MainApp implements ActionListener {
	
	static MainApp win;
	int num1;
	int num2;
	int result;
	String value1;
	String value2;
	String res;
	JLabel label2;
	JTextField numberOne;
	JTextField numberTwo;
	JLabel label;
	JButton addButton;
	JButton minButton;
	JButton multButton;
	JButton divButton;
	JButton divreButton;
	JButton modButton;

	private MainApp() {
		setupGUI();
	}

	// Class that setup the GUI of the program
	public void setupGUI() {

		// Create the frame of the window
		JFrame window = new JFrame("Operations");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create the main panel of the window
		JPanel panel = new JPanel();
		window.setContentPane(panel);
		panel.setLayout(null);

		// Create a label
		label = new JLabel("Please give the program two integers:");
		label.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(53, 0, 280, 40);

		// Create a filed for number one (input)
		numberOne = new JTextField("Number One");
		numberOne.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				numberOne.setText("");
				if ((numberOne.getText() == " ") || (numberTwo.getText() == " ")) {
					label2.setText("Waiting for inputs");
				}
			}
		});
		
		numberOne.setHorizontalAlignment(SwingConstants.CENTER);
		numberOne.setBounds(53, 51, 100, 40);

		// Create a filed for number two (input)
		numberTwo = new JTextField("Number Two");
		numberTwo.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				numberTwo.setText("");
				if ((numberOne.getText() == " ") || (numberTwo.getText() == " ")) {
					label2.setText("Waiting for inputs");
				}
			}
		});
		
		numberTwo.setHorizontalAlignment(SwingConstants.CENTER);
		numberTwo.setBounds(233, 51, 100, 40);

		// Create minus button
		minButton = new JButton("-");
		minButton.setBounds(163, 114, 57, 35);
		minButton.setActionCommand("min");
		minButton.addActionListener(this);

		// Create add button
		addButton = new JButton("+");
		addButton.setBounds(53, 114, 57, 35);
		addButton.setActionCommand("add");
		addButton.addActionListener(this);

		// Create multiplication button
		multButton = new JButton("*");
		multButton.setBounds(276, 114, 57, 35);
		multButton.setActionCommand("mult");
		multButton.addActionListener(this);

		// Create division button
		divButton = new JButton("/");
		divButton.setBounds(53, 160, 57, 35);
		divButton.setActionCommand("div");
		divButton.addActionListener(this);

		// Create real division button
		divreButton = new JButton("/");
		divreButton.setBounds(163, 160, 57, 35);
		divreButton.setActionCommand("divre");
		divreButton.addActionListener(this);

		// Create modulo button
		modButton = new JButton("%");
		modButton.setBounds(276, 160, 57, 35);
		modButton.setActionCommand("mod");
		modButton.addActionListener(this);

		// Create label for the result of the operation
		label2 = new JLabel("Result:");
		label2.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		label2.setBounds(10, 216, 364, 35);
		label2.setHorizontalAlignment(SwingConstants.CENTER);

		// Add all the elements to the panel of the window
		window.getContentPane().add(numberOne);
		window.getContentPane().add(numberTwo);
		window.getContentPane().add(addButton);
		window.getContentPane().add(minButton);
		window.getContentPane().add(multButton);
		window.getContentPane().add(divButton);
		window.getContentPane().add(divreButton);
		window.getContentPane().add(modButton);
		window.getContentPane().add(label2);
		window.getContentPane().add(label);

		// Declare windows size and visibility
		window.setSize(400, 300);
		window.setVisible(true);
	}

	// Create an action listener of the six buttons

	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == "add") {
			value1 = numberOne.getText();
			value2 = numberTwo.getText();
			num1 = Integer.parseInt(value1);
			num2 = Integer.parseInt(value2);
			result = num1 + num2;
			res = Integer.toString(result);
			label2.setText("The result is: " + value1 + " + " + value2 + " = "
					+ res);
		} else if (e.getActionCommand() == "min") {
			value1 = numberOne.getText();
			value2 = numberTwo.getText();
			num1 = Integer.parseInt(value1);
			num2 = Integer.parseInt(value2);
			result = num1 - num2;
			res = Integer.toString(result);
			label2.setText("The result is: " + value1 + " - " + value2 + " = "
					+ res);
		} else if (e.getActionCommand() == "mult") {
			value1 = numberOne.getText();
			value2 = numberTwo.getText();
			num1 = Integer.parseInt(value1);
			num2 = Integer.parseInt(value2);
			result = num1 * num2;
			res = Integer.toString(result);
			label2.setText("The result is: " + value1 + " * " + value2 + " = "
					+ res);
		} else if (e.getActionCommand() == "div") {
			value1 = numberOne.getText();
			value2 = numberTwo.getText();
			num1 = Integer.parseInt(value1);
			num2 = Integer.parseInt(value2);
			result = num1 / num2;
			res = Integer.toString(result);
			label2.setText("The result is: " + value1 + " / " + value2 + " = "
					+ res);
		} else if (e.getActionCommand() == "divre") {
			value1 = numberOne.getText();
			value2 = numberTwo.getText();
			num1 = Integer.parseInt(value1);
			num2 = Integer.parseInt(value2);
			double result = num1 / (double) num2;
			res = Double.toString(result);
			label2.setText("The result is: " + value1 + " / " + value2 + " = "
					+ res);
		}

		else if (e.getActionCommand() == "mod") {
			value1 = numberOne.getText();
			value2 = numberTwo.getText();
			num1 = Integer.parseInt(value1);
			num2 = Integer.parseInt(value2);
			result = num1 % num2;
			res = Integer.toString(result);
			label2.setText("The result is: " + value1 + " % " + value2 + " = "
					+ res);
		}
	}

	// Run the MainApp class in order to create the GUI
	public static void main(String[] args) {
		
		win = new MainApp();
	}
}