/**
 * This is an implementation of the minimax algorithm for a tic-tac-toe
 * game. 
 * 
 * Implementation notes for the minimax have been
 * taken from Chong Kim at https://youtu.be/9z2Z6xiauNA
 * 
 * @author  Drew Johnson
 * @version 1.0
 * @since   2017-11-10
 */

package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Location {
    
    public static final int DIM = 3;
    public static final int BOARD_SIZE = DIM * DIM;
    public char [] board;
    public char turn;
    public char oppMove;
    public char myMove;
    
    public Location(char myMove)
    {                                         
        turn = myMove;                             //Initial turn
        this.myMove = myMove;
        oppMove = (myMove == 'X' ? 'O' : 'X');
        board = new char[BOARD_SIZE];           
        
        for(int i = 0; i < BOARD_SIZE; i++)
        {
            board[i] = '-';     //Initialize board with empty spaces
        }
    }

    /**
     * Constructor used for testing purposes only
     * 
     * @param layout Used to simulate a scenario on the board 
     * @param turn   Current player's turn represented by either an 'x' or 'o'
     */
    public Location(String layout, char turn) 
    {
        this.board = layout.toCharArray();
        this.turn = turn;
    }
    
    /**
     * Populates a move on the game board and changes turn to the opponent
     * 
     * @param index The space on the board to be filled with a move
     * @return Location object
     */
    public Location move(int index)
    {
        board[index] = turn;
        turn = (turn == 'X' ? 'O' : 'X'); // Change turns
        return this;
    }
    
    /**
     * This method is used by the minimax method to test all possible scenarios.
     * Once minimax receives what it needs, all test case moves are removed 
     * from the board using this method. 
     * 
     * @param index is the index from which to remove test move.
     * @return Location object
     */
    public Location remove(int index)
    {
        board[index] = '-';     // Removes move from board
        turn = (turn == 'X' ? 'O' : 'X');   // change turns
        return this;
    }
    
    /**
     * Fetches indices of blank spaces on the game board.
     * 
     * @return list of available spaces on the game board.
     */
    public List<Integer> possibleMoves()
    {
        List<Integer> list = new ArrayList<>(); // List for holding possible
                                                // moves.
        for(int i = 0; i < BOARD_SIZE; i++)
        {
            if(board[i] == '-')
            {
                list.add(i);
            }
        }
        
        return list;
    }
    
    /**
     * This method determines if a the current user is a winner based off of
     * their most recent move.
     * 
     * @param turn  Current users turn
     * @return boolean expression for determining a winner.
     */
    public boolean winner(char turn)
    {
        boolean winner = false;
        
        for(int i = 0; i < BOARD_SIZE; i += DIM) 
        {   
            // Determine a winner based off of a horizontal sequence
            winner = winner || lineMatch(turn, i, i + DIM, 1);
        }
        
        for(int i = 0; i < DIM; i++)
        {
            // Determine a winner based off of a verticle sequence
            winner = winner || lineMatch(turn, i, BOARD_SIZE, DIM);
        }
        
        //Determine a winner based off of a diagonal sequence 
        winner = winner || lineMatch(turn, 0, BOARD_SIZE, DIM + 1);
        winner = winner || lineMatch(turn, DIM - 1, BOARD_SIZE - 1, DIM - 1);
        
        return winner;
    }
    
    /**
     * 
     * @param turn the player making the move
     * @param start where to start looking for moves
     * @param end   where to stop looking
     * @param step  how many spaces to skip before looking for the next
     *              occurrence of turn
     * @return      boolean value that determines whether a match for a 
     *              specified pattern has been found.
     */
    private boolean lineMatch(char turn, int start, int end, int step) 
    {
        for(int i = start; i < end; i += step)
        {
            if(board[i] != turn) // If value checked on board is a space or 
            {                    // the opposite player's move, a win is not
                                 // found.
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Finds the amount of empty spaces on the board.
     * 
     * @return total number of empty spaces.
     */
    public int blanks()
    {
        int total = 0;
        
        for(int i = 0; i < BOARD_SIZE; i++)
        {
            if(board[i] == '-')
            {
                total++;
            }
        }
        
        return total;
    }
    
    /**
     * Finds the optimal move through recursion. Makes moves on blank spaces
     * to find optimal move on the game board.
     * 
     * @return if 'x' is the human player we want to return the max value of
     *         the array list. Otherwise, return the minimum value.
     */
    public int minimax()
    {
        if(winner(myMove))
            return blanks() + 1;
        
        if(winner(oppMove))
            return -blanks() - 1;
        
        if(blanks() == 0) 
            return 0;
        
        List<Integer> list = new ArrayList<>();
        
        for (Integer index : possibleMoves())
        {
            list.add(move(index).minimax());
            remove(index);
        }
        return turn == myMove ? Collections.max(list) : Collections.min(list);
    }
    
    /**
     * Compares moves based on the index returned by the minimax method.
     * 
     * @return if the user is 'x' (AI) the max is returned. Else, the minimum
     * is returned.
     */
    public int bestMove()
    {
        Comparator<Integer> cmp = new Comparator<Integer>() {
            // Implements empty compare method used for comparing values 
            // from variable 'list' of possible moves.
            @Override
            public int compare(Integer first, Integer second) 
            {
                int a = move(first).minimax();
                remove(first);
                
                int b = move(second).minimax();
                remove(second);
                
                return a - b;
            }
        };
        
        List<Integer> list = possibleMoves();
      
        return turn == myMove ? Collections.max(list, cmp) : Collections.min(list, cmp);
    }
    
    /**
     * This determines whether or not the game is over.
     * 
     * @return a boolean value on whether 'x' or 'o' is the winner or a draw.
     */
    public boolean gameOver()
    {
        return winner(myMove) || winner(oppMove) || blanks() == 0;
    }

    public String toString()
    {
        return new String(board);
    }
}
