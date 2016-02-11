package puzzle15;

public class Fitness2 extends Fitness {

	public Fitness2() {
		super();
	}
	
	/*
	 * fitness_function_2 considers:
	 * 	The number of misplaced tiles
	 * if the board is in the solved state, this function return 0
	 * if all the tile are misplaced, this function return 1
	 * 
	 * @param board Board where is applied fitness function
	 * @ return float ratio between the number of misplaced tiled,and the total number of tile
	 */
	
	public float fitness_function(Board board) {
		return tiles_in_position(board);
	}
}
