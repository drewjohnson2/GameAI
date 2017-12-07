package controller;

import controller.Location;
import controller.HTTPRequest;
import view.TicTacToeGUI;
import model.TicTacToeData;
import model.Facade;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JFrame;

public class Main 
{
    public static TicTacToeData myData;
    private static final char TURN_X = 'X';
    private static final char TURN_O = 'O';
    static int myBestMove;
    
    public static void main(String [] args)
    {        
        TicTacToeGUI myGUI = new TicTacToeGUI();
        myGUI.setSize(500, 500);
        myGUI.setTitle("Super Tic-Tac-Toe");
        myGUI.setLocation(700, 300);
        myGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myGUI.setVisible(true);
        String myTeamID = "Team 4";
        
        HTTPRequest.setMyID(myTeamID);
        HTTPRequest myRequest;
        myRequest = new HTTPRequest();
        myData = new TicTacToeData();
        char myMove = TURN_X;
        
        ActionListener gameModeFalse = null;
        ActionListener gameModeTrue = null;
        
        myRequest.get(myData);

        Location location = new Location(myMove);
        while (myData.gameWinner.equals("-")) {
            location.board = myData.gameBoard;
            myBestMove = location.bestMove();
            if (myData.gameMode == false) {
                myRequest.get(myData);
                System.arraycopy(myData.gameBoard, 0, location.board, 0, myData.gameBoard.length);
                for (int i = 0; i < myData.gameBoard.length; i++) {
                    String move = Character.toString(myData.gameBoard[i]);
                    myGUI.myLocation[i].setText(move);
                }
                if (gameModeFalse == null) {
                gameModeFalse = ae -> {
                    System.out.println("Trying to put");
                    System.out.println("Best Move = " + myBestMove);
                    myRequest.put(myBestMove);
                    System.out.println("Put done");
                };
                myBestMove = location.bestMove();
                myGUI.pressMove.addActionListener(gameModeFalse);
                }
            }
            if (myData.gameMode == true && myData.gameTurn >= 9) {
                myRequest.get(myData);
                Facade myFacade = new Facade(myData.gameBoard, myMove);
                for (int i = 0; i < myData.gameBoard.length; i++) {
                    String move = Character.toString(myData.gameBoard[i]);
                    myGUI.myLocation[i].setText(move);
                }
                if (gameModeTrue == null) {
                    gameModeTrue = ae -> myRequest.put(myFacade.getValue());
                    myGUI.pressMove.removeActionListener(gameModeFalse);
                    myGUI.pressMove.addActionListener(gameModeTrue);
                }
            }
            myRequest.get(myData);
        }
        print(myData.gameBoard);
        
//        Location location = new Location(turnX);
//        Scanner input = new Scanner(System.in);
//        char [] gameBoard = new char [9];
//        int counter = 1;
//        location.turn = 'X';
//        char [] test = {'O','-', '-', 'O', '-', '-','-','-','-'};
//        System.arraycopy(test, 0, location.board, 0, test.length);
//        best = location.bestMove();
//        System.out.println(best);
//        location.move(best);
//        System.arraycopy(location.board, 0, test, 0, test.length);
        
//        for(int i = 0; i < gameBoard.length; i++)
//        {
//            gameBoard[i] = '-';
//        }
        
        //print(test);
        
//        do
//        {
//            
//            if(location.turn == 'x')
//            {
//                System.out.println("Move?");
//                int index = input.nextInt();
//                location.move(index-1);
//            }
//            
//            else if(location.turn == 'o')
//            {
//                int best = location.bestMove();
//                location.move(best);
//            }
//            
//            System.arraycopy(location.board, 0, gameBoard, 0, gameBoard.length);
//            print(gameBoard);
//            counter++;
//            
//        } while(!location.gameOver() || counter == 8);
    }
    
    public static void print(char [] gameBoard)
    {
        for(int i = 0; i < gameBoard.length; i++)
        {
            System.out.print(gameBoard[i]);
            
            System.out.print((i == 2 || i == 5 || i == 8) ? "\n" : "|");
            
            if(i == 2 || i == 5)
            {
                System.out.print("-----\n");
            }
            
        }
        System.out.println();
    }
    
    
}
