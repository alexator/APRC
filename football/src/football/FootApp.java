package football;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JTextField;

import java.awt.Color;

public class FootApp implements ActionListener {

	private static BufferedReader reader;
	private static String line;
	private static ArrayList<String> row_team = new ArrayList<String>();
	private static ArrayList<Team> teams = new ArrayList<Team>();
	private static ArrayList<Team> teams_copy = new ArrayList<Team>();
	private static Team t;
	static JFrame window;
	static FootApp win;
	private JPanel panel;
	private JPanel dialogPanel;
	private JTable table;
	JScrollPane scrollPane1;
	JScrollPane scrollPane2;
	private JTextField txtfGamesPlayed;
	private JTextField txtfPoint;
	private JTextField txtfGoalsScored;
	private JTextField txtfGoalsConceded;
	private JList<Team> teamsList;
	private JLabel lblGamesPlayed;
	private JLabel lblPoints;
	private JLabel lblGoalsScored;
	private JLabel lblGoalsConceded;
	private JLabel lblTeams;
	private JButton btnGetStats;
	private JButton btnSave;
	private DefaultListModel<Team> modelTeams;
	private JButton btnSort;
	private JTextField txtfHome;
	private JTextField txtfAway;
	private JTextField hField;
	private JTextField aField;
	private JLabel lblVs;
	private JLabel lblHomeTeam;
	private JLabel lblAwayTeam;
	private JButton btnGetHome;
	private JButton btnSetAway;
	private JButton btnSetScores;
	private int home;
	private int away;

	private FootApp() {

		setupGUI();
	}

	public void setupGUI() {

		window = new JFrame("Football Application");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		window.setContentPane(panel);
		panel.setLayout(null);

		btnSave = new JButton("Save content");
		btnSave.setBounds(660, 529, 134, 43);
		btnSave.setActionCommand("save");
		btnSave.addActionListener(this);
		panel.add(btnSave);

		JLabel lblScoreTable = new JLabel("Table of Scores");
		lblScoreTable.setHorizontalAlignment(SwingConstants.CENTER);
		lblScoreTable.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblScoreTable.setBounds(17, 326, 584, 16);
		panel.add(lblScoreTable);

		table = new JTable(row(), head());
		scrollPane1 = new JScrollPane(table);
		scrollPane1.setBounds(17, 354, 631, 177);
		scrollPane1.setViewportView(table);
		panel.add(scrollPane1);

		modelTeams = new DefaultListModel<Team>();
		for (int i = 0; i < teams.size(); i++) {
			modelTeams.addElement(teams.get(i));
		}

		teamsList = new JList<Team>(modelTeams);
		teamsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane2 = new JScrollPane(teamsList);
		scrollPane2.setBounds(17, 38, 147, 177);
		panel.add(scrollPane2);

		lblTeams = new JLabel("Teams");
		lblTeams.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblTeams.setBounds(17, 10, 147, 16);
		panel.add(lblTeams);

		txtfGamesPlayed = new JTextField();
		txtfGamesPlayed.setBounds(283, 38, 134, 28);
		panel.add(txtfGamesPlayed);
		txtfGamesPlayed.setColumns(10);

		txtfPoint = new JTextField();
		txtfPoint.setColumns(10);
		txtfPoint.setBounds(283, 68, 134, 28);
		panel.add(txtfPoint);

		txtfGoalsScored = new JTextField();
		txtfGoalsScored.setColumns(10);
		txtfGoalsScored.setBounds(567, 38, 134, 28);
		panel.add(txtfGoalsScored);

		txtfGoalsConceded = new JTextField();
		txtfGoalsConceded.setColumns(10);
		txtfGoalsConceded.setBounds(567, 68, 134, 28);
		panel.add(txtfGoalsConceded);

		lblGamesPlayed = new JLabel("Games Played");
		lblGamesPlayed.setBounds(190, 40, 93, 16);
		panel.add(lblGamesPlayed);

		lblPoints = new JLabel("Points");
		lblPoints.setBounds(190, 74, 93, 16);
		panel.add(lblPoints);

		lblGoalsScored = new JLabel("Goals Scored");
		lblGoalsScored.setBounds(455, 40, 93, 16);
		panel.add(lblGoalsScored);

		lblGoalsConceded = new JLabel("Goals Conceded");
		lblGoalsConceded.setBounds(455, 74, 107, 16);
		panel.add(lblGoalsConceded);

		btnGetStats = new JButton("Get Stats");
		btnGetStats.setForeground(Color.BLUE);
		btnGetStats.setBounds(190, 108, 134, 43);
		btnGetStats.setActionCommand("stats");
		btnGetStats.addActionListener(this);
		panel.add(btnGetStats);

		btnSort = new JButton("Sort Teams");
		btnSort.setBounds(249, 536, 134, 28);
		btnSort.setActionCommand("sort");
		btnSort.addActionListener(this);
		panel.add(btnSort);

		txtfHome = new JTextField();
		txtfHome.setBounds(455, 196, 134, 28);
		panel.add(txtfHome);
		txtfHome.setColumns(10);

		txtfAway = new JTextField();
		txtfAway.setBounds(649, 196, 134, 28);
		panel.add(txtfAway);
		txtfAway.setColumns(10);

		lblVs = new JLabel("VS");
		lblVs.setHorizontalAlignment(SwingConstants.CENTER);
		lblVs.setForeground(Color.RED);
		lblVs.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblVs.setBounds(587, 201, 61, 16);
		panel.add(lblVs);

		lblHomeTeam = new JLabel("Home Team");
		lblHomeTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblHomeTeam.setBounds(451, 179, 131, 16);
		panel.add(lblHomeTeam);

		lblAwayTeam = new JLabel("Away Team");
		lblAwayTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblAwayTeam.setBounds(649, 179, 134, 16);
		panel.add(lblAwayTeam);

		btnGetHome = new JButton("Set Home");
		btnGetHome.setBounds(465, 226, 117, 29);
		btnGetHome.setActionCommand("home");
		btnGetHome.addActionListener(this);
		panel.add(btnGetHome);

		btnSetAway = new JButton("Set Away");
		btnSetAway.setBounds(659, 226, 117, 29);
		btnSetAway.setActionCommand("away");
		btnSetAway.addActionListener(this);
		panel.add(btnSetAway);

		btnSetScores = new JButton("Set Scores");
		btnSetScores.setBounds(558, 255, 117, 29);
		btnSetScores.setActionCommand("edit");
		btnSetScores.addActionListener(this);
		panel.add(btnSetScores);

		window.setSize(800, 600);
		window.setVisible(true);
	}

