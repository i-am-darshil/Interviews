package LowLevelSystemDesign.Questions.TicTacToe;

import LowLevelSystemDesign.Questions.TicTacToe.Game.Game;

public class Main {
    public static void main(String[] args) {
        /*
        Requirements
        1. Need a game board
        2. Need game pieces - 'x' is a piece and 'o' is a piece
        3. Need players - has a game piece
        4. Need a game - has a board, has some players
         */

        Game game = new Game(4, 3);
        game.play();
    }
}
