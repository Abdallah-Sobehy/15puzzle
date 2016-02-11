package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import puzzle15.Board;
import puzzle15.Solver2;

public class JUSolver2 {

	private Solver2 solver = new Solver2(1);
	Board b;
	@Before
	public void setUp() throws Exception {
		b = new Board();
	}

	//@Test
	public void test()
	{
		solver.next_move(b, "");
		b.slide("down");
		solver.next_move(b, "");
		b.slide("right");
		solver.next_move(b, "");
		
	}
	
	//@Test
	public void test_beat_current()
	{
		ArrayList<Board> l_b = new ArrayList<Board>();
		//b.slide("right");
		//b.shuffle(15);
		b.shuffle(4, 2);
		b.display();
		l_b.add(b);
		ArrayList<ArrayList<String>> lOflOfMoves = new ArrayList<ArrayList<String>>();
		ArrayList<String> lOfMoves = new ArrayList<String>();
		lOfMoves.add("");
		lOflOfMoves.add(lOfMoves);
		
		ArrayList<String> beat_moves = solver.beat_current_fit(l_b, lOflOfMoves);
		System.out.println("Beat current with : "+ beat_moves);
		
		Iterator<String> moves_iterator = beat_moves.iterator();
		moves_iterator.next();
		while (moves_iterator.hasNext())
		{
			b.slide(moves_iterator.next());
			b.display();
		}
		// Another beat
		
		beat_moves = solver.beat_current_fit(l_b, lOflOfMoves);
		System.out.println("Beat current with : "+ beat_moves);
		
		
		
		
		//b.slide("right");
		//solver.beat_current_fit(l_b, lOflOfMoves);
		
	}
	
	//@Test
	public void test_possible_moves()
	{
		System.out.println(solver.possible_moves(b, "down"));
	}
	
	@Test
	public void test_solve()
	{
		//b.shuffle((float)0.3,(float)0.5);
		b.shuffle(25,11);
		b.display();
		Scanner in = new Scanner(System.in);
		System.out.println("Press enter to beign solving");
	    in.nextLine();
		ArrayList<String> beat_moves = solver.solve(b);
		System.out.println(beat_moves);
		if (beat_moves != null)
			System.out.println("number of moves : " + beat_moves.size());
	}

}
