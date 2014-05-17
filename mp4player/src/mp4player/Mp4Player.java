package mp4player;

import javax.swing.JButton;

public class Mp4Player extends Player{
	
	JButton btnaddMp4;

	@Override
	public void setupGUI() {
		btnaddMp4 = new JButton("Add Mp4 Song");
		btnaddMp4.setActionCommand("creatlist");
		btnaddMp4.setBounds(400, 150, 134, 40);
		btnaddMp4.addActionListener(this);
		panel.add(btnaddMp4);
	}
	
	
}
