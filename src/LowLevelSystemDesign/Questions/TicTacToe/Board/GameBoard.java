package LowLevelSystemDesign.Questions.TicTacToe.Board;

import LowLevelSystemDesign.Questions.TicTacToe.Game.Move;
import LowLevelSystemDesign.Questions.TicTacToe.Player.Player;

public class GameBoard {
    private final int[][] board;
    private final int size;

    public GameBoard(int size) {
        this.size = size;
        board = new int[size][size];
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int player = getBoardVal(i, j);
                String playerPiece =player == 0 ? " " : String.valueOf(player);
                String piece = "| " + playerPiece + " |";
                System.out.print(piece);
            }
            System.out.println();
        }
    }

    public void makeMove(Move move, Player player) {
        int row = move.row;
        int col = move.col;
        board[row][col] = player.getPlayerId();
    }
    public boolean isMoveAlreadyPlayed(Move move) {
        int row = move.row;
        int col = move.col;
        return board[row][col] != 0;
    }

    public boolean isValidMove(Move move) {
        int row = move.row;
        int col = move.col;
        return row >= 0 && row < size && col >= 0 && col < size;
    }

    public int getBoardVal(int row, int col) {
        return board[row][col];
    }
}
