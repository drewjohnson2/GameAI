/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe;

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
    
    char[] board = {'x', 'o', 'o',
                    'o', 'x', 'x',
                    '-', 'x', 'o'};
        char myMove = 'x';
    
    public DeathMatchTest() {
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
        assertTrue(new DeathMatch("xxx------".toCharArray(), 'x').losingTurn('x', "xxx------".toCharArray()));
        assertTrue(new DeathMatch("o--o--o--".toCharArray(), 'x').losingTurn('o', "o--o--o--".toCharArray()));
        
        assertTrue(new DeathMatch("--x-x-x--".toCharArray(), 'x').losingTurn('x', "--x-x-x--".toCharArray()));
        
        assertTrue(new DeathMatch("x---x---x".toCharArray(), 'x').losingTurn('x', "x---x---x".toCharArray()));
        //assertTrue(new DeathMatch("ooxxxoox-".toCharArray(), 'x').losingTurn('o', "ooxxoxox-".toCharArray()));
    }
    
    @Test
    public void testCheckMove()
    {
      
        char[] newBoard = {'x', 'o', 'x',
                           'o', 'o', 'x',
                           '-', 'x', 'o'};
        
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
        
        DeathMatch test = new DeathMatch(board, 'o');
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
        
        DeathMatch test = new DeathMatch(board, 'o');
        
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
