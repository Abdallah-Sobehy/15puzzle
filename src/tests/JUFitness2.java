package tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import puzzle15.Board;
import puzzle15.Fitness2;


/**
 * Test class for {@link Fitness}<br>
 * this class used Junit test for unit test on the method of class Fitness
 * 
 * @author Abdalla Sobehy, Fred Aklamanu, Mohsin Kazmi, Renaud
 * @version 1
 */
@RunWith(Parameterized.class)
public class JUFitness2 {

	/**
	 * expected float when we run the method fitness2
	 */
	private final float expectedResult;
	/**
	 * board used to do the unit test on the method of class Fitness
	 */
	private final Board board;
	
	/**
	 * Constructor of the class
	 * initialise the attribute with the parameter take as input
	 * these input will be populate with the method param(),which take annotation Parameters. 
	 */
	public JUFitness2(Board b,float expectedFloat) {
		this.board = b;
		this.expectedResult = expectedFloat;
	}
	
	/**
	 * method to populate the parameter
	 * we take a board in solved state, and move the tile one by one from their good place
	 * in order to test all the class cases.
	 * here we give the expected result, and the board on which method fitness1() is applied 
	 * this method work with the annotation Parameters
	 */
	@Parameters
	public static Collection<Object[]> param(){
		Board b1 = new Board();
		Board b2 = new Board();
		b2.slide("right");
		Board b3 = new Board();
		b3.copy_board(b2);
	    b3.slide("right");
	    
	    Board b4 = new Board();
	    b4.copy_board(b3);
	    b4.slide("right");
	    Board b5 = new Board();
	    b5.copy_board(b4);
	    b5.slide("down");
	    Board b6 = new Board();
	    b6.copy_board(b5);
	    b6.slide("left");
	    Board b7 = new Board();
	    b7.copy_board(b6);
	    b7.slide("left");
	    Board b8 = new Board();
	    b8.copy_board(b7);
	    b8.slide("left");
	    Board b9 = new Board();
	    b9.copy_board(b8);
	    b9.slide("down");
	    Board b10 = new Board();
	    b10.copy_board(b9);
	    b10.slide("right");
	    Board b11 = new Board();
	    b11.copy_board(b10);
	    b11.slide("right");
	    Board b12 = new Board();
	    b12.copy_board(b11);
	    b12.slide("right");
	    Board b13 = new Board();
	    b13.copy_board(b12);
	    b13.slide("down");
	    Board b14 = new Board();
	    b14.copy_board(b13);
	    b14.slide("left");
	    Board b15 = new Board();
	    b15.copy_board(b14);
	    b15.slide("left");
	    Board b16 = new Board();
	    b16.copy_board(b15);
	    b16.slide("left");
	    
		return Arrays.asList(
				new Object[]{b1,0},
				new Object[]{b2,2f/16},
				new Object[]{b3,3f/16},
				new Object[]{b4,4f/16},
				new Object[]{b5,5f/16},
				new Object[]{b6,6f/16},
				new Object[]{b7,7f/16},
				new Object[]{b8,8f/16},
				new Object[]{b9,9f/16},
				new Object[]{b10,10f/16},
				new Object[]{b11,11f/16},
				new Object[]{b12,12f/16},
				new Object[]{b13,13f/16},
				new Object[]{b14,14f/16},
				new Object[]{b15,15f/16},
				new Object[]{b16,16f/16}
				);
	}
	
	/**
	 * test the different case which are populate by the param() method.
	 */
	@Test
	public void testFitness() {
		this.board.display();
		assertEquals(expectedResult, new Fitness2().fitness_function(board), 0);
	}
}
