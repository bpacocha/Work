package mygroup.myproject.model;

import java.util.List;

public class Game {

	private String gameId;
	private String clockTime;
	private long playId;
	private List<String> play;
public Game() {
        
    }

    public Game(String clock, long games, List<String> plays ) {
        super();
        this.clockTime = clock;
        this.playId = games;
     
    }
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String games) {
		this.gameId = games;
	}
	public String getClock() {
		return clockTime;
	}
	public void setClock(String clock) {
		this.clockTime = clock;
	}
	public long getPlayId() {
		return playId;
	}
	public void setPlayId(long playId) {
		this.playId = playId;
	}
	public List<String> getPlays() {
		return play;
	}
	public void setPlays(List<String> plays) {
		this.play = plays;
	}
	@Override
	public String toString() {
		return "Game [games=" + gameId + ", clock=" + clockTime + ", playID=" + playId + ", plays=" + play + "]";
	}
	
}
