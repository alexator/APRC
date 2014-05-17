package mp4player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Font;
import java.awt.Color;

public class Player implements ActionListener {

	JFrame window;
	public static Player mp3;
	JList<Mp3> songDatabase;
	JList<PlayList> playlistDatabase;
	JList<Mp3> list_3;
	JList<Mp3> playlistsongs;
	JList<String> search;
	public static Mp3 s;
	public static Mp3 s1;
	public static Mp3 s2;
	public static Mp3 s3;
	public static Mp3 s4;
	public static PlayList p;
	public static ArrayList<Mp3> songs = new ArrayList<Mp3>();
	public static DefaultListModel<Mp3> model1;
	public static DefaultListModel<String> model4 = new DefaultListModel<String>();
	public static DefaultListModel<Mp3> model3 = new DefaultListModel<Mp3>();
	public static DefaultListModel<PlayList> model2 = new DefaultListModel<PlayList>();
	public static ArrayList<PlayList> playlists = new ArrayList<PlayList>();

	JLabel lblSongList;
	JLabel lblSongTitle;
	JLabel lblSongArtist;
	JLabel lblPlaylists;
	JLabel lblPlaylistSongs;
	JLabel lblPlaylistName;
	JLabel lblSearchResults;
	JTextField songTitle;
	JTextField songArtist;
	JTextField playlistName;
	JTextArea textArea;
	JTextArea searchResult;
	JButton btnaddToPlaylist;
	JButton btnaddS;
	JButton btnedit;
	JButton btnfind;
	JButton btndelete;
	JButton btnAddtoPlaylist;
	JButton btnDeletePlaylist;
	JButton btnDeleteSongFrom;
	JPanel panel;

	int idplaylist = 0;
	int idsong = 4;

	public Player() {
		setupGUI();
	}

	/*
	 * This method creates and initialize the GUI of the program.
	 */

