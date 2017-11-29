package TicTacToe;

import java.util.Scanner;

public class Main 
{
    
    public static void main(String [] args)
    {        
        Location location = new Location();
        Scanner input = new Scanner(System.in);
        char [] gameBoard = new char [9];
        int counter = 1;
        
        for(int i = 0; i < gameBoard.length; i++)
        {
            gameBoard[i] = ' ';
        }
        
        print(gameBoard);
        
        do
        {
            
            if(location.turn == 'x')
            {
                System.out.println("Move?");
                int index = input.nextInt();
                location.move(index-1);
            }
            
            else if(location.turn == 'o')
            {
                int best = location.bestMove();
                location.move(best);
            }
            
            System.arraycopy(location.board, 0, gameBoard, 0, gameBoard.length);
            print(gameBoard);
            counter++;
            
        } while(!location.gameOver() || counter == 8);
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
