package tennis.game;

import java.util.Scanner;

public class App {
	private static final Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.print("Player 1 name :");
		Player player1 = new Player(scanner.nextLine());
		System.out.print("Player 2 name :");
		Player player2 = new Player(scanner.nextLine());
		if (player1.getName().equals(player2.getName()))
			throw new IllegalStateException("Players shoud have different names");
		Match tennisMatch = new Match(player1, player2);
		displayHelpMessage();
		System.out.print("Action> ");
			while (tennisMatch.getWinner() == null) {

				String action = scanner.nextLine();
				switch (action) {
				case "1":
					tennisMatch.winPoint(player1);
					break;
				case "2":
					tennisMatch.winPoint(player2);
					break;
				case "d":
					System.out.println(tennisMatch.getScore());
					break;
				default:
					displayHelpMessage();
					break;
				}
			}
		scanner.close();
		System.out.println(tennisMatch.getScore());

	}
	
	private static void displayHelpMessage(){
		System.out.println("Possible Actions:");
		System.out.println("'1' : first player win point");
		System.out.println("'2' : second player win point");
		System.out.println("'d' : display score dashboard");
	}

}
