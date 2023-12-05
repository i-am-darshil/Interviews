package LowLevelSystemDesign.Questions.TicTacToe.Game;

import LowLevelSystemDesign.Questions.TicTacToe.Board.GameBoard;
import LowLevelSystemDesign.Questions.TicTacToe.Player.Player;

import java.util.Deque;
import java.util.LinkedList;

public class Game {

    private final GameBoard board;
    private final Deque<Player> playerMoves;
    private int movesRemaining;

    private final int boardSize;

    public Game (int boardSize, int numPlayers) {
        this.boardSize = boardSize;
        board = new GameBoard(boardSize);
        playerMoves = new LinkedList<>();
        initialize(numPlayers);
        movesRemaining = boardSize * boardSize;
    }

    public void initialize(int numPlayers) {
        for (int i = 1; i <= numPlayers; i++) {
            Player player = new Player(i);
            playerMoves.add(player);
        }
    }

    public void play() {
        int winner = -1;
        while (winner == -1) {
            board.display();
            if (movesRemaining == 0) {
                System.out.println("It is a tie");
                break;
            }

            Player player = playerMoves.poll();
            Move move = player.getMove();

            if (!board.isValidMove(move)) {
                System.out.println(move + " is an invalid move. Row & Col should be between 0 and " + boardSize + ". Try Again");
                playerMoves.offerFirst(player);
                continue;
            }

            if (board.isMoveAlreadyPlayed(move)) {
                System.out.println(move + " is already played. Try Again");
                playerMoves.offerFirst(player);
                continue;
            }

            board.makeMove(move, player);
            movesRemaining--;

            winner = checkWinner(player);

            playerMoves.offer(player);
        }

        if (winner != -1) {
            System.out.println("Winner is " + winner);
        }

    }

    public int checkWinner(Player player) {
        boolean rowWinner;
        boolean colWinner;
        boolean primDiagWinner = true;
        boolean secDiagWinner = true;

        for (int i = 0; i < boardSize; i++) {
            // check row
            rowWinner = true;
            for (int j = 0; j < boardSize; j++) {
                if (board.getBoardVal(i, j) != player.getPlayerId()) {
                    rowWinner = false;
                    break;
                }
            }

            if (rowWinner) return player.getPlayerId();

            // check col
            colWinner = true;
            for (int j = 0; j < boardSize; j++) {
                if (board.getBoardVal(j, i) != player.getPlayerId()) {
                    colWinner = false;
                    break;
                }
            }

            if (colWinner) return player.getPlayerId();

            if (board.getBoardVal(i, i) != player.getPlayerId()) {
                primDiagWinner = false;
            }

            if (board.getBoardVal(i, (boardSize-1) - i) != player.getPlayerId()) {
                secDiagWinner = false;
            }

        }

        if (primDiagWinner || secDiagWinner) return player.getPlayerId();

        return -1;
    }
}
