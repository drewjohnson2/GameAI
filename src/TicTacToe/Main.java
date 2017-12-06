/**
 * IGNORE THIS MAIN. THIS IS A DUMMY UNIT MADE COMPLETELY FOR TESTING PURPOSES
*/


package TicTacToe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main 
{
    
    public static void main(String [] args)
    {   
//        char[] gameBoard = {'x', 'o', 'x',
//                            '-', 'x', 'o',
//                            'o', 'x', 'o'};
//        
//        char myMove = 'o';
//        
//        int optimalMove;
//        
//        List<Integer> list = new ArrayList<>();
//        
//        for(int i = 0; i < gameBoard.length; i++)
//        {
//            if(gameBoard[i] == myMove)
//            {
//                list.add(i);
//            }
//        }
//        
//        DeathMatch dm = new DeathMatch(gameBoard, myMove);
//        
//        System.out.println("Non-losing moves:\n");
//        list = dm.checkMove(list);
//        
//        for(int i = 0; i < list.size(); i++)
//        {
//            System.out.println(list.get(i).toString());
//        }
//        System.out.println();
//        
//        System.out.println("No Middle:\n");
//        
//        list = dm.removeMiddle(list);
//        
//        for(int i = 0; i < list.size(); i++)
//        {
//            System.out.println(list.get(i).toString());
//        }
//        
//        System.out.println();
//        
//        System.out.println("No Corners:\n");
//        list = dm.removeCorners(list);
//        
//        for(int i = 0; i < list.size(); i++)
//        {
//            System.out.println(list.get(i).toString());
//        }
//        System.out.println();
//        
//        
//        optimalMove = dm.makeMove(list);
//        
//        System.out.println("Move to Make:\n");
//        System.out.println(optimalMove);
        
        
        
        
        
        
        char ourTurn = 'o';
        Location location = new Location(ourTurn);
//        Scanner input = new Scanner(System.in);
//        char [] gameBoard = new char [9];
//        int counter = 1;
//        location.turn = 'o';
        char [] test = {'x','-', '-', 'x', '-', '-','-','-','-'};
        System.arraycopy(test, 0, location.board, 0, test.length);
        int best = location.bestMove();
        System.out.println(best);
        location.move(best);
        System.arraycopy(location.board, 0, test, 0, test.length);
        
//        for(int i = 0; i < gameBoard.length; i++)
//        {
//            gameBoard[i] = '-';
//        }
//        
        print(test);
//        
//        do
//        {
//            
//            if(location.turn == ourTurn)
//            {
//                System.out.println("Move?");
//                int index = input.nextInt();
//                location.move(index-1);
//            }
//            
//            else
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
//    
//    
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
