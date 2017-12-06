/**
 * This is an AI for the Tic-Tac-Toe Deathmatch.
 * 
 * @author Drew Johnson
 * @version 1.0
 * @since 2017-12-4
 */
package TicTacToe;

import java.util.ArrayList;
import java.util.List;

public class DeathMatch {
    
    List<Integer> possibleMoves;
    public char [] board;
    public static final int DIM = 3;
    public static final int BOARD_SIZE = DIM * DIM;
    public char oppMove;
    public List<Integer> worstCase = new ArrayList<>();
    
    public DeathMatch(char [] board, char myMove)
    {
        oppMove = (myMove == 'x' ? 'o' : 'x');
        
        possibleMoves = new ArrayList<>();
        this.board = board;
        
        for(int i = 0; i < board.length; i++)
        {
            if(board[i] == myMove)
            {
                possibleMoves.add(i);
                worstCase.add(i);
            }
        }
    }
    
    /**
     * Determines whether or not making a certain move would result in a loss.
     * 
     * @param turn      The current token we are using. (x or o)
     * @param board     The current state of the game board.
     * @return          A boolean value that will signal if moving a current
     *                  token will result in a loss.
     */
    public boolean losingTurn(char turn, char [] board)
    {
        boolean winner = false;
        
        for(int i = 0; i < BOARD_SIZE; i += DIM) 
        {   
            // Determine a winner based off of a horizontal sequence
            winner = winner || lineMatch(turn, i, i + DIM, 1, board);
        }
        
        for(int i = 0; i < DIM; i++)
        {
            // Determine a winner based off of a verticle sequence
            winner = winner || lineMatch(turn, i, BOARD_SIZE, DIM, board);
        }
        
        //Determine a winner based off of a diagonal sequence 
        winner = winner || lineMatch(turn, 0, BOARD_SIZE, DIM + 1, board);
        winner = winner || lineMatch(turn, DIM - 1, BOARD_SIZE - 1, DIM - 1, board);
        
        return winner;
    }
    
    /**
     * 
     * @param turn  the player making the move
     * 
     * @param start where to start looking for moves
     * 
     * @param end   where to stop looking
     * 
     * @param step  how many spaces to skip before looking for the next
     *              occurrence of turn
     * 
     * @param board The current state of the game board
     * 
     * @return      boolean value that determines whether a match for a 
     *              specified pattern has been found.
     */
    private boolean lineMatch(char turn, int start, int end, int step, char[] board) 
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
     * Checks all current locations occupied by our team and determines 
     * which tokens must remain the same.
     * 
     * @param possibleMoves     ArrayList containing all current spaces 
     *                          occupied by us.
     * 
     * @return                  Altered ArrayList containing only moves
     *                          that will not result in a loss.
     */
    
    public List<Integer> checkMove(List<Integer> possibleMoves)
    {
        char [] holdBoard = new char [9];
        
        for(int i = 0; i < this.board.length; i ++)
        {
            holdBoard[i] = this.board[i];
        }
        
        List<Integer> toDelete = new ArrayList<>();
        
        for(Integer index: possibleMoves)
        {
            holdBoard[index] = oppMove;
            
            if(losingTurn(oppMove, holdBoard) == true)
            {
                toDelete.add(index);
            }
            
            for(int i = 0; i < this.board.length; i ++)
            {
                holdBoard[i] = this.board[i];
            }
        }
        
        possibleMoves.removeAll(toDelete);
        
        return possibleMoves;
    }
    
    /**
     * Checks the current size of the array of non-losing moves.
     * 
     * @param list  List of non-losing moves
     * 
     * @return      Boolean value that will indicate if the list contains more
     *              than one move
     */
    
    public boolean checkSize(List<Integer> list)
    {
        return (list.size() > 1);
    }
    
    /**
     * Removes corner spaces as valid moves if possible. 
     * In tic-tac-toe it is advantageous to occupy the corner spaces, so if 
     * possible we remove them from the List in attempt to continue to occupy 
     * those spaces.
     * 
     * @param possibleMoves     List of non-losing moves.
     * @return                  List of non-losing moves.
     */
    
    public List<Integer> removeCorners(List<Integer> possibleMoves)
    {
        List<Integer> toDelete = new ArrayList<>();
        
        if(checkSize(possibleMoves) == false)
        {
            return possibleMoves;
        }
        
        else
        {
            
            for(Integer index : possibleMoves)
            {
                if(index == 0 || index == 2 || index == 6 || index == 8)
                {
                    toDelete.add(index);
                }
                
                if(checkSize(possibleMoves) == false)
                {
                    return possibleMoves;
                }
            }
        }
        
        possibleMoves.removeAll(toDelete);
        
        return possibleMoves;
        
    }
    
    /**
     * Removes middle space as a valid move if possible. In tic-tac-toe it is
     * advantageous to occupy the middle position, so if possible we remove
     * them from the List in attempt to continue to occupy that space.
     * 
     * @param possibleMoves         List of non-losing moves.
     * 
     * @return                      List of non-losing moves.
     */
    public List<Integer> removeMiddle(List<Integer> possibleMoves)
    {
        List<Integer> toDelete = new ArrayList();
        
        if(checkSize(possibleMoves) == false)
        {
            return possibleMoves;
        }
        
        else
        {
            for(Integer index : possibleMoves)
            {
                if(index == 4)
                {
                    toDelete.add(index);
                }
            }
        }
        
        possibleMoves.removeAll(toDelete);
        
        return possibleMoves;
        
    }
    
    /**
     * Passes back the first element of the non-losing List after corners and
     * the middle space has been removed. If no non-losing moves were found, 
     * the first element in the original list is returned.
     * 
     * @param possibleMoves         List of non-losing moves. (Possibly)
     * 
     * @return                      First element from the List.
     */
    public int makeMove(List<Integer> possibleMoves)
    {
        if(possibleMoves.isEmpty())
        {
            return worstCase.get(1);
        }
        
        return possibleMoves.get(0);
    }
}
