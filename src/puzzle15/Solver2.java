package puzzle15;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
/**
 * @author Abdallah Sobehy, Fred Aklamanu, Mohsin Kazmi, Renaud
 * @version 2
 * <tt> Solver </tt> solves a 15 puzzle board.<br>
*/
public class Solver2 {
	// Board to be solved
	private Board board = new Board();;
	// Fitness value for the board before executing the next move
	private float current_fit_val;
	// number of moves to reach the solution
	private int num_moves;
	// maximum depth of the tree traversed to find the next move
	private int max_depth;
	// Move done in last step
	private String last_move;
	// Fitness class object
	Fitness fit = new Fitness();
	// Maps moves Strings to integer to facilitate traversing
	Map map = new HashMap<>();
	/*
	* Constructor resets num_moves, max_depth, last_move
	* Must be called each time before solving a new board 
	*/ 
	public Solver2()
	{
//		board.copy_board(b.get_board());
//		current_fit_val = fit.fitness_function_1(board);
		num_moves = 0;
		max_depth = 0;
		last_move = "none";
		map.put(0,"up"); map.put(1, "down"); map.put(2,"right"); map.put(3, "left");
	}
	


	/*
	 * Computes the next move to reach the solution for the board
	 * @param b board to compute the next move for 
	 */
	public String [] next_move(Board b, String last_move)
	{
		// Copy board to process without changing the original
		Board tmp = new Board();
		tmp.copy_board(b.get_board());
		// Call possible moves function to know possible moves with border restrictions and not repeating last move
		List possible_moves = possible_moves(tmp, last_move);
		//System.out.println(possible_moves);
		Iterator<String> iterator = possible_moves.iterator();
		// Try all possible moves and save the least fitness value and the corresponding move
		float min_fit_val = 2;
		String min_fit_move = null;
		while (iterator.hasNext()) {
			String move =  iterator.next();
			tmp.slide(move);
			float tmp_fit_val = fit.fitness_function_3(tmp);
			if ( tmp_fit_val < min_fit_val){
				min_fit_val = tmp_fit_val;
				min_fit_move = move;
			}
			// Return to previous state by performing slide in opposite direction
			tmp.slide(opposite_move(move));
		}
//		float minimum = Collections.min(fit_vals);
//		System.out.println("Minimum : " + min_fit_val);
//		System.out.println("Corresponding move: "+ min_fit_move);
		return new String [] {min_fit_move , Float.toString(min_fit_val)};
//		if (min_fit_val < current_fit_val)
//			return min_fit_move;
//		else return null;
	}
	
