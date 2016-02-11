package puzzle15;

/**
 * @author Abdalla Sobehy, Fred Aklamanu, Mohsin Kazmi, Renaud
 * @version 1
 * <tt> Fitness </tt> is a fitness function to evaluate how close we are to the solved board.<br>
 * Input: valid board input which is randomly shuffle.
 * return: value b/w 0 and 1. ( 0 means solved state & 1 means most mixed up state of the board )
 * Tested by {@link TestBoard}
 */
public abstract class Fitness {

	private float most_mixed_up = 1;
	
	
	public abstract float fitness_function(Board board);
	protected float tiles_in_position(Board board) {
		float tiles_in_position = 0;
		float ret = 0; 
		int [] index;
		int [] temp_index;
		Board solved = new Board();
		for(int i=0;i<17;i++){
			if (i == 1)
				continue;
			index = board.get_position(i-1);
			temp_index = solved.get_position(i-1);
			if (index[0] == temp_index[0] && index[1] == temp_index[1])
			{
				tiles_in_position += 1;
			} 
		}
		ret = (float)(most_mixed_up - tiles_in_position/16.0);
		return ret;
	}

	protected float displace_tiles_steps(Board board) {
		float total_tile_steps = 0;
		float ret = 0; 
		int [] index;
		int [] temp_index;
		Board solved = new Board();
		for(int i=0;i<17;i++){
			if (i == 1)
				continue;
			index = board.get_position(i-1);
			temp_index = solved.get_position(i-1);
			if (!(index[0] == temp_index[0] && index[1] == temp_index[1]))
			{
				if (index[0] < temp_index[0])	
					total_tile_steps +=  temp_index[0] - index[0];
				else
					total_tile_steps += index[0] - temp_index[0];
		
				if (index[1] < temp_index[1])
					total_tile_steps +=  temp_index[1] - index[1];
				else
					total_tile_steps += index[1] - temp_index[1];
			}
		}
		
		/* In most mixed up board, by keeping maximization of tile steps
		 * from its original position. 
		 * |12 |15 | 9 |13 |
		 * |11 |   |10 |14 |
		 * | 8 | 3 | 2 | 5 |
		 * | 4 | 7 | 6 | 1 |
		 * 
		 * In this case, we require 60 single tile moves
		 */
		//System.out.println("total steps required: " + total_tile_steps);
		ret = (float) (total_tile_steps/60);
		return ret;
	}
}
