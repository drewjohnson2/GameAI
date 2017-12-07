package model;

import controller.DeathMatch;
import java.util.ArrayList;
import java.util.List;

public class Facade 
{
    public List<Integer> possibleMoves;
    public List<Integer> worstCase = new ArrayList<>();
    int optimalMove;
    public DeathMatch dm;
    char oppMove;
    
    public Facade(char [] board, char myMove)
    {
        possibleMoves = new ArrayList<>();
        dm = new DeathMatch(board, myMove);
        oppMove = (myMove == 'x' ? 'o' : 'x');
        
        for(int i = 0; i < board.length; i++)
        {
            if(board[i] == myMove)
            {
                possibleMoves.add(i);
                worstCase.add(i);
            }
        }
        
    }
    
    public int getValue()
    {
        possibleMoves = dm.checkMove(possibleMoves);
        possibleMoves = dm.removeMiddle(possibleMoves);
        possibleMoves = dm.removeCorners(possibleMoves);
        optimalMove = dm.makeMove(possibleMoves);
        
        return optimalMove;
    }
}