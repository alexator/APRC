package mp4player;

import java.util.ArrayList;

public class PlayList {
	
	private int id;
	private String name;
	private ArrayList<Mp3> songs= new ArrayList<Mp3>();
	
	public PlayList(String n, int i) {
		
		id = i;
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public void addSong(Mp3 s) {
		songs.add(s);
	}
	
	public void removeSong(int i) {
		for(int j = 0; j < songs.size(); j++){
			if(songs.get(j).getId() == i ){
				songs.remove(j);
			}
		}
	}
	
	public ArrayList<Mp3> getSongs() {
		return songs;
	}
	
	public int getId() {
		return id;
	}
	
	public void removeSong() {
		//
	}
	
	public String toString() {
		return name;
	}
}
