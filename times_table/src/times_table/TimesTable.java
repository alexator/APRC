package times_table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class TimesTable implements ActionListener {

	static JFrame window;
	static TimesTable win;
	private JTable table;
	private JTextField textField;
	String value;
	int num;
	JScrollPane scrollPane;

	private TimesTable() {

		setupGUI();
	}

	public void setupGUI() {

		window = new JFrame("Times Table");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		window.setContentPane(panel);
		panel.setLayout(null);

		JButton btnGetInt = new JButton("Get Int");
		btnGetInt.setBounds(277, 230, 117, 29);
		btnGetInt.setActionCommand("get");
		btnGetInt.addActionListener(this);
		panel.add(btnGetInt);

		textField = new JTextField();
		textField.setBounds(49, 229, 134, 28);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblPleaseGiveAn = new JLabel(
				"Please give an int number to calculate the table");
		lblPleaseGiveAn.setBounds(6, 6, 389, 16);
		panel.add(lblPleaseGiveAn);

		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(6, 45, 274, 161);
		panel.add(scrollPane);

		window.setSize(400, 300);
		window.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == "get") {
			value = textField.getText();
			num = Integer.parseInt(value);
			table = new JTable(row(num), head(num));
			scrollPane.setViewportView(table);

		}
	}

	public Object[] head(int n) {
		int i = 0;
		Object[] s = new Object[n];
		for (i = 0; i < n; i++) {
			s[i] = i + 1;
		}
		return s;
	}

	public Object[][] row(int n) {
		Object[][] o = new Object[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				o[i][j] = (i + 1) * (j + 1);
			}
		}
		return o;
	}

	public static void main(String[] args) {

		win = new TimesTable();

	}
}
