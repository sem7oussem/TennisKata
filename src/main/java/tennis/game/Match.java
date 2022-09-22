package tennis.game;

public class Match {

    public static final String BASE_SCORE_PREFIX = "Player\t|Set\t|Game|";
    public static final String NORMAL_SCORE_PREFIX = BASE_SCORE_PREFIX + "\n";
    public static final String TIE_BREAK_SCORE_PREFIX = BASE_SCORE_PREFIX + "\t|TieBreak\n";
    public static final String USER_NOT_FOUND_MESSAGE = "There is no player with name ";

    private boolean tieBreak;
    private Game game;
    private Player winner;

    public Match(Player player1, Player player2) {
        this.game = new Game(player1, player2);
        this.winner = null;
        this.tieBreak = false;
    }

    public String getScore() {
        Player player1 = this.game.getPlayer1();
        Player player2 = this.game.getPlayer2();
        int p1gameScore = player1.getGameScore();
        int p2gameScore = player2.getGameScore();
        String p1ScoreString = player1.getName() + "\t|";
        String p2ScoreString = player2.getName() + "\t|";
        p1ScoreString += player1.getSetScore() + "\t|";
        p2ScoreString += player2.getSetScore() + "\t|";
        if (p1gameScore < 4 && p2gameScore < 4 && (p1gameScore + p2gameScore != 6)) {
            p1ScoreString += Game.possible_game_scores[p1gameScore];
            p2ScoreString += Game.possible_game_scores[p2gameScore];

        } else {
            int diff = p1gameScore - p2gameScore;
            if (diff == 0) {
                p1ScoreString += "DEUCE";
                p2ScoreString += "DEUCE";
            } else if (diff == 1) {
                p1ScoreString += "ADV";
                p2ScoreString += Game.possible_game_scores[3];
            } else if (diff == -1) {
                p1ScoreString += Game.possible_game_scores[3];
                p2ScoreString += "ADV";
            }
        }
        String score;
        if (this.tieBreak) {
            p1ScoreString += "\t|" + player1.getTieBreakScore() + "\n";
            p2ScoreString += "\t|" + player2.getTieBreakScore() + "\n";
            score = TIE_BREAK_SCORE_PREFIX + p1ScoreString + p2ScoreString;
        } else {
            p1ScoreString += "\n";
            p2ScoreString += "\n";
            score = NORMAL_SCORE_PREFIX + p1ScoreString + p2ScoreString;
        }
        if (winner != null) {
            score = "#####\t" + winner.getName() + " won the match\t####\n" + score;
        }
        return score;
    }

    public void updateScore(String trigger) {
        Player player1 = this.game.getPlayer1();
        Player player2 = this.game.getPlayer2();
        if (trigger.equals("game")) {
            int p1SetScore = player1.getSetScore();
            int p2SetScore = player2.getSetScore();
            if ((p1SetScore == 6 && p2SetScore <= 4) || (p1SetScore == 7)) {
                winMatch(player1);
            } else if ((p2SetScore == 6 && p1SetScore <= 4) || (p2SetScore == 7)) {
                winMatch(player2);
            } else if (p1SetScore == 6 && p2SetScore == 6) {
                this.tieBreak = true;
            }

        } else {
            if (this.tieBreak) {
                int p1TieBreakScore = player1.getTieBreakScore();
                int p2TieBreakScore = player2.getTieBreakScore();
                if (p1TieBreakScore == 7) {
                    winGame(player1);
                } else if (p2TieBreakScore == 7) {
                    winGame(player2);
                }
            } else {
                int p1gameScore = player1.getGameScore();
                int p2gameScore = player2.getGameScore();
                int diff = p1gameScore - p2gameScore;
                if (p1gameScore > 3 || p2gameScore > 3) {

                    if (diff > 1) {
                        winGame(player1);
                        this.game.resetScore();
                    }
                    if (diff < -1) {
                        winGame(player2);
                        this.game.resetScore();
                    }
                }
            }
        }
    }

    public void winGame(Player player) {
        Player player1 = this.game.getPlayer1();
        Player player2 = this.game.getPlayer2();
        if (winner != null) {
            throw new IllegalStateException(
                    "Match is over and the winner was " + winner.getName() + ". Start a new match to play.");
        }

        if (player.equals(player1)) {
            player1.incrementSetScore();
        } else if (player.equals(player2)) {
            player2.incrementSetScore();
        } else {
            throw new IllegalStateException(USER_NOT_FOUND_MESSAGE + player.getName());
        }

        updateScore("game");
    }

    public void winPoint(Player player) {
        Player player1 = this.game.getPlayer1();
        Player player2 = this.game.getPlayer2();
        if (winner != null) {
            throw new IllegalStateException(
                    "Match is over and the winner was " + winner.getName() + ". Start a new match to play.");
        }
        if (this.tieBreak) {
            if (player.equals(player1)) {
                player1.incrementTieBreakScore();
            } else if (player.equals(player2)) {
                player2.incrementTieBreakScore();
            } else {
                throw new IllegalStateException(USER_NOT_FOUND_MESSAGE + player.getName());
            }
        } else if (player.equals(player1)) {
            player1.incrementGameScore();
        } else if (player.equals(player2)) {
            player2.incrementGameScore();
        } else {
            throw new IllegalStateException(USER_NOT_FOUND_MESSAGE + player.getName());
        }
        updateScore("point");
    }

    public void winMatch(Player player) {
        this.winner = player;
    }

    public Player getWinner() {
        return winner;
    }


}

