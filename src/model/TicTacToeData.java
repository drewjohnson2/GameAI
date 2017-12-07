
package model;


public class TicTacToeData {

    public char[] gameBoard;
    public int gameTurn;
    public boolean gameMode;
    public String gameWinner;
    
    public TicTacToeData() {
        gameBoard = new char[9];
        gameTurn = 1;
        gameMode = false;
        gameWinner = "";
    }
}
