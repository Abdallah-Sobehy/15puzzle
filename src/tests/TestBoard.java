package tests;
import puzzle15.Board;

/**
 * Test class for {@link Board}<br>
 */
public class TestBoard {
	public static void main(String args[]) 
	{
		// Initialize board and display
		Board b = new Board();
		b.display();
		// Slide up should return false
		System.out.println("Attempt to slide up.");
		if (b.slide("up"))
		{
			System.out.println("Unexpected outcome: slide up when empty tile in last row.");
			b.display();
		}
		else System.out.println("can not slide up AS EXPECTED.");
		// Slide left Should return false
		System.out.println("Attempt to slide left.");
		if (b.slide("left"))
		{
			System.out.println("Unexpected outcome: slide left when empty tile in last column.");
			b.display();
		}
		else System.out.println("can not slide left AS EXPECTED.");
		// Slide down should return true
		System.out.println("Attempt to slide down.");
		if (b.slide("down"))
			b.display();
		else System.out.println("Unexpected result: sliding down rejected when it should be accepted.");
		// Slide right should return true
		System.out.println("Attempt to slide right.");
		if (b.slide("right"))
			b.display();
		else System.out.println("Unexpected result: sliding right rejected when it should be accepted.");
		// Slide up
		System.out.println("Attempt to slide up.");
		if (b.slide("up"))
			b.display();
		else System.out.println("Unexpected result: sliding up rejected when it should be accepted.");
		// Slide left
		System.out.println("Attempt to slide left.");
		if (b.slide("left"))
			b.display();
		else System.out.println("Unexpected result: sliding left rejected when it should be accepted.");
		
		System.out.println("Attempt to slide right then down 3 times.");
		for (int i = 0 ; i<3 ;i++)
		{
			b.slide("right");
			b.slide("down");
		}
		b.display();
		
		// Slide Right should return false
		System.out.println("Attempt to slide Right.");
		if (b.slide("right"))
		{
			System.out.println("Unexpected outcome: slide right when empty tile in first column.");
			b.display();
		}
		else System.out.println("can not slide right AS EXPECTED.");
		// Slide down Should return false
		System.out.println("Attempt to slide down.");
		if (b.slide("down"))
		{
			System.out.println("Unexpected outcome: slide down when empty tile in first row.");
			b.display();
		}
		else System.out.println("can not slide down AS EXPECTED.");
		
		// Test shuffle function
		System.out.println("Attempt to shuffle the board with 1 random move.");
		b.shuffle(1);
		b.display();
		
		// Test shuffle function
		System.out.println("Attempt to shuffle the board with 3 random moves.");
		b.shuffle(3);
		b.display();
	}
}
