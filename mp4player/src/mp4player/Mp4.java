package mp4player;

public class Mp4 extends Mp3 {
	
	
	private int memory ;
	
	public Mp4 (int i, String t, String a, int mem ) {
		super (i,t,a) ;
		memory = mem ;
	}
	
	public int getMem() {
		return memory;
	}
	
	public void setMem(int m) {
		memory = m;
	}
	
}
