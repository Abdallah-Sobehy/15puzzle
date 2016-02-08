package puzzle15;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Stack;

public class Solver {
	private class Node implements Comparable<Node> {
		Board board;
		float fitness_value;
		Node prevNode;
		
		private Node(Board board, float fitness_value, Node prevNode){
			this.board = board;
			this.fitness_value = fitness_value;
			this.prevNode = prevNode;
		}
		
		private Board getBoard() {
			return board;
		}

		private Node getPrevNode() {
			return prevNode;
		}

		public int compareTo(Node that) {
			if (this.fitness_value < that.fitness_value)
				return -1;
			else if (this.fitness_value > that.fitness_value)
				return 1;
			else
				return 0;
		}
	}

	PriorityQueue<Node> queue = new PriorityQueue<Node>();
	HashMap<Float,Integer> states = new HashMap<Float, Integer>();
	Stack<Board> stack = new Stack<Board>();
	

	    public Solver() {

	    }

	    public Board solver(Board initial){
	    	int i = 80;
	    	Board board = new Board();
	    	Board board1 = new Board();
	    	Board board2 = new Board();
	    	Board board3 = new Board();
	    	Board board4 = new Board();
	    	
	    	float initialValue, fitnessValue;
	    	boolean upFlag = true, downFlag = true, leftFlag = true, rightFlag = true;
	    	float upValue, downValue, leftValue, rightValue;
	    	Fitness fit = new Fitness();
	    	initialValue = fit.fitness_function_1(initial);
	    	
	    	board.copy_board(initial);
	    	fitnessValue = initialValue;
	    	Node prevNode = null;
	    	Node n,n1,n2,n3,n4;
	    	//int count = 1;
	    	
    		queue.add(new Node(board, fitnessValue, prevNode));
	    	
	    	while(i > 0) {
	    		
	    		/*if (states.containsKey(board.get_fitnessValue())) {
	    			board.display();
	    			Board bb = new Board();
					bb.copy_board(board);
	    			System.out.println("count1 = " + count);
	    				for (int j = 0; j < (count - states.get(board.get_fitnessValue())); j++) {
	    					queue.poll();
	    					System.out.println("Queue poll");
	    				}
	    				if (!queue.isEmpty() ) {
	    					board.copy_board(queue.poll().getBoard());
	    					board.display();
	    					states.put(board.get_fitnessValue(), ((states.get(bb.get_fitnessValue()) - 1) != 0) ? (states.get(bb) - 1):1);
	    				}
	    				
	    		} else {
	    			states.put(board.get_fitnessValue(), count);
					System.out.println("count2 = " + count);
	    		}*/
	    			//if (!isSolvable(board)) {
	    			//System.out.println("Board is not solvable");
	    			//break;
	    		//}
	    		n = new Node(board, fitnessValue, prevNode);
	    		prevNode = n;
	    		board.display();
	    		board1.copy_board(board);
	    		board2.copy_board(board);
	    		board3.copy_board(board);
	    		board4.copy_board(board);
	    		if (board1.slide("up") && upFlag) {
	    			//System.out.println("UP *************");
	    			upValue = fit.fitness_function_1(board1);
	    			n1 = new Node(board1, upValue, n);
	    			queue.add(n1);
	    			//count++;
	    		} else {
	    			upValue = 1;
	    			upFlag = true;
	    		}
	    		if (board2.slide("down") && downFlag) {
	    			//System.out.println("down *************");
	    			downValue = fit.fitness_function_1(board2);
	    			n2 = new Node(board2, downValue, n);
	    			queue.add(n2);
	    			//count++;
	    		} else {
	    			downValue = 1;
	    			downFlag = true;
	    		}
	    		if (board3.slide("right") && rightFlag) {
	    			//System.out.println("right *************");
	    			rightValue = fit.fitness_function_1(board3);
	    			n3 = new Node(board3, rightValue, n);
	    			queue.add(n3);
	    			//count++;
	    		} else {
	    			rightValue = 1;
	    			rightFlag = true;
	    		}
	    		if (board4.slide("left") && leftFlag) {
	    			//System.out.println("left *************");
	    			leftValue = fit.fitness_function_1(board4);
	    			n4 = new Node(board4, leftValue, n);
	    			queue.add(n4);
	    			//count++;
	    		} else {
	    			leftValue = 1;
	    			leftFlag = true;
	    		}
	    		
	    		
	    		// values comparison
	    		if (upValue <= downValue && rightValue <= leftValue) {
	    			if (upValue <= rightValue) {
	    				board.copy_board(board1);
	    				//System.out.println("1");
	    				fitnessValue = upValue;
	    				downFlag = false;
	    			} else {
	    				board.copy_board(board3);
	    				//System.out.println("2");
	    				fitnessValue = rightValue;
	    				leftFlag = false;
	    			}
	    		} 
	    		else if (upValue <= downValue && rightValue >= leftValue) {
	    			if (upValue <= leftValue) {
	    				board.copy_board(board1);
	    				//System.out.println("3");
	    				fitnessValue = upValue;
	    				downFlag = false;
	    			} else {
	    				board.copy_board(board4);
	    				//System.out.println("4");
	    				fitnessValue = leftValue;
	    				rightFlag = false;
	    			}
	    		}
	    		else if (upValue >= downValue && rightValue <= leftValue) {
	    			if (downValue <= rightValue) {
	    				board.copy_board(board2);
	    				//System.out.println("5");
	    				fitnessValue = downValue;
	    				upFlag = false;
	    			} else {
	    				board.copy_board(board3);
	    				//System.out.println("6");
	    				fitnessValue = rightValue;
	    				leftFlag = false;
	    			}
	    		}
	    		else if  (upValue >= downValue && rightValue >= leftValue) {
	    			if (downValue <= leftValue) {
	    				board.copy_board(board2);;
	    				//System.out.println("7");
	    				fitnessValue = downValue;
	    				upFlag = false;
	    			}
	    			else {
	    				board.copy_board(board4);
	    				//System.out.println("8");
	    				fitnessValue = leftValue;
	    				rightFlag = false;
	    			}
	    		}

	    		stack.push(board);
	    		i--;
	    		
	    		if (upValue == 0 || downValue == 0 || rightValue == 0 || leftValue == 0) {
	    			System.out.println("break");
	    			break;
	    		}
	    	}
	    	return board;
	    }
	    
	   /* public boolean isSolvable(Board board) {
	    	int[] indexBlank = new int[2];
	    	int[] linearArray = new int[16];
	    	int[][] boardArray = new int[4][4];
	    	int nbInversions = 0;
	    	boardArray = board.get_board();
	    	indexBlank = board.get_position(-1);
	    	for (int i=0; i < 4; i++)
	    		for (int j=0; j < 4; j++)
	    			linearArray[j + (i*4)] = boardArray[i][j];
	    	
	    	for (int i = 0; i<16;i++)
	    		for (int j=i+1;j<16;j++)
	    			if (linearArray[i] > linearArray[j] && (linearArray[j] != -1))
	    				nbInversions++;
	    	System.out.println(indexBlank[0]);
	    	System.out.println(nbInversions);
	    	if (((indexBlank[0] + 1) % 2 == 0) && ((nbInversions % 2) != 0) || ((indexBlank[0] + 1) % 2 != 0) && ((nbInversions % 2) == 0))
	    		return true;
	    	else
	    		return false;
	    				
	    } */

}