	public Object[] head() {
		Object[] h = { "Rank", "Team", "Games Played", "Goals Scored",
				"Goals Conceded", "Points", "Goals Diff" };
		return h;
	}

	public Object[][] row() {
		Object[][] o = new Object[20][7];
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 7; j++) {
				if (j == 0) {
					o[i][j] = "?";
				} else if (j == 1) {
					o[i][j] = teams.get(i).getTeam();
				} else if (j == 2) {
					o[i][j] = teams.get(i).getGamesPlayed();
				} else if (j == 3) {
					o[i][j] = teams.get(i).getGoalsScored();
				} else if (j == 4) {
					o[i][j] = teams.get(i).getGoalsConceded();
				} else if (j == 5) {
					o[i][j] = teams.get(i).getPoints();
				} else if (j == 6) {
					o[i][j] = teams.get(i).getGoalsDiff();
				}
			}
		}
		return o;
	}

	public Object[][] rowSort() {
		Object[][] o = new Object[20][7];
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 7; j++) {
				if (j == 0) {
					o[i][j] = i + 1;
				} else if (j == 1) {
					o[i][j] = teams_copy.get(i).getTeam();
				} else if (j == 2) {
					o[i][j] = teams_copy.get(i).getGamesPlayed();
				} else if (j == 3) {
					o[i][j] = teams_copy.get(i).getGoalsScored();
				} else if (j == 4) {
					o[i][j] = teams_copy.get(i).getGoalsConceded();
				} else if (j == 5) {
					o[i][j] = teams_copy.get(i).getPoints();
				} else if (j == 6) {
					o[i][j] = teams_copy.get(i).getGoalsDiff();
				}
			}
		}
		return o;
	}

	public static void main(String[] args) {

		loadData();
		parseData();
		sortTeams();
		win = new FootApp();
	}

	public static void loadData() {

		try {
			reader = new BufferedReader(new FileReader("premiership.txt"));
			while ((line = reader.readLine()) != null) {
				row_team.add(line);
			}
		} catch (IOException e) {
		}
	}

	public static void parseData() {
		for (int i = 0; i < row_team.size(); i++) {
			String team = row_team.get(i).split(";")[0];
			String games_played = row_team.get(i).split(";")[1];
			String goals_scored = row_team.get(i).split(";")[2];
			String goals_conceded = row_team.get(i).split(";")[3];
			String points = row_team.get(i).split(";")[4];
			int goals_diff = Integer.parseInt(goals_scored)
					- Integer.parseInt(goals_conceded);
			t = new Team(team, Integer.parseInt(games_played),
					Integer.parseInt(goals_scored),
					Integer.parseInt(goals_conceded), Integer.parseInt(points),
					goals_diff);
			teams.add(t);
		}
		for (int i = 0; i < teams.size(); i++) {
			teams_copy.add(teams.get(i));
		}
	}

	public static void newFile(ArrayList<Team> l) {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					"premiership.txt"));
			for (int i = 0; i < l.size(); i++) {
				writer.write(l.get(i).getTeam() + ";"
						+ l.get(i).getGamesPlayed() + ";"
						+ l.get(i).getGoalsScored() + ";"
						+ l.get(i).getGoalsConceded() + ";"
						+ l.get(i).getPoints() + "\n");
			}
			writer.close();
		} catch (IOException e) {
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "save") {
			newFile(teams);
		} else if (e.getActionCommand() == "stats") {
			txtfGamesPlayed.setText(String.valueOf(teamsList.getSelectedValue()
					.getGamesPlayed()));
			txtfGoalsScored.setText(String.valueOf(teamsList.getSelectedValue()
					.getGoalsScored()));
			txtfGoalsConceded.setText(String.valueOf(teamsList
					.getSelectedValue().getGoalsConceded()));
			txtfPoint.setText(String.valueOf(teamsList.getSelectedValue()
					.getPoints()));
		} else if (e.getActionCommand() == "sort") {
			table = new JTable(rowSort(), head());
			scrollPane1.setViewportView(table);
		} else if (e.getActionCommand() == "home") {
			txtfHome.setText(teamsList.getSelectedValue().getTeam());
			teamsList.getSelectedValue();
		} else if (e.getActionCommand() == "away") {
			txtfAway.setText(teamsList.getSelectedValue().getTeam());
		} else if (e.getActionCommand() == "edit") {
			hField = new JTextField(5);
			aField = new JTextField(5);

			dialogPanel = new JPanel();
			dialogPanel.add(new JLabel("Home:"));
			dialogPanel.add(hField);
			dialogPanel.add(Box.createHorizontalStrut(15));
			dialogPanel.add(new JLabel("Away:"));
			dialogPanel.add(aField);

			int result = JOptionPane.showConfirmDialog(null, dialogPanel,
					"Please set the goas for home team and away team ",
					JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				home = Integer.parseInt(hField.getText());
				away = Integer.parseInt(aField.getText());
			}
			if (home >= 0 && away >= 0) {
				for (int i = 0; i < teams.size(); i++) {
					if (teams.get(i).getTeam().equals(txtfHome.getText())) {
						teams.get(i).setGoalsScored(home);
						teams.get(i).setGamesPlayed(1);
						teams.get(i).setGoalsConceded(away);
						if (home > away) {
							teams.get(i).setPoints(3);
						} else if (home == away) {
							teams.get(i).setPoints(3);
						}
						teams.get(i).setGoaldDiff(
								teams.get(i).getGoalsScored()
										- teams.get(i).getGoalsConceded());
					}
					if (teams.get(i).getTeam().equals(txtfAway.getText())) {
						teams.get(i).setGoalsScored(away);
						teams.get(i).setGamesPlayed(1);
						teams.get(i).setGoalsConceded(home);
						if (away > home) {
							teams.get(i).setPoints(3);
						} else if (home == away) {
							teams.get(i).setPoints(3);
						}
						teams.get(i).setGoaldDiff(
								teams.get(i).getGoalsScored()
										- teams.get(i).getGoalsConceded());
					}
				}
				update();
			}
		}

	}

	public void update() {
		row();
		table = new JTable(row(), head());
		scrollPane1.setViewportView(table);

	}

	public static void sortTeams() {

		Collections.sort(teams_copy, new Comparator<Team>() {
			@Override
			public int compare(Team t1, Team t2) {
				if (t2.getPoints() - t1.getPoints() == 0) {
					return t2.getGoalsDiff() - t1.getGoalsDiff();
				} else {
					return t2.getPoints() - t1.getPoints();
				}
			}

		});
	}

}
