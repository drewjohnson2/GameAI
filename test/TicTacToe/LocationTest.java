/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe;

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
    
    public LocationTest() {
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
        Location location = new Location();
        assertEquals('x', location.turn);
        assertEquals("---------", location.toString());
    }
    
    @Test
    public void testMove()
    {
        Location location = new Location().move(1);
        assertEquals('o', location.turn);
        assertEquals("-x-------", location.toString());
        
    }
    
    @Test
    public void testRemove()
    {
        Location location = new Location().move(1).remove(1);
        assertEquals('x', location.turn);
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
        
        assertEquals(list, new Location().move(1).move(2).possibleMoves());
    }
    
    @Test
    public void testWinner() throws Exception
    {
        assertFalse(new Location().winner('x'));
        assertTrue(new Location("xxx------", 'x').winner('x'));
        
        assertTrue(new Location("o--" +
                                "o--" +
                                "o--", 'x').winner('o'));
        
        assertTrue(new Location("--x" +
                                "-x-" +
                                "x--", 'x').winner('x'));
        
        assertTrue(new Location("x--" +
                                "-x-" +
                                "--x", 'x').winner('x'));
    }
    
    @Test
    public void testMinimax()
    {
        assertEquals(7, new Location("xxx------", 'x').minimax());
        assertEquals(-7, new Location("ooo------", 'o').minimax());
        assertEquals(0, new Location("xoxxoxoxo", 'x').minimax());
        assertEquals(7, new Location("xx-------", 'x').minimax());
        assertEquals(-7, new Location("oo-------", 'o').minimax());
        assertEquals(0, new Location().minimax());
        
    }
    
    @Test
    public void testBestMove()
    {
        assertEquals(2, new Location("xx-------", 'x').bestMove());
        assertEquals(2, new Location("xx-------", 'x').bestMove());
    }
    
    @Test
    public void testGameOver()
    {
        assertFalse(new Location().gameOver());
        assertTrue(new Location("xxx------", 'x').gameOver());
        assertTrue(new Location("ooo------", 'x').gameOver());
        assertTrue(new Location("xoxxoxoxo", 'x').gameOver());
    }
}
