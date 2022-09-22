package tennis.test;


import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import tennis.game.Match;
import tennis.game.Player;

public class MatchTest {
	Player p1 = new Player("Player1");
	Player p2 = new Player("Player2");

	/**
	 * Helper method for tests simulation Makes player win n points
	 * 
	 * @param match
	 * @param player
	 * @param n
	 */
	public void winPoints(Match match, Player player, int n) {
		for (int i = 0; i < n; i++) {
			match.winPoint(player);
		}
	}

	/**
	 * Helper method for tests simulation. it makes player win n games
	 * 
	 * @param match
	 * @param player
	 * @param n
	 */
	public void winGames(Match match, Player player, int n) {
		for (int i = 0; i < n; i++) {
			match.winGame(player);
		}
	}
	protected static final List<String> testingScores = Arrays.asList(new String[] {
			// games score expected
			// sipmle game playing/winning

			"Player1\t|0\t|Fifteen\n" + // test 1
			"Player2\t|0\t|Love\n",

			"Player1\t|0\t|Thirty\n" + // test 2
			"Player2\t|0\t|Love\n",

			"Player1\t|0\t|Fourty\n" + // test 3
			"Player2\t|0\t|Love\n",

			"Player1\t|1\t|Love\n" + // test 4
			"Player2\t|0\t|Love\n",

			// deuce
			"Player1\t|1\t|DEUCE\n" + // test 5
			"Player2\t|0\t|DEUCE\n",

			// deuce rules
			"Player1\t|1\t|Fourty\n" + // test 6
			"Player2\t|0\t|ADV\n",
			
			"Player1\t|1\t|DEUCE\n" + // test 7
			"Player2\t|0\t|DEUCE\n",
			
			"Player1\t|1\t|ADV\n" + // test 8
			"Player2\t|0\t|Fourty\n",
			
			"Player1\t|2\t|Love\n" + // test 9
			"Player2\t|0\t|Love\n",

			// simple set winning
			"#####\tPlayer1 won the game\t####\n" + Match.NORMAL_SCORE_PREFIX + 
			"Player1\t|6\t|Love\n" + // test 10
			"Player2\t|0\t|Love\n",

			// tie break
			"Player1\t|6\t|Love\t|0\n" + // test 11
			"Player2\t|6\t|Love\t|0\n",

			// tie break rules
			"Player1\t|6\t|Love\t|1\n" + // test 12
			"Player2\t|6\t|Love\t|0\n",
			
			"Player1\t|6\t|Love\t|6\n" + // test 13
			"Player2\t|6\t|Love\t|1\n",
			
			"#####\tPlayer1 won the game\t####\n" + Match.TIE_BREAK_SCORE_PREFIX + 
			"Player1\t|7\t|Love\t|7\n" + // test14
			"Player2\t|6\t|Love\t|1\n" });

	@Test
	public void should_return_set_score_zero_for_both_game_score_p1_fifteen_p2_love() {
		Match match = new Match(p1, p2);
		match.winPoint(p1);// 15 - 0

		assertEquals(Match.NORMAL_SCORE_PREFIX + testingScores.get(0), match.getScore());
	}

	@Test
	public void should_return_set_score_zero_for_both_game_score_p1_thirty_p2_love() {
		Match match = new Match(p1, p2);
		match.winPoint(p1);// 15 - 0
		match.winPoint(p1);// 30 - 0

		assertEquals(Match.NORMAL_SCORE_PREFIX + testingScores.get(1), match.getScore());
	}

	@Test
	public void should_return_set_score_zero_for_both_game_score_p1_fourty_p2_love() {
		Match match = new Match(p1, p2);
		match.winPoint(p1);// 15 - 0
		match.winPoint(p1);// 30 - 0
		match.winPoint(p1);// 40 - 0

		assertEquals(Match.NORMAL_SCORE_PREFIX + testingScores.get(2), match.getScore());
	}

	@Test
	public void should_return_set_score_p1_one_p2_zero_game_score_both_love() {
		Match match = new Match(p1, p2);
		match.winPoint(p1);// 15 - 0
		match.winPoint(p1);// 30 - 0
		match.winPoint(p1);// 40 - 0
		match.winPoint(p1);// 1 - 0 :: 0 - 0

		assertEquals(Match.NORMAL_SCORE_PREFIX + testingScores.get(3), match.getScore());
	}

	@Test
	public void should_return_set_score_p1_one_p2_zero_game_score_both_deuce() {
		Match match = new Match(p1, p2);

		winGames(match, p1, 1);// 1 - 0 :: 0 - 0
		winPoints(match, p1, 3);// 1 - 0 :: 40 - 0
		winPoints(match, p2, 3);// 1 - 0 :: 40 - 40

		assertEquals(Match.NORMAL_SCORE_PREFIX + testingScores.get(4), match.getScore());
	}

	@Test
	public void should_return_set_score_p1_one_p2_zero_game_score_p1_fourty_p2_adv() {
		Match match = new Match(p1, p2);
		winGames(match, p1, 1);// 1 - 0 :: 0 - 0
		winPoints(match, p1, 3);// 1 - 0 :: 40 - 0
		winPoints(match, p2, 3);// 1 - 0 :: 40 - 40
		winPoints(match, p2, 1);// 1 - 0 :: 40 - ADV

		assertEquals(Match.NORMAL_SCORE_PREFIX + testingScores.get(5), match.getScore());
	}

