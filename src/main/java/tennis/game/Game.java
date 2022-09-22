package tennis.game;

public class Game {

	protected static final String[] possible_game_scores = new String[] { "Love", "Fifteen", "Thirty", "Fourty" };

	private Player player1;
	private Player player2;

	public Game(Player player1,Player player2) {
		this.player1=player1;
		this.player2=player2;
		this.player1.setGameScore(0);
		this.player2.setGameScore(0);
	}

	public void resetScore()
	{
		this.player1.resetGameScore();
		this.player2.resetGameScore();
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

}
