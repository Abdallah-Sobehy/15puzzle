package tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import puzzle15.Board;
import puzzle15.Fitness3;


/**
 * @author Abdalla Sobehy, Fred Aklamanu, Mohsin Kazmi, Renaud
 * @version 1
 */
@RunWith(Parameterized.class)
public class JUFitness3 {


	/**
	 * expected float when we run the method fitness3
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
	public JUFitness3(Board b,float expectedFloat) {
		this.board = b;
		this.expectedResult = expectedFloat;
	}
	
	/**
	 * method to populate the parameter
	 * we take a board in solved state, and move the tile one by one from their good place
	 * in order to test all the class cases.
	 * here we give the expected result, and the board on which method fitness() is applied 
	 * this method work with the annotation Parameters
	 */
	@Parameters
	public static Collection<Object[]> param(){
		Board b1 = new Board();
		Board b2 = new Board(3);
		b2.slide("right");
		Board b3 = new Board(3);
		b3.copy_board(b2);
	    b3.slide("right");
	    
	    Board b4 = new Board(3);
	    b4.copy_board(b3);
	    b4.slide("right");
	    Board b5 = new Board(3);
	    b5.copy_board(b4);
	    b5.slide("down");
	    Board b6 = new Board(3);
	    b6.copy_board(b5);
	    b6.slide("left");
	    Board b7 = new Board(3);
	    b7.copy_board(b6);
	    b7.slide("left");
	    Board b8 = new Board(3);
	    b8.copy_board(b7);
	    b8.slide("left");
	    Board b9 = new Board(3);
	    b9.copy_board(b8);
	    b9.slide("down");
	    Board b10 = new Board(3);
	    b10.copy_board(b9);
	    b10.slide("right");
	    Board b11 = new Board(3);
	    b11.copy_board(b10);
	    b11.slide("right");
	    Board b12 = new Board(3);
	    b12.copy_board(b11);
	    b12.slide("right");
	    Board b13 = new Board(3);
	    b13.copy_board(b12);
	    b13.slide("down");
	    Board b14 = new Board(3);
	    b14.copy_board(b13);
	    b14.slide("left");
	    Board b15 = new Board(3);
	    b15.copy_board(b14);
	    b15.slide("left");
	    Board b16 = new Board(3);
	    b16.copy_board(b15);
	    b16.slide("left");
	    Board b17 = new Board(3);
	    b17.shuffle(200,6);
	    Board b18 = new Board(3);
	    b18.shuffle((float)1.0, (float)1.0);
	    
		return Arrays.asList(
				new Object[]{b1,0},
				new Object[]{b2,(float)0.033333335},
				new Object[]{b3,(float)0.06666667},
				new Object[]{b4,(float)0.1},
				new Object[]{b5,(float)0.13333334},
				new Object[]{b6,(float)0.13333334},
				new Object[]{b7,(float)0.13333334},
				new Object[]{b8,(float)0.13333334},
				new Object[]{b9,(float)0.16666667},
				new Object[]{b10,(float)0.2},
				new Object[]{b11,(float)0.23333333},
				new Object[]{b12,(float)0.26666668},
				new Object[]{b13,(float)0.3},
				new Object[]{b14,(float)0.3},
				new Object[]{b15,(float)0.3},
				new Object[]{b16,(float)0.3},
				new Object[]{b17,(float)0.5},
				new Object[]{b18,(float)1}
				);
	}
	
	/**
	 * test the different case which are populate by the param() method.
	 */
	@Test
	public void testFitness() {
		this.board.display();
		assertEquals(expectedResult, new Fitness3().fitness_function(board), 0);
	}

}
