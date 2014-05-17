package gui_atm;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class AtmSystem implements ActionListener {

	static AtmSystem win;
	private static int balInPen = 100 * 100;
	private static Account user1 = new Account(balInPen);

	final static int MAX_DEP = 500 * 100;
	final static int MIN_DEP = 100 * 100;
	final static int MAX_WITH = 250 * 100;
	final static int MIN_WITH = 10 * 100;
	static int dep;
	static int wit;
	String value1;
	String value2;
	int num;
	String res;
	JLabel headLabel;
	static JLabel balLabel;
	JLabel label;
	JButton depButton;
	JButton witButton;
	JTextField moneyInp;
	static JTextArea textArea;
	static JFrame window;

	private AtmSystem() {
		setupGUI();
	}

	public void setupGUI() {

		// Create the frame of the window
		window = new JFrame("A.T.M System");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create the main panel of the window
		JPanel panel = new JPanel();
		window.setContentPane(panel);

		// Create a label
		headLabel = new JLabel("Welcome to your Bank!");
		headLabel.setBounds(177, 6, 388, 40);
		headLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		headLabel.setHorizontalAlignment(SwingConstants.CENTER);

		// Create label for the result of the operation
		label = new JLabel(
				"Please type the amount of money you want and then press the button to performe the action you want.");
		label.setBounds(6, 40, 688, 40);
		label.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		label.setHorizontalAlignment(SwingConstants.CENTER);

		// Create a label
		balLabel = new JLabel(convertPounds(user1.getBalance()));
		balLabel.setBounds(124, 92, 145, 40);
		balLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		balLabel.setHorizontalAlignment(SwingConstants.CENTER);

		// Create minus button
		depButton = new JButton("Deposit");
		depButton.setBounds(94, 409, 98, 48);
		depButton.setActionCommand("dep");
		depButton.addActionListener(this);

		// Create add button
		witButton = new JButton("Withdrawal");
		witButton.setBounds(549, 406, 98, 54);
		witButton.setActionCommand("wit");
		witButton.addActionListener(this);
		panel.setLayout(null);

		// Add all the elements to the panel of the window
		window.getContentPane().add(depButton);
		window.getContentPane().add(witButton);
		window.getContentPane().add(headLabel);
		window.getContentPane().add(label);
		window.getContentPane().add(balLabel);

		moneyInp = new JTextField("Amount of money");
		moneyInp.setBounds(272, 289, 151, 69);
		moneyInp.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				moneyInp.setText("");
			}
		});
		panel.add(moneyInp);
		moneyInp.setColumns(10);

		JLabel lblCurrentBalance = new JLabel("Current Balance:");
		lblCurrentBalance.setBounds(16, 92, 159, 40);
		panel.add(lblCurrentBalance);

		textArea = new JTextArea();
		textArea.setBounds(26, 145, 665, 132);
		panel.add(textArea);

		// Declare windows size and visibility
		window.setSize(700, 500);
		window.setVisible(true);
	}

	// Create an action listener of the six buttons

	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == "dep") {
			value1 = moneyInp.getText();
			num = Integer.parseInt(value1);
			getDeposit(num);
			doDeposit(user1);
		} else if (e.getActionCommand() == "wit") {
			value1 = moneyInp.getText();
			num = Integer.parseInt(value1);
			getWithdraw(num);
			doWithdraw(user1);

		}
	}

	public static void main(String[] args) {
		win = new AtmSystem();

	}

	private static void doDeposit(Account u) {
		if (checkRangeDep(dep)) {
			u.deposit(dep);
			dispDep(u);
		} else {
			JOptionPane.showMessageDialog(window,
					"Your amount must be between 100 and 500 Pounds",
					"Warning", JOptionPane.WARNING_MESSAGE);
		}
	}

	private static void getDeposit(int d) {
		dep = d;
		dep = dep * 100;
	}

	private static void dispDep(Account u) {
		balLabel.setText(convertPounds(u.getBalance()));
		textArea.setText("Your deposit was: " + convertPounds(dep));
	}

	private static boolean checkRangeDep(int d) {
		if ((d >= MIN_DEP) && (d <= MAX_DEP)) {
			return true;
		} else {
			return false;
		}
	}

	private static void doWithdraw(Account u) {
		if (checkRangeWit(wit)) {
			if (checkMultWit(wit/100)) {
				if (noZeroAcc(u)) {
					u.withdraw(wit);
					dispWit(u);
				} else {
					JOptionPane.showMessageDialog(window,
							"You can't empty your account", "Warning",
							JOptionPane.WARNING_MESSAGE);

				}
			} else {
				JOptionPane.showMessageDialog(window,
						"Your withdrawal must be a multiple of 10", "Warning",
						JOptionPane.WARNING_MESSAGE);
				System.out.println("problem");
			}
		} else {
			JOptionPane.showMessageDialog(window,
					"Your amount must be between 10 and 250 Pounds", "Warning",
					JOptionPane.WARNING_MESSAGE);

		}
	}

	private static void getWithdraw(int w) {
		wit = w;
		wit = wit * 100;
	}

	private static boolean checkRangeWit(int w) {
		if ((w >= MIN_WITH) && (w <= MAX_WITH)) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean checkMultWit(int w) {
		if (w % 10 == 0) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean noZeroAcc(Account u) {
		if ((u.getBalance() - wit) > 0) {
			return true;
		} else {
			return false;
		}
	}

	private static void dispWit(Account u) {

		balLabel.setText(convertPounds(u.getBalance()));
		textArea.setText("Your withdraw was: " + convertPounds(wit));

	}

	private static String convertPounds(int amount) {

		String conv = String.format("%d.%d", amount / 100, amount % 100);
		return conv;
	}
}
