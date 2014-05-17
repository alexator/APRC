package mp3player;

public class Mp3 {

	private int id;
	private String title;
	private String artist;
	
	public Mp3(int i, String t, String a) {
		id = i;
		title = t;
		artist = a;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String t) {
		title = t;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public void setArtist(String a) {
		artist = a;
	}
	
	public int getId() {
		return id;
	}
	
	public String toString() {
		return title;
	}
	
}