	@Test
	public void should_return_set_score_p1_one_p2_zero_game_score_both_deuce_afetr_adv() {
		Match match = new Match(p1, p2);
		winGames(match, p1, 1);// 1 - 0 :: 0 - 0
		winPoints(match, p1, 3);// 1 - 0 :: 40 - 0
		winPoints(match, p2, 3);// 1 - 0 :: 40 - 40
		winPoints(match, p1, 1);// 1 - 0 :: ADV - 40
		winPoints(match, p2, 1);// 1 - 0 :: 40 - 40

		assertEquals(Match.NORMAL_SCORE_PREFIX + testingScores.get(6), match.getScore());
	}

	@Test
	public void should_return_set_score_p1_one_p2_zero_game_score_p1_adv_p2_fourty() {
		Match match = new Match(p1, p2);
		winGames(match, p1, 1);// 1 - 0 :: 0 - 0
		winPoints(match, p1, 3);// 1 - 0 :: 40 - 0
		winPoints(match, p2, 3);// 1 - 0 :: 40 - 40
		winPoints(match, p1, 1);// 1 - 0 :: ADV - 40
		winPoints(match, p2, 1);// 1 - 0 :: 40 - 40
		winPoints(match, p1, 1);// 1 - 0 :: ADV - 40

		assertEquals(Match.NORMAL_SCORE_PREFIX + testingScores.get(7), match.getScore());
	}

	@Test
	public void should_return_set_score_p1_two_p2_zero_game_score_both_love() {
		Match match = new Match(p1, p2);
		winGames(match, p1, 2);// 2 - 0 :: 0 - 0

		assertEquals(Match.NORMAL_SCORE_PREFIX + testingScores.get(8), match.getScore());
	}

	@Test
	public void should_return_set_score_p1_six_p2_zero_game_score_both_love_and_p1_winner() {
		Match match = new Match(p1, p2);
		winGames(match, p1, 6);// 6 - 0 :: 0 - 0

		assertEquals(testingScores.get(9), match.getScore());
	}

	@Test
	public void should_return_set_score_p1_six_p2_six_game_score_both_love_tb_score_zero_both() {
		Match match = new Match(p1, p2);
		winGames(match, p1, 5);// 5 - 0 :: 0 - 0 :: 0 - 0
		winGames(match, p2, 5);// 5 - 5 :: 0 - 0 :: 0 - 0
		winGames(match, p1, 1);// 6 - 5 :: 0 - 0 :: 0 - 0
		winGames(match, p2, 1);// 6 - 6 :: 0 - 0 :: 0 - 0

		assertEquals(Match.TIE_BREAK_SCORE_PREFIX + testingScores.get(10), match.getScore());
	}

	@Test
	public void should_return_set_score_both_six_game_score_both_love_tb_score_p1_one_p2_zero() {
		Match match = new Match(p1, p2);
		winGames(match, p1, 5);// 5 - 0 :: 0 - 0 :: 0 - 0
		winGames(match, p2, 5);// 5 - 5 :: 0 - 0 :: 0 - 0
		winGames(match, p1, 1);// 6 - 5 :: 0 - 0 :: 0 - 0
		winGames(match, p2, 1);// 6 - 6 :: 0 - 0 :: 0 - 0
		winPoints(match, p1, 1);// 6 - 6 :: 0 - 0 :: 1 - 0
		assertEquals(Match.TIE_BREAK_SCORE_PREFIX + testingScores.get(11), match.getScore());
	}

	@Test
	public void should_return_set_score_both_six_game_score_both_love_tb_score_p1_six_p2_one() {
		Match match = new Match(p1, p2);
		winGames(match, p1, 5);// 5 - 0 :: 0 - 0 :: 0 - 0
		winGames(match, p2, 5);// 5 - 5 :: 0 - 0 :: 0 - 0
		winGames(match, p1, 1);// 6 - 5 :: 0 - 0 :: 0 - 0
		winGames(match, p2, 1);// 6 - 6 :: 0 - 0 :: 0 - 0
		winPoints(match, p1, 6);// 6 - 6 :: 0 - 0 :: 6 - 0
		winPoints(match, p2, 1);// 6 - 6 :: 0 - 0 :: 6 - 1
		assertEquals(Match.TIE_BREAK_SCORE_PREFIX + testingScores.get(12), match.getScore());
	}

	@Test
	public void should_return_set_score_both_six_game_score_both_love_tb_score_p1_seven_p2_one_and_p3_winner() {
		Match match = new Match(p1, p2);
		winGames(match, p1, 5);// 5 - 0 :: 0 - 0 :: 0 - 0
		winGames(match, p2, 5);// 5 - 5 :: 0 - 0 :: 0 - 0
		winGames(match, p1, 1);// 6 - 5 :: 0 - 0 :: 0 - 0
		winGames(match, p2, 1);// 6 - 6 :: 0 - 0 :: 0 - 0
		winPoints(match, p2, 1);// 6 - 6 :: 0 - 0 :: 0 - 1
		winPoints(match, p1, 7);// 7 - 6 :: 0 - 0 :: 7 - 1
		assertEquals(testingScores.get(13), match.getScore());
	}
}