	/*
	 * Beats current fitness value with one move or more
	 */
	public ArrayList<String> beat_current_fit(ArrayList<Board> listOfBoards, ArrayList<ArrayList<String>> lOflOfMoves)
	{
		Iterator<Board> board_iterator = listOfBoards.iterator();
		Iterator<ArrayList<String>> lOfMoves_iterator = lOflOfMoves.iterator();
		System.out.println("Calling beat_current");
		// Update current fitness value the first time the function is called (when lOflOfMoves has 1 element which is empty move)
		if (lOflOfMoves.get(0).size() == 1)
		{
			current_fit_val = fit.fitness_function_3(listOfBoards.get(0));
			System.out.println("Updating current fitness value to : " +  current_fit_val);
//			System.out.println("First input board: " + listOfBoards.size());
//			listOfBoards.get(0).display();
		}
		float min_fit_val = 2;
		String min_fit_move = "";
		int minIndex = -1;
		int i = 0;
		while (board_iterator.hasNext()) 
		{
			Board b = board_iterator.next();
			ArrayList<String> moves = lOfMoves_iterator.next();
			// Call next_move with the last move of the board
			String [] min_found = next_move(b, moves.get(moves.size() - 1));
			Float board_min_fit_val = Float.parseFloat(min_found[1]);
			String board_min_fit_move = min_found[0];
			//System.out.println("board min fit move: "+ board_min_fit_move+ ", board min fit value " + board_min_fit_val);
			if (Float.parseFloat(min_found[1]) < min_fit_val )
			{
				min_fit_val = board_min_fit_val;
				min_fit_move = board_min_fit_move;
				minIndex = i;
			}
			i ++ ;
		}
		//System.out.println("All boards min fit move: "+ min_fit_move+ ",  min fit value " + min_fit_val + " , at index " + minIndex) ;
		if (min_fit_val < current_fit_val)
		{
			lOflOfMoves.get(minIndex).add(new String (min_fit_move));
			System.out.println("fit_val before beat current: " + current_fit_val + " After: " + min_fit_val);
			//System.out.println("Moves: "+lOflOfMoves.get(minIndex) );
			return lOflOfMoves.get(minIndex);
		}
		else
		{
			// Return the iterator to the start of the list of boards.
			board_iterator = listOfBoards.listIterator();
			lOfMoves_iterator = lOflOfMoves.listIterator();
			
			ArrayList<Board> next_level_boards = new ArrayList<Board>();
			ArrayList<ArrayList<String>> next_level_lOflOfMoves = new ArrayList<ArrayList<String>>();
			while (board_iterator.hasNext()) 
			{
				
				Board b = board_iterator.next();
				//b.display();
				ArrayList<String> moves = lOfMoves_iterator.next();
				//System.out.println(moves);
				String prev_move = moves.get(moves.size() - 1);
				System.out.println("Possible moves except : " + opposite_move(prev_move));
				ArrayList<String> possible_moves = possible_moves(b, prev_move);
				//System.out.println(possible_moves);
				Iterator<String> pos_moves_iterator = possible_moves.iterator();
				while (pos_moves_iterator.hasNext())
				{
					String slide_direction = pos_moves_iterator.next();
					//System.out.println("Possible direction: " + slide_direction);
					b.slide(slide_direction);
					Board b_tmp = new Board();
					b_tmp.copy_board(b);
					next_level_boards.add(b_tmp);
					//b.display();
					b.slide(opposite_move(slide_direction));
					//System.out.println(moves);
					// update next level list of list of moves
					ArrayList<String> tmp = new ArrayList<String>(moves);
					next_level_lOflOfMoves.add(tmp);
					next_level_lOflOfMoves.get(next_level_lOflOfMoves.size() -1 ).add(new String(slide_direction));
					
				}
				System.out.println(next_level_lOflOfMoves);
			}

			//System.out.println(next_level_lOflOfMoves);
//			return null;
			return beat_current_fit(next_level_boards, next_level_lOflOfMoves);
		
		}
	}
	/*
	 * Fully solves the board from a given position
	 */
	public ArrayList<String> solve(Board b)
	{
		// Prepare input for beat_current
		// Copies board so as not to change original board
		Board tmp_b = new Board();
		tmp_b.copy_board(b);
		// Initiate list of boards to be sent to beat_current function
		ArrayList<Board> l_b = new ArrayList<Board>();
		// Initiate the moves needed to reach the solution (starting with one empty string
		ArrayList<ArrayList<String>> lOflOfMoves = new ArrayList<ArrayList<String>>();
		// String list to store the moves needed to reach the solution
		ArrayList<String> solve_moves =  new ArrayList<String>();
		// Last move initilaized to empty first time
		String last_move = "";
		// Initialize current_fit_val
		current_fit_val = fit.fitness_function_3(b);
		while (current_fit_val != 0.0)
		{
			// Call pre_beat_current for initalizations
			pre_beat_current(tmp_b, l_b, lOflOfMoves, last_move);
			
			ArrayList<String> beat_moves = beat_current_fit(l_b, lOflOfMoves);
			System.out.println("Moves: "+beat_moves );
			Iterator<String> moves_iterator = beat_moves.iterator();
			// Ignore the first move because it is the last move before sending the board 
			moves_iterator.next();
			while (moves_iterator.hasNext())
			{
				Scanner in = new Scanner(System.in);
				System.out.println("Enter a string");
			    in.nextLine();
			    
				String s = moves_iterator.next();
				solve_moves.add(new String(s));
				tmp_b.slide(s);
				tmp_b.display();				
			}
			current_fit_val = fit.fitness_function_3(tmp_b);
			last_move = beat_moves.get(beat_moves.size() - 1);
		}
		return solve_moves;
	}
	/*
	 * Prepares Input to beat current function
	 */
	public void pre_beat_current(Board b, ArrayList<Board> listOfBoards, ArrayList<ArrayList<String>> lOflOfMoves, String last_move)
	{
			// Initiate list of boards to be sent to beat_current function
//			listOfBoards = new ArrayList<Board>();
			listOfBoards.clear();
			listOfBoards.add(b);
			// Initiate the moves needed to reach the solution (starting with one empty string)
//			lOflOfMoves = new ArrayList<ArrayList<String>>();
			lOflOfMoves.clear();
			ArrayList<String> lOfMoves = new ArrayList<String>();
			lOfMoves.add(last_move);
			lOflOfMoves.add(lOfMoves);
	}
	/*
	 * Returns an array of strings of the possible moves given the board and the previous move made
	 */
	public ArrayList<String> possible_moves( Board b, String l_move)
	{
		Board tmp = new Board();
		List possible_moves = new ArrayList<String>();
		
		for ( int i = 0 ; i < 4 ; i++)
		{
			tmp.copy_board(b.get_board());
			if (!(map.get(i).equals(opposite_move(l_move))) && tmp.slide((String)map.get(i)) )
				possible_moves.add((String)map.get(i));
		}
		//System.out.println(possible_moves);
		return (ArrayList<String>) possible_moves;
	}
	
	/*
	 * Returns the opposite move to the input move e.g. input = right, output = left
	 */
	public String opposite_move(String m)
	{
		switch(m)
		{
			case "right":return "left";
			case "left":return "right";
			case "up":return "down";
			case "down":return "up";
			default: return null;
		}
	}
	
}
