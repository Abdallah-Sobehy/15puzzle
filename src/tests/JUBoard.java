package tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import puzzle15.Board;

public class JUBoard 
{
	Board b;
	/*
	 * Initializing a board b to be used in every test case
	 */
	@Before
	public void setUp()
	{
		b = new Board();
	}
	/*
	 * Testing board's initial state which should be in a solved position
	 */
	@Test
	public void testInital()
	{
		int [] [] initial_board = new int [] [] {
		{ 1, 2, 3, 4},
		{ 5, 6, 7, 8},
		{ 9,10,11,12},
		{13,14,15,-1}
		};
		assertArrayEquals("board initialization error.",initial_board, b.get_board());
	}
	
	/*
	 * Test get_position function
	 */
	@Test
	public void test_get_position()
	{
		int [] pos_4 = new int [] {0,3};
		assertArrayEquals(pos_4, b.get_position(4));
	}
	
	/*
	 * Test Slide function in valid directions.
	 */
	@Test
	public void test_slide_valid()
	{
		int [][] tmp = b.get_board();
		// Testing valid slide right
		assertTrue("Valid slide right rejected.",b.slide("right"));
		tmp[3][3] = 15; tmp [3][2] = -1;
		assertArrayEquals("Wrong position after slide right.",tmp, b.get_board());
		
		// testing valid slide down
		assertTrue("Valid slide down rejected",b.slide("down"));
		tmp[3][2] = 11; tmp[2][2] = -1;
		assertArrayEquals("Wrong position after slide down.",tmp, b.get_board());
		
		// testing valid slide left 
		assertTrue("Valid slide left rejected", b.slide("left"));
		tmp [2][2] = 12 ; tmp[2][3] = -1;
		assertArrayEquals("Valid slide left rejected.", tmp, b.get_board());
		
		// Testing valid slide up
		assertTrue("Valid slide up rejected.", b.slide("up"));
		tmp [2][3] = 8 ; tmp [1][3] = -1;
		assertArrayEquals("Valid slide up rejected.",tmp, b.get_board());	
	}
	
	/*
	 * Test slide function in invalid directions
	 */
	@Test
	public void test_slide_invalid()
	{
		assertFalse("invalid slide left accepted.",b.slide("left"));
		assertFalse("invalid slide up accepted.",b.slide("up"));
		// 3 slides right and 3 slides down to reach a position invalid for right and down slide
		for (int i = 0 ; i<3 ;i++)
		{
			b.slide("right");
			b.slide("down");
		}
		assertFalse("invalid slide right accepted.",b.slide("right"));
		assertFalse("invalid slide down accepted.",b.slide("down"));
	}
	/*
	 * Test the shuffle function with a seed as an input
	 */
	@Test
	public void test_shuffle()
	{
		int seed = 2;
		// with seed = 2, the 4 first moves (known by testing in Testboard) are: right, down, left, up
		int [][] expected = {
				{ 1, 2, 3, 4},
				{ 5, 6, 7, 8},
				{ 9,10,12,15},
				{13,14,11,-1}
				};
		b.shuffle(4,2);
		assertArrayEquals("Unexpected outcoe with shuffle.",expected, b.get_board());
	}
	/*
	 * Test copy board function
	 */
	@Test
	public void test_copy_board()
	{
		Board tmp = new Board();
		// Shuffle board
		b.shuffle(15);
		tmp.copy_board(b);
		assertArrayEquals("Error in copy board function",tmp.get_board(), b.get_board());
	}
}
