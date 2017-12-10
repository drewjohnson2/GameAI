/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe;

import controller.DeathMatch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author drewjohnson
 */
public class DeathMatchTest {
    
    public char[] board = {'X', 'O', 'O',
                    'O', 'X', 'X',
                    '-', 'X', 'O'};
    public char myMove = 'X';
    
    public DeathMatchTest(char [] board, char myMove) {
        this.board = board;
        this.myMove = myMove;
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testDeathMatch()
    {
        DeathMatch test = new DeathMatch(board, myMove);
        
//        System.out.println(test.possibleMoves.size());
        
//        for(int i = 0; i < board.length; i++)
//        {
//            System.out.println(board[i]);
//        }
//        
//        for(int i = 0; i < test.possibleMoves.size(); i++)
//        {
//            System.out.println(test.possibleMoves.get(i).toString());
//        }
//        System.out.println();
    }
    
    @Test
    public void testLosingTurn()
    {
        assertTrue(new DeathMatch("XXX------".toCharArray(), 'X').losingTurn('X', "XXX------".toCharArray()));
        assertTrue(new DeathMatch("O--O--O--".toCharArray(), 'X').losingTurn('O', "O--O--O--".toCharArray()));
        
        assertTrue(new DeathMatch("--X-X-X--".toCharArray(), 'X').losingTurn('X', "--X-X-X--".toCharArray()));
        
        assertTrue(new DeathMatch("X---X---X".toCharArray(), 'X').losingTurn('X', "X---X---X".toCharArray()));
        //assertTrue(new DeathMatch("ooxxxoox-".toCharArray(), 'x').losingTurn('o', "ooxxoxox-".toCharArray()));
    }
    
    @Test
    public void testCheckMove()
    {
      
        char[] newBoard = {'X', 'O', 'X',
                           'O', 'O', 'X',
                           '-', 'X', 'O'};
        
        DeathMatch test = new DeathMatch(newBoard, myMove);
//        for(int i = 0; i < test.board.length; i++)
//        {
//            System.out.println(test.board[i]);
//        }
        
        List<Integer> list = new ArrayList<>();
        list = test.checkMove(test.possibleMoves);
//        System.out.println(list.size());
//        System.out.println();
        
//        for(int i = 0; i < test.possibleMoves.size(); i++)
//        {
//            System.out.println(list.get(i).toString());
//        }
//        
//        System.out.println();
        
    }
    
    @Test
    public void testCheckSize()
    {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        
        assertFalse(new DeathMatch(board, '0').checkSize(list));
    }
    
    @Test
    public void testRemoveCorners()
    {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        
        DeathMatch test = new DeathMatch(board, 'O');
        System.out.println("Hey");
        
        list = test.removeCorners(list);
        
        System.out.println(list.size());
        
        for(int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i).toString());
        }
        
        System.out.println();
        
        list.add(2);
        list.add(0);
        list.add(6);
        
        list = test.removeCorners(list);
        
        for(int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i).toString());
        }
        
    }
    
    @Test
    public void testRemoveMiddle()
    {
        List<Integer> list = new ArrayList<>();
        list.add(4);
        
        DeathMatch test = new DeathMatch(board, 'O');
        
        list = test.removeMiddle(list);
        
        System.out.println();
        System.out.println(list.size());
        
        for(int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i).toString());
        }
        
        list.add(1);
        list.add(2);
        list.add(8);
        
        System.out.println();
        
        list = test.removeMiddle(list);
        
        System.out.println(list.size());
        
        for(int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i).toString());
        }
        
        
    }
    
}
