package japrc2013;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

public final class StudyPlannerGUI extends JFrame implements
		StudyPlannerGUIInterface {
	private JButton generateButton;
	private JButton exitButton;
	private JButton btnAddTop;
	private JButton btnDeletTop;
	private JList<String> topicList;
	private JList<String> studyPlan;
	private JLabel topicLabel;
	private JLabel planLabel;
	private JTextField topicSub;
	private JTextField topicLen;
	private JTextArea plan;

	private StudyPlanner planner;

	public StudyPlannerGUI(StudyPlanner simToUse) {
		super("Study Planner");
		setLayout(new FlowLayout());

		this.planner = simToUse;

		generateButton = new JButton("Generate Study Plan");
		generateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				planner.setBlockSize(60);
				planner.setBreakLength(10);
				plan.setText("");// logo tou append prepei na katharisei gia tin
									// periptosi pou ksanapatisoume generate
									// plan
				planner.generateStudyPlan();
			}
		});
		add(generateButton);

		exitButton = new JButton("Exit Program");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		add(exitButton);

		topicLabel = new JLabel("Topics:");
		add(topicLabel);

		String[] data = { "one", "two", "three", "four" };
		topicList = new JList<String>(data);
		topicList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(topicList);

		planLabel = new JLabel("Study Plan:");
		add(planLabel);

		data = new String[] { "one", "two", "three", "four" };
		studyPlan = new JList<String>(data);
		add(studyPlan);

		topicSub = new JTextField();
		topicSub.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				topicSub.setText("");
			}
		});
		add(topicSub);

		topicLen = new JTextField();
		topicLen.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				topicLen.setText("");
			}
		});
		add(topicLen);

		btnAddTop = new JButton("Add the new Topic");
		btnAddTop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!topicSub.getText().equals("")
						&& !topicLen.getText().equals("")) {
					planner.addTopic(topicSub.getText(),
							Integer.parseInt(topicLen.getText()));
					updateDisplay();
				} else {
					throw new StudyPlannerException(
							"No input in fields topic's subject and tpoic's lenght!");
				}
			}
		});
		add(btnAddTop);

		btnDeletTop = new JButton("Delete Topic");
		btnDeletTop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (topicList.getSelectedValue() != null) {
					String name = topicList.getSelectedValue().split(" \\(")[0];
					planner.deleteTopic(name);
					updateDisplay();
				} else {
					throw new StudyPlannerException("No topic selection");
				}
			}
		});
		add(btnDeletTop);

		plan = new JTextArea();
		add(plan);

	}

	@Override
	public void notifyModelHasChanged() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				updateDisplay();
			}
		});
	}

	private void updateDisplay() {
		if (planner == null) {
			// nothing to update from, so do nothing
		} else {
			List<String> topicData = new ArrayList<String>();
			for (TopicInterface t : planner.getTopics()) {
				topicData.add(t.getSubject() + " (" + t.getDuration() + ")");
			}
			topicList.setListData(topicData.toArray(new String[1]));

			List<String> eventData = new ArrayList<String>();
			Calendar index = new GregorianCalendar();
			SimpleDateFormat d_f = new SimpleDateFormat("HH:mm");
			String time;

			for (StudyBlockInterface ev : planner.getStudyPlan()) {
				time = d_f.format(index.getTime());

				eventData.add(time + " ) " + ev.getTopic() + " ("
						+ ev.getDuration() + ")");
				index.add(Calendar.MINUTE, ev.getDuration());

			}
			for (int i = 0; i < eventData.size(); i++) {
				plan.append(eventData.get(i) + "\n"); // ti gientai otn kaneis
														// deuteri fora generate
														// plan??
			}
			studyPlan.setListData(eventData.toArray(new String[1]));

		}

	}

	public static void main(String[] args) {
		StudyPlanner planner = new StudyPlanner();
		planner.addTopic("Java file handling", 480);
		planner.addTopic("CSYA", 720);

		StudyPlannerGUI gui = new StudyPlannerGUI(planner);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(275, 180);

		planner.setGUI(gui);

		gui.setVisible(true);
		gui.updateDisplay();
	}

}
