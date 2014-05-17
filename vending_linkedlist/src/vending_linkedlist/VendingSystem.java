package vending_linkedlist;

import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class VendingSystem implements ActionListener {

	private static VendingItem chocolate = new VendingItem("Chocolate", 57, 5);
	private static VendingItem beer = new VendingItem("Beer", 120, 8);
	private static VendingItem lemonade = new VendingItem("Lemonade", 69, 15);
	private static VendingItem cola = new VendingItem("Cola", 71, 2);
	private static VendingItem crisps = new VendingItem("Crisps", 34, 4);
	private static VendingItem curryPot = new VendingItem("Curry Pot", 149, 2);

	final private static int MONEY = 500;
	private static int change = MONEY;
	private static int cost = 0;
	private static ArrayList<String> receipt = new ArrayList<String>();
	static LinkedList<VendingItem> link ;

	static int two_pounds;
	static int pounds;
	static int fifties;
	static int twenties;
	static int tens;
	static int fives;
	static int twos;
	static int ones;

	JButton btnBuyItem;
	JButton btnInsertAPound;
	JButton btnCompleteSale;
	JButton btnNoSale;
	JScrollPane scrollPane;
	JLabel headLabel;
	JLabel label;

	static JFrame window;
	static VendingSystem win;
	static JTextArea textArea_1;
	private static JTextArea textArea_3;
	private static TextArea textArea_2;
	private static DefaultListModel<VendingItem> model;
	private JList<VendingItem> list_1;

	private VendingSystem() {
		setupGUI();
	}

	public void setupGUI() {

		// Create the frame of the window
		window = new JFrame("Vending Machine");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create the main panel of the window
		JPanel panel = new JPanel();
		window.setContentPane(panel);

		// Create a label
		headLabel = new JLabel("Welcome User!");
		headLabel.setBounds(6, 6, 688, 18);
		headLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		headLabel.setHorizontalAlignment(SwingConstants.CENTER);

		// Create label for the result of the operation
		label = new JLabel(
				"Please make your selection from the below products!");
		label.setBounds(6, 36, 688, 18);
		label.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.setLayout(null);

		window.getContentPane().add(headLabel);
		window.getContentPane().add(label);

		btnInsertAPound = new JButton("Insert a Pound");
		btnInsertAPound.setBounds(324, 221, 134, 40);
		panel.add(btnInsertAPound);
		btnInsertAPound.setActionCommand("ins");
		btnInsertAPound.addActionListener(this);

		btnCompleteSale = new JButton("Complete Sale");
		btnCompleteSale.setBounds(324, 139, 134, 40);
		btnCompleteSale.setActionCommand("comp");
		btnCompleteSale.addActionListener(this);
		panel.add(btnCompleteSale);

		btnNoSale = new JButton("No Sale");
		btnNoSale.setBounds(324, 180, 134, 40);
		btnNoSale.setActionCommand("no");
		btnNoSale.addActionListener(this);

		panel.add(btnNoSale);

		textArea_1 = new JTextArea();
		textArea_1.setBounds(192, 298, 581, 40);
		textArea_1.setLineWrap(true);
		panel.add(textArea_1);

		JLabel lblOutput = new JLabel("Output:");
		lblOutput.setBounds(119, 311, 61, 16);
		panel.add(lblOutput);

		JLabel lblChange = new JLabel("Change:");
		lblChange.setBounds(452, 364, 61, 16);
		panel.add(lblChange);

		JLabel lblItem = new JLabel("Item");
		lblItem.setBounds(32, 364, 61, 16);
		panel.add(lblItem);

		JLabel lblCost = new JLabel("Cost");
		lblCost.setBounds(89, 364, 61, 16);
		panel.add(lblCost);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(150, 364, 61, 16);
		panel.add(lblTotal);

		textArea_3 = new JTextArea();
		textArea_3.setBounds(452, 386, 321, 182);
		textArea_3.setLineWrap(true);
		panel.add(textArea_3);

		textArea_2 = new TextArea();
		textArea_2.setBounds(23, 386, 405, 182);
		panel.add(textArea_2);

		model = new DefaultListModel<VendingItem>();
		for(int i = 0; i< link.size(); i++){
			model.addElement(link.get(i));
		}

		list_1 = new JList<VendingItem>(model);
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_1.setBounds(16, 65, 269, 221);
		panel.add(list_1);

		btnBuyItem = new JButton("Buy Item");
		btnBuyItem.setBounds(324, 97, 134, 40);
		btnBuyItem.setActionCommand("by");
		btnBuyItem.addActionListener(this);
		panel.add(btnBuyItem);

		// Declare windows size and visibility
		window.setSize(800, 600);
		window.setVisible(true);
	}

	// Create an action listener of the six buttons

	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == "by") {
			buyItem(list_1.getSelectedValue());
			list_1.setModel(model);
		} else if (e.getActionCommand() == "ins") {
			insertPound();

		} else if (e.getActionCommand() == "comp") {
			completeSale();
			btnNoSale.setEnabled(false);
			btnInsertAPound.setEnabled(false);
			btnNoSale.setEnabled(false);
			btnCompleteSale.setEnabled(false);
			btnBuyItem.setEnabled(false);
			textArea_1.setText("\nYour total was " + convertPounds(cost)
					+ " pounds and your change are " + convertPounds(change)
					+ " pounds.");

		} else if (e.getActionCommand() == "no") {
			noSale(chocolate, beer, lemonade, cola, crisps, curryPot);
		}
	}

	public static void main(String[] args) {
		createList();
		win = new VendingSystem();

	}

	private static void addToReceipt(String n, int va) {

		receipt.add(String.format("%-12s%-10s%-10s", n, convertPounds(va),
				convertPounds(cost)));
		textArea_1.setText("Your selection was: " + n + " , which costs "
				+ convertPounds(va) + " pounds and your change are "
				+ convertPounds(change) + " pounds.");
	}

	private static String convertPounds(int amount) {

		String conv = String.format("%d.%d", amount / 100, amount % 100);
		return conv;
	}

	private static void displayReceipt() {

		for (int i = 0; i < receipt.size(); i++) {
			textArea_2.append(receipt.get(i) + "\n");
		}
	}

	private static void getLowestCoins(int value) {

		System.out.println();

		two_pounds = value / 200;
		if (two_pounds > 0) {
			value = value % 200;
			textArea_3.append("2P : " + two_pounds + "\n");
		} else {
			two_pounds = 0;
			textArea_3.append("2P : 0 \n");
		}

		pounds = value / 100;
		if (pounds > 0) {
			value = value % 100;
			textArea_3.append("1P : " + pounds + "\n");
		} else {
			pounds = 0;
			textArea_3.append("1P : 0 \n");
		}

		fifties = value / 50;
		if (fifties > 0) {
			value = value % 50;
			textArea_3.append("50p : " + fifties + "\n");
		} else {
			fifties = 0;
			textArea_3.append("50p : 0 \n");
		}

		twenties = value / 20;
		if (twenties > 0) {
			value = value % 20;
			textArea_3.append("20p : " + twenties + "\n");
		} else {
			twenties = 0;
			textArea_3.append("20p : 0 \n");
		}

		tens = value / 10;
		if (tens > 0) {
			value = value % 10;
			textArea_3.append("10p : " + tens + "\n");

		} else {
			tens = 0;
			textArea_3.append("10p : 0 \n");
		}

		fives = value / 5;
		if (fives > 0) {
			value = value % 5;
			textArea_3.append("5p : " + fives + "\n");
		} else {
			fives = 0;
			textArea_3.append("5p : 0 \n");

		}

		twos = value / 2;
		if (twos > 0) {
			value = value % 2;
			textArea_3.append("2p : " + twos + "\n");

		} else {
			twos = 0;
			textArea_3.append("2p : 0 \n");
		}

		ones = value / 1;
		if (ones > 0) {
			value = value % 1;
			textArea_3.append("1p : " + ones + "\n");

		} else {
			ones = 0;
			textArea_3.append("1p : 0 \n");
		}
	}

	private static void doSale(int va) {

		cost = cost + va;
		change = change - va;
	}

	private static void completeSale() {

		displayReceipt();
		getLowestCoins(change);
	}

	private static void noSale(VendingItem u1, VendingItem u2, VendingItem u3,
			VendingItem u4, VendingItem u5, VendingItem u6) {
		u1.resetStock();
		u2.resetStock();
		u3.resetStock();
		u4.resetStock();
		u5.resetStock();
		u6.resetStock();
		textArea_1.setText("Your money was refund");
		change = MONEY;
		receipt = new ArrayList<String>();
		cost = 0;
		getLowestCoins(change);


	}

	private static void buyItem(VendingItem i) {

		if (i.stockLeft()) {
			if (change >= i.getItemPrice()) {
				i.buyItem();
				doSale(i.getItemPrice());
				addToReceipt(i.getItemName(), i.getItemPrice());
			} else {
				JOptionPane.showMessageDialog(window, "Not enough money!",
						"Warning", JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(window, "Out of Stock", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private static void insertPound() {
		change = change + 100;
		textArea_1.setText("Your new balance is " + convertPounds(change)
				+ " pounds.");
	}

	private static void createList() {
		link = new LinkedList<VendingItem>();
	    link.add(chocolate);
	    link.add(beer);
	    link.add(lemonade);
	    link.add(cola);
	    link.add(crisps);
	    link.add(curryPot);
	}
}
