package puzzle15;

public class Fitness3 extends Fitness {

	public Fitness3() {
		super();
	}
	
	/*
	 * fitness_function_3 considers:
	 * Misplaced tiles distance in terms of steps from the tile original position.
	 * if the board is in the solved state, this function return 0
	 * if all the tile are misplaced, this function return 1
	 * 
	 * @param board Board where is applied fitness function
	 * @ return float ratio between the number of misplaced tiled,and the total number of tile
	 */
	public float fitness_function(Board board) {
		
		return displace_tiles_steps(board); 
		
	}
}
