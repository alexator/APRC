package malakies;





import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JList;

public class guia {
	
	static guia win;
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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	
	
	
	private guia() {
		setupGUI();
	}

	public void setupGUI() {

		// Create the frame of the window
		window = new JFrame("A.T.M System");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create the main panel of the window
		JPanel panel = new JPanel();
		window.setContentPane(panel);
		panel.setLayout(null);
		
		JLabel lblTopics = new JLabel("Topics:");
		lblTopics.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblTopics.setBounds(17, 6, 73, 20);
		panel.add(lblTopics);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(17, 34, 326, 121);
		panel.add(textArea_1);
		
		JLabel lblStudyPlan = new JLabel("Study Plan:");
		lblStudyPlan.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblStudyPlan.setBounds(163, 396, 134, 32);
		panel.add(lblStudyPlan);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(17, 426, 466, 356);
		panel.add(textArea_2);
		
		textField = new JTextField();
		textField.setBounds(17, 320, 326, 41);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(17, 237, 326, 41);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnGeneratePlan = new JButton("Generate Plan");
		btnGeneratePlan.setBounds(590, 616, 165, 166);
		panel.add(btnGeneratePlan);
		
		JButton btnAddTopic = new JButton("Add Topic");
		btnAddTopic.setBounds(355, 237, 128, 124);
		panel.add(btnAddTopic);
		
		JButton btnDeleteTopic = new JButton("Delete Topic");
		btnDeleteTopic.setBounds(215, 167, 128, 41);
		panel.add(btnDeleteTopic);
		
		JLabel lblTopicTitle = new JLabel("Topic's Title");
		lblTopicTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblTopicTitle.setBounds(17, 209, 100, 20);
		panel.add(lblTopicTitle);
		
		JLabel lblTopicsDuration = new JLabel("Topic's Duration");
		lblTopicsDuration.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblTopicsDuration.setBounds(17, 297, 139, 20);
		panel.add(lblTopicsDuration);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(757, 730, 159, 52);
		panel.add(btnExit);
		
		JLabel lblCalendarEvents = new JLabel("Calendar Events:");
		lblCalendarEvents.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblCalendarEvents.setBounds(590, 6, 176, 20);
		panel.add(lblCalendarEvents);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setBounds(590, 34, 326, 121);
		panel.add(textArea_3);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(590, 200, 326, 41);
		panel.add(textField_2);
		
		JLabel lblEventsTitle = new JLabel("Event's Title");
		lblEventsTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblEventsTitle.setBounds(590, 179, 100, 20);
		panel.add(lblEventsTitle);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(590, 276, 326, 41);
		panel.add(textField_3);
		
		JLabel lblEventsDuration = new JLabel("Event's Duration");
		lblEventsDuration.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblEventsDuration.setBounds(590, 253, 134, 20);
		panel.add(lblEventsDuration);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(590, 372, 81, 41);
		panel.add(textField_4);
		
		JLabel lblEventsStartTime = new JLabel("Event's Start Time");
		lblEventsStartTime.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblEventsStartTime.setBounds(590, 329, 149, 20);
		panel.add(lblEventsStartTime);
		
		JList list = new JList();
		list.setBounds(590, 455, 165, 136);
		panel.add(list);
		
		JLabel lblEventsType = new JLabel("Event's Type");
		lblEventsType.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblEventsType.setBounds(590, 423, 108, 20);
		panel.add(lblEventsType);
		
		JButton btnAddEvent = new JButton("Add Event");
		btnAddEvent.setBounds(788, 465, 128, 117);
		panel.add(btnAddEvent);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(757, 616, 159, 52);
		panel.add(btnSave);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.setBounds(757, 673, 159, 52);
		panel.add(btnLoad);
		
		JButton btnNewButton = new JButton("<--- Set Target --->");
		btnNewButton.setBounds(378, 69, 175, 52);
		panel.add(btnNewButton);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(674, 372, 81, 41);
		panel.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(757, 372, 81, 41);
		panel.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(839, 372, 81, 41);
		panel.add(textField_7);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(611, 355, 40, 16);
		panel.add(lblYear);
		
		JLabel lblMonth = new JLabel("Month");
		lblMonth.setBounds(690, 355, 49, 16);
		panel.add(lblMonth);
		
		JLabel lblDay = new JLabel("Day");
		lblDay.setBounds(779, 355, 40, 16);
		panel.add(lblDay);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setBounds(861, 355, 40, 16);
		panel.add(lblTime);

		// Declare windows size and visibility
		window.setSize(940, 840);
		window.setVisible(true);
	}


	public static void main(String[] args) {
		win = new guia();

	}
}
