package LowLevelSystemDesign.Questions.TicTacToe.Player;

import LowLevelSystemDesign.Questions.TicTacToe.Game.Move;

import java.util.Scanner;

public class Player {
    private final int playerId;
    public Player (int playerId) {
        this.playerId = playerId;
    }

    public Move getMove() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Row of your move (0 indexed)");
        int row = sc.nextInt();

        System.out.println("Enter Col of your move (0 indexed)");
        int col = sc.nextInt();

        return new Move(row, col);
    }

    public int getPlayerId() {
        return playerId;
    }
}
