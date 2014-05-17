package football;

public class Team {
	
	private String team;
	private int games_played;
	private int goals_scored;
	private int goals_conceded;
	private int points;
	private int goals_diff;
	
	public Team(String t, int gp, int gs, int gc, int p, int gd) {
		team = t;
		games_played = gp;
		goals_scored = gs;
		goals_conceded = gc;
		points = p;
		goals_diff = gd; 
	}
	
	public String getTeam() {
		return team;
	}
	
	public int getGamesPlayed() {
		return games_played;
	}
	
	public void setGamesPlayed(int gp) {
		games_played = games_played + gp;
	}
	
	public int getGoalsScored() {
		return goals_scored;
	}
	
	public void setGoalsScored(int gs) {
		goals_scored = goals_scored + gs;
	}
	
	public int getGoalsConceded() {
		return goals_conceded;
	}
	
	public void setGoalsConceded(int gc) {
		goals_conceded = goals_conceded + gc;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int po) {
		points = points + po ;
	}
	
	public int getGoalsDiff() {
		return goals_diff;
	}
	
	public void setGoaldDiff(int gdf) {
		goals_diff = gdf;
	}
	
	public String toString() {
		return team; 
	}
	
	
}
