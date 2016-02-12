package tests;

import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

import puzzle15.Board;
import puzzle15.Solver;

public class JUSolver {

	private Solver solver = new Solver();
	Board b;
	
	@Before
	public void setUp() throws Exception {
		b = new Board();
	}
	
	@Test
	public void test() {
		Stack<Board> stack = new Stack<Board>();
		Stack<Board> stack1 = new Stack<Board>();

		b.shuffle(40, 2);
        for (Board board : solver.solver(b)) {
        	stack.push(board);
        }
        while (!stack.isEmpty()) {
        	stack1.push(stack.pop());
        }
        while(!stack1.isEmpty()){
          stack1.peek().display();
          stack1.pop();
        }
	}

}
