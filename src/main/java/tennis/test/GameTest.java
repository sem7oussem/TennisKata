package tennis.test;



import org.junit.jupiter.api.BeforeEach;
import tennis.game.Game;

import org.junit.jupiter.api.Test;
import tennis.game.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

	protected Player player1;
	protected Player player2;


	@BeforeEach
	public void intiatePlayers() {
		player1 = new Player("Player 1");
		player2 = new Player("Player 2");
	}


	@Test
	public void scoreInitializationTest() {

		Game game = new Game(player1,player2);
		assertEquals(0, game.getPlayer1().getGameScore());
		assertEquals(0, game.getPlayer2().getGameScore());
	}

	@Test
	public void scoreIncrementTest() {
		Game game = new Game(player1,player2);
		game.getPlayer1().incrementGameScore();
		game.getPlayer2().incrementGameScore();
		assertEquals(1, game.getPlayer1().getGameScore());
		assertEquals(1, game.getPlayer2().getGameScore());
	}

	@Test
	public void scoreResetTest() {

		Game game = new Game(player1,player2);
		game.getPlayer1().incrementGameScore();
		game.getPlayer2().incrementGameScore();
		game.resetScore();
		assertEquals(0, game.getPlayer1().getGameScore());
		assertEquals(0, game.getPlayer2().getGameScore());
	}

}
