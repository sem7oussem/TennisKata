package tennis.game;

public class Player {

	private final String name;
	private int gameScore;
	private int setScore;
	private int tieBreakScore;

	public Player(String name) {
		this.name = name;
		this.gameScore=0;
		this.setScore=0;
		this.tieBreakScore=0;
	}

	public void incrementSetScore (){
		this.setSetScore(this.getSetScore()+1);
	}

	public void incrementGameScore (){
		this.setGameScore(this.getGameScore()+1);
	}

	public void incrementTieBreakScore (){
		this.setTieBreakScore(this.getTieBreakScore()+1);
	}

	public void resetGameScore(){
		this.setGameScore(0);
	}
	public String getName() {
		return name;
	}


	public int getGameScore() {
		return gameScore;
	}

	public void setGameScore(int gameScore) {
		this.gameScore = gameScore;
	}

	public int getSetScore() {
		return setScore;
	}

	public void setSetScore(int setScore) {
		this.setScore = setScore;
	}

	public int getTieBreakScore() {
		return tieBreakScore;
	}

	public void setTieBreakScore(int tieBreakScore) {
		this.tieBreakScore = tieBreakScore;
	}
}
