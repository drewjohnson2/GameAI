/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe;

import controller.Location;
import java.util.List;
import java.util.ArrayList;
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
public class LocationTest {
    
    public char myMove;
    
    public LocationTest(char myMove) {
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

    /**
     * Test of toString method, of class Location.
     */
    
    @Test
    public void testLocation()
    {
        Location location = new Location(myMove);
        assertEquals('X', location.turn);
        assertEquals("---------", location.toString());
    }
    
    @Test
    public void testMove()
    {
        Location location = new Location(myMove).move(1);
        assertEquals('O', location.turn);
        assertEquals("-X-------", location.toString());
        
    }
    
    @Test
    public void testRemove()
    {
        Location location = new Location(myMove).move(1).remove(1);
        assertEquals('X', location.turn);
        assertEquals("---------", location.toString());
    }
    
    @Test
    public void testPossibleMoves()
    {
        List<Integer> list = new ArrayList<>();
        
        for(int i = 0; i < Location.BOARD_SIZE; i++)
        {
            list.add(i);
        }
        list.remove(new Integer(1));
        list.remove(new Integer(2));
        
        assertEquals(list, new Location(myMove).move(1).move(2).possibleMoves());
    }
    
    @Test
    public void testWinner() throws Exception
    {
        assertFalse(new Location(myMove).winner('X'));
        assertTrue(new Location("XXX------", 'X').winner('X'));
        
        assertTrue(new Location("O--" +
                                "O--" +
                                "O--", 'X').winner('O'));
        
        assertTrue(new Location("--X" +
                                "-X-" +
                                "X--", 'X').winner('X'));
        
        assertTrue(new Location("X--" +
                                "-X-" +
                                "--X", 'X').winner('X'));
    }
    
    @Test
    public void testMinimax()
    {
        assertEquals(7, new Location("XXX------", 'X').minimax());
        assertEquals(-7, new Location("OOO------", 'O').minimax());
        assertEquals(0, new Location("XOXXOXOXO", 'X').minimax());
        assertEquals(7, new Location("XX-------", 'X').minimax());
        assertEquals(-7, new Location("OO-------", 'O').minimax());
        assertEquals(0, new Location(myMove).minimax());
        
    }
    
    @Test
    public void testBestMove()
    {
        assertEquals(2, new Location("XX-------", 'X').bestMove());
        assertEquals(2, new Location("XX-------", 'X').bestMove());
    }
    
    @Test
    public void testGameOver()
    {
        assertFalse(new Location(myMove).gameOver());
        assertTrue(new Location("XXX------", 'X').gameOver());
        assertTrue(new Location("OOO------", 'X').gameOver());
        assertTrue(new Location("XOXXOXOXO", 'X').gameOver());
    }
}
