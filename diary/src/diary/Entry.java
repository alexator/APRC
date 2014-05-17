package diary;

public class Entry {

	private String title;
	private String time;
	private String content;
	
	public Entry(String t, String d, String c){
		title = t;
		time = d;
		content = c;
	}
	
	public String toString() {
		return "Time of entry: " + time + "  Title: " + title + "\n" + "Content: " + content;
	}
}