	public void setupGUI() {

		/* Main Window */
		window = new JFrame("MP3 Player");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Main Panel of the window */
		panel = new JPanel();
		window.setContentPane(panel);
		panel.setLayout(null); // Set layout to null because of the use of
								// Absolute layout

		// Label of song database
		lblSongList = new JLabel("Songs:");
		lblSongList.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblSongList.setBounds(10, 6, 53, 20);
		panel.add(lblSongList);

		// Creation of list of song database
		songDatabase = new JList<Mp3>(model1);
		songDatabase.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		songDatabase.setBounds(10, 32, 306, 158);
		panel.add(songDatabase);
		songDatabase.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					if (songDatabase.getSelectedValue() != null) {
						songTitle.setText(songDatabase.getSelectedValue()
								.getTitle());
						songArtist.setText(songDatabase.getSelectedValue()
								.getArtist());
					}
				}
			}
		});

		// Creation of the textField for the title of song
		songTitle = new JTextField();
		songTitle.setBounds(396, 30, 371, 32);
		songTitle.setColumns(10);
		panel.add(songTitle);

		// Creation of the textField for the artist of song
		songArtist = new JTextField();
		songArtist.setBounds(396, 74, 371, 32);
		songArtist.setColumns(10);
		panel.add(songArtist);

		// Label for the header of song's title textField
		lblSongTitle = new JLabel("Title:");
		lblSongTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblSongTitle.setBounds(338, 35, 40, 20);
		panel.add(lblSongTitle);

		// Label for the header of song's artist textField
		lblSongArtist = new JLabel("Artist:");
		lblSongArtist.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblSongArtist.setBounds(338, 79, 46, 20);
		panel.add(lblSongArtist);

		// Label for the header of playlist's textArea
		lblPlaylists = new JLabel("Playlists:");
		lblPlaylists.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblPlaylists.setBounds(10, 256, 79, 20);
		panel.add(lblPlaylists);

		playlistDatabase = new JList<PlayList>(model2);
		playlistDatabase.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		playlistDatabase.setBounds(10, 281, 306, 124);
		panel.add(playlistDatabase);

		playlistsongs = new JList<Mp3>(model3);
		playlistsongs.setBounds(10, 455, 306, 110);
		playlistsongs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.add(playlistsongs);

		playlistDatabase.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				model3 = new DefaultListModel<Mp3>();
				if (!arg0.getValueIsAdjusting()) {
					if (playlistDatabase.getSelectedValue() != null) {
						for (int i = 0; i < playlistDatabase.getSelectedValue()
								.getSongs().size(); i++) {
							model3.addElement(playlistDatabase
									.getSelectedValue().getSongs().get(i));
						}
						playlistsongs.setModel(model3);
						playlistsongs.invalidate();
					
						
					}
				}
			}
		});

		lblPlaylistSongs = new JLabel("Playlist Songs:");
		lblPlaylistSongs.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblPlaylistSongs.setBounds(10, 429, 118, 20);
		panel.add(lblPlaylistSongs);

		// Button that adds songs to the playList
		btnaddS = new JButton("Add Song");
		btnaddS.setBounds(355, 141, 134, 40);
		btnaddS.setActionCommand("addsong");
		btnaddS.addActionListener(this);
		panel.add(btnaddS);

		// Button that creates playList
		btnaddToPlaylist = new JButton("Create Playlist");
		btnaddToPlaylist.setBounds(461, 348, 134, 40);
		btnaddToPlaylist.setActionCommand("creatlist");
		btnaddToPlaylist.addActionListener(this);
		panel.add(btnaddToPlaylist);

		btnedit = new JButton("Edit Mp3 Song");
		btnedit.setBounds(501, 141, 134, 40);
		btnedit.setActionCommand("edit");
		btnedit.addActionListener(this);
		panel.add(btnedit);

		btnfind = new JButton("Find Songs");
		btnfind.setBounds(648, 141, 134, 40);
		btnfind.setActionCommand("find");
		btnfind.addActionListener(this);
		panel.add(btnfind);

		btndelete = new JButton("Delete Mp3");
		btndelete.setBounds(10, 197, 128, 40);
		btndelete.setActionCommand("deleteS");
		btndelete.addActionListener(this);
		panel.add(btndelete);

		btnAddtoPlaylist = new JButton("Add Song to Playlist");
		btnAddtoPlaylist.setBounds(177, 197, 139, 40);
		btnAddtoPlaylist.setActionCommand("addtoPl");
		btnAddtoPlaylist.addActionListener(this);
		panel.add(btnAddtoPlaylist);

		btnDeletePlaylist = new JButton("Delete Playlist");
		btnDeletePlaylist.setBounds(633, 348, 134, 40);
		btnDeletePlaylist.setActionCommand("deleteP");
		btnDeletePlaylist.addActionListener(this);
		panel.add(btnDeletePlaylist);

		btnDeleteSongFrom = new JButton("Delete Playlist Song");
		btnDeleteSongFrom.setBounds(10, 577, 139, 40);
		btnDeleteSongFrom.setActionCommand("deletePlSo");
		btnDeleteSongFrom.addActionListener(this);
		panel.add(btnDeleteSongFrom);

		playlistName = new JTextField("");
		playlistName.setBounds(461, 291, 306, 32);
		panel.add(playlistName);
		playlistName.setColumns(10);

		lblPlaylistName = new JLabel("Playlist Name:");
		lblPlaylistName.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblPlaylistName.setBounds(355, 298, 103, 16);
		panel.add(lblPlaylistName);

		search = new JList<String>();
		search.setForeground(Color.GREEN);
		search.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		search.setBackground(Color.BLACK);
		search.setBounds(440, 455, 327, 172);
		panel.add(search);

		lblSearchResults = new JLabel("Search Results:");
		lblSearchResults.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblSearchResults.setBounds(440, 426, 118, 26);
		panel.add(lblSearchResults);

		window.setSize(800, 680);
		window.setVisible(true);

	}

	/** Action Listener for the JButton */
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == "addsong") {
			if ("".equals(songTitle.getText())
					|| "".equals(songArtist.getText())) {
				JOptionPane.showMessageDialog(window,
						"Please fill both Artist and Title fileds", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {
				idsong = idsong + 1;
				s = new Mp3(idsong, songTitle.getText(), songArtist.getText());
				model1.addElement(s);
				songDatabase.setModel(model1);
				songDatabase.invalidate();

			}

		} else if (e.getActionCommand() == "creatlist") {
			if ("".equals(playlistName.getText())) {
				JOptionPane
						.showMessageDialog(
								window,
								"Please type the name of the Playlist that you want to create",
								"Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				idplaylist = idplaylist + 1;

				model2.addElement(new PlayList(playlistName.getText(),
						idplaylist));
			}
		} else if (e.getActionCommand() == "addtoPl") {
			if (playlistDatabase.getSelectedValue() != null) {
				playlistDatabase.getSelectedValue().addSong(
						songDatabase.getSelectedValue());
				playlistsongs.setModel(model3);
				playlistsongs.invalidate();
				
			} else {
				JOptionPane.showMessageDialog(window,
						"No Playlist is selected", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (e.getActionCommand() == "edit") {
			if ("".equals(songTitle.getText())
					|| "".equals(songArtist.getText()) || songDatabase.getSelectedValue() == null) {
				JOptionPane.showMessageDialog(window,
						"Please select the song you want to edit", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {
				songDatabase.getSelectedValue().setTitle(songTitle.getText());
				songDatabase.getSelectedValue().setArtist(songArtist.getText());
				if(songDatabase.getSelectedValue() != null && playlistDatabase.getSelectedValue() !=null) {
				songDatabase.setModel(model1);
				playlistsongs.setModel(model3);
				songDatabase.invalidate();
				playlistsongs.invalidate();
				
				}
			}
		} else if (e.getActionCommand() == "deleteS") {
			if (songDatabase.getSelectedValue() == null) {
				JOptionPane.showMessageDialog(window,
						"Please select the song you want to delete", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {
				System.out.println(songDatabase.getSelectedValue().getId());
				System.out.println(playlistDatabase.getSelectedValue()
						.getSongs());
				playlistDatabase.getSelectedValue().removeSong(
						songDatabase.getSelectedValue().getId());
				removeS(model3, songDatabase.getSelectedValue().getId());
				removeS(model1, songDatabase.getSelectedValue().getId());
				
				songDatabase.setModel(model1);
				playlistsongs.setModel(model3);
				songDatabase.invalidate();
				playlistsongs.invalidate();
				songTitle.setText("");
				songArtist.setText("");
			}

		} else if (e.getActionCommand() == "deletePlSo") {
			if (playlistsongs.getSelectedValue() == null) {
				JOptionPane
						.showMessageDialog(
								window,
								"Please select the song you want to delete from the Playlist",
								"Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				playlistDatabase.getSelectedValue().removeSong(
						playlistsongs.getSelectedValue().getId());
				removeS(model3, playlistsongs.getSelectedValue().getId());
				playlistsongs.setModel(model3);
				playlistsongs.invalidate();
			}
		} else if (e.getActionCommand() == "deleteP") {
			if (playlistDatabase.getSelectedValue() == null) {
				JOptionPane.showMessageDialog(window,
						"Please select the Playlist you want to delete",
						"Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				removeP(model2, playlistDatabase.getSelectedValue().getId());
				playlistDatabase.setModel(model2);
				playlistsongs.setModel(model3);
				playlistsongs.invalidate();
			}
		} else if (e.getActionCommand() == "find") {
			if ("".equals(songTitle.getText())
					&& "".equals(songArtist.getText())) {
				JOptionPane.showMessageDialog(window,
						"Please fill the Title or the Artist filed", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {

				model4.removeAllElements();
				findMp3(songTitle.getText(), songArtist.getText());
				search.setModel(model4);
				songTitle.setText("");
				songArtist.setText("");
			}
		}

	}

	private void findMp3(String t, String a) {
		for (int i = 0; i < model1.size(); i++) {
			if (t.equalsIgnoreCase(model1.get(i).getTitle())
					&& a.equalsIgnoreCase(model1.get(i).getArtist())) {
				model4.addElement(model1.get(i).getArtist() + " - "
						+ model1.get(i).getTitle());
			} else {

				if ("".equals(t)) {
				} else {
					if (t.equalsIgnoreCase(model1.get(i).getTitle())) {
						model4.addElement(model1.get(i).getArtist() + " - "
								+ model1.get(i).getTitle());
					}
				}
				if ("".equals(a)) {
				} else {
					if (a.equalsIgnoreCase(model1.get(i).getArtist())) {
						model4.addElement(model1.get(i).getArtist() + " - "
								+ model1.get(i).getTitle());
					}
				}
			}
		}
	}

	private void removeS(DefaultListModel<Mp3> m, int id) {
		for (int i = 0; i < m.size(); i++) {
			if (m.get(i).getId() == id) {
				m.remove(i);
			}
		}
	}

	private void removeP(DefaultListModel<PlayList> p, int id) {
		for (int i = 0; i < p.size(); i++) {
			if (p.get(i).getId() == id) {
				p.remove(i);
			}
		}
	}

	// Main

	public static void main(String[] args) {
		createSongs();
		songList();
		mp3 = new Player();

	}

	// Method that creates mp3 instances
	public static void createSongs() {

		s1 = new Mp3(1, "Creep", "Radiohead");
		s2 = new Mp3(2, "Somebody told me", "Killers");
		s3 = new Mp3(3, "Fade to black", "Metallica");
		s4 = new Mp3(4, "Madness", "Muse");
	}

	// Method that adds the selected songs to an ArrayList<Mp3>
	public static void addSongs(Mp3 s) {
		songs.add(s);
		System.out.println(songs);
	}

	// Method that creates songs database Model
	public static void songList() {
		model1 = new DefaultListModel<Mp3>();
		model1.addElement(s1);
		model1.addElement(s2);
		model1.addElement(s3);
		model1.addElement(s4);
	}

	// Method that creates playList instances
	public static void createPlaylist(String n, int i) {

		p = new PlayList(n, i);
	}

}
