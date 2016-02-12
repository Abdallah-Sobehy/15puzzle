package puzzle15;

public class Fitness1 extends Fitness {

	public Fitness1() {
		super();
	}
	
	/*
	 * fitness_function_1 considers:
	 * 	1) the number of misplaced tiles (means give weightage to each tile place) and 
	 *  2) misplaced tiles distance in terms of steps from the tile original position.
	 * 
	 * Algorithm gives 50% weightage to 1) and 50% weightage to 2) for calculating
	 * fitness value of given board and at the end, both values are added up to return.
	 * 
	 */
	public float fitness_function(Board board) {
		float ret1 = 0, ret2 = 0;

		ret1 = tiles_in_position(board);
		ret2 = displace_tiles_steps(board); 
	
		return (float) (0.5 * ret1 + 0.5 * ret2);
	}	
}
