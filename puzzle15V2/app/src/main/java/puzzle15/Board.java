package puzzle15;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Abdalla Sobehy, Fred Aklamanu, Mohsin Kazmi, Renaud
 * @version 1
 * <tt> Board </tt> is a board for the puzzle 15 game.<br>
* The board is a 4x4 2D integer array with 15 tiles numbered from 1 to 15 and one empty tile.
* Tested by {@link TestBoard}
*/
public class Board 
{
	private int dim = 4;
	private int [][] board = new int [dim][dim];
	/*
	 * Constructor initializes the board in the solved position
	 * The empty tile is represented by -1
	 */
	public Board()
	{
		for ( int i = 0 ; i < dim ; i++)
		{
			for ( int j = 0 ; j < dim ; j++)
			{
				board[i][j] = j + 1 + (i*dim);
			}
		}
		// write the empty tile to -1 
		board[dim-1][dim-1] = -1;
	}
	/*
	 * @return 2D representation of the board
	 */
	public int [][] get_board()
	{
		return board;
	}
	/*
	 * Displays the board
	 */
	public void display()
	{
		System.out.print("|");
		for ( int i = 0 ; i < dim ; i++)
		{
			for ( int j = 0 ; j < dim ; j++)
			{
				// Switch case to decide the output depending on the value of the tile
				switch (board[i][j])
				{
					// Case no tile (empty) which is represented by -1
					case -1:
						System.out.print("  |");
						break;
					// Case of two digit value tile
					case 10:
					case 11:
					case 12:
					case 13:
					case 14:
					case 15:
						System.out.print(board[i][j] + "|");
						break;
					// Case for a one digit value number
					default:
						System.out.print(board[i][j] + " |");	
				}
				
				if (j == dim-1)
				{
					System.out.print("\n");
					if (i != dim-1)
						System.out.print("|");
				}
			}
		}
		System.out.println();
	}
	/*
	 * Finds the index of a tile in the board
	 * @param tile number on the tile to be searched or -1 to get the index of the empty tile.
	 * @return the index of the searched tile.
	 * @throws IllegalArgumentException if the searched tile is not from 1 to 15 or -1
	 */
	public int [] get_position(int tile) throws IllegalArgumentException
	{
		if ( (tile < 1 || tile > 15) && tile != -1 )
			throw new IllegalArgumentException("Wrong inout for get_position function");
		for ( int i = 0 ; i < dim ; i++)
		{
			for ( int j = 0 ; j < dim ; j++)
			{
				if (board[i][j] == tile)
				{
					int [] index = {i,j};
					return index;
				}
			}
		}
		return null;
	}
	
	/*
	 * Slides a tile up/down/right/left depending on the place of the empty tile.
	 * @param direction a string to specify the direction of sliding of the tile. 
	 * should take one of the following values "up", "down", "right", "left"
	 * @return true if the move is successfully executed
	 * false if the move is not possible to be done e.g. sliding up when the empty tile is in the last row
	 */
	public boolean slide(String direction ) throws IllegalArgumentException
	{
		// Get index of the empty tile
		int [] empty_i = get_position(-1);
		// tile number to be slided
		int moved_tile;
		switch (direction)
		{
			case "up":
				// Move is not possible if the empty tile is in the last row
				if (empty_i[0] == dim - 1)
					return false;
				// The tile to be moved is the one under the empty tile
				moved_tile = board[empty_i[0]+1][empty_i[1]];
				// Switch moved tile with empty tile
				board[empty_i[0]][empty_i[1]] = moved_tile;
				board[empty_i[0]+1][empty_i[1]] = -1;
				break;
			case "down":
				// Move is not possible if the empty tile is in the first row
				if (empty_i[0] == 0)
					return false;
				// The tile to be moved is the one above the empty tile
				moved_tile = board[empty_i[0]-1][empty_i[1]];
				// Switch moved tile with empty tile
				board[empty_i[0]][empty_i[1]] = moved_tile;
				board[empty_i[0]-1][empty_i[1]] = -1;
				break;
			case "right":
				// Move is not possible if the empty tile is in the first column
				if (empty_i[1] == 0)
					return false;
				// The tile to be moved is the one to the left of the empty tile
				moved_tile = board[empty_i[0]][empty_i[1]-1];
				// Switch moved tile with empty tile
				board[empty_i[0]][empty_i[1]] = moved_tile;
				board[empty_i[0]][empty_i[1]-1] = -1;
				break;
			case "left":
				// Move is not possible if the empty tile is in the last column
				if (empty_i[1] == dim -1)
					return false;
				// The tile to be moved is the one to the right of the empty tile
				moved_tile = board[empty_i[0]][empty_i[1]+1];
				// Switch moved tile with empty tile
				board[empty_i[0]][empty_i[1]] = moved_tile;
				board[empty_i[0]][empty_i[1]+1] = -1;
				break;
			default:
				throw new IllegalArgumentException("Wrong input to slide function");
		}
			return true;
	}

	/*
	 * Shuffles the board with random number of moves.
	 * @param num_moves number of moves to be randomly executed on the board.
	 */
	public void shuffle(int num_moves)
	{
		// Map to map randomly generated integers to directions of moves
		Map map = new HashMap();
		map.put(0,"up"); map.put(1, "down"); map.put(2,"right"); map.put(3, "left");
		int rnd;
		for (int i = 0 ; i < num_moves ; i++)
		{
			Random ran = new Random();
			rnd = ran.nextInt(4);
			// If the randomly selected move is not possible decrease i to repeat.
			if (!slide((String) map.get(rnd)))
				i--;
			
		}
	}
	
	/*
	 * Shuffles the board with random number of moves.
	 * @param num_moves number of moves to be randomly executed on the board.
	 * @param seed to generate the random moves
	 */
	public void shuffle(int num_moves, int seed)
	{
		// Map to map randomly generated integers to directions of moves
		Map map = new HashMap();
		map.put(0,"up"); map.put(1, "down"); map.put(2,"right"); map.put(3, "left");
		int rnd;
		Random ran = new Random(seed);
		for (int i = 0 ; i < num_moves ; i++)
		{
			rnd = ran.nextInt(4);
			System.out.println("Random move is : " + map.get(rnd));
			// If the randomly selected move is not possible decrease i to repeat.
			if (!slide((String) map.get(rnd)))
				i--;
			
		}
	}
	/*
	 * Copies the input board.
	 * @param b board to be copied.
	 */
	public void copy_board(Board b)
	{
		for ( int i = 0 ; i < dim ; i++)
		{
			for ( int j = 0 ; j < dim ; j++)
			{
				board[i][j] = b.get_board()[i][j];
			}
		}
	}
	/*
	 * Copies the input board.
	 * @param b int[][] to be copied.
	 */
	public void copy_board(int[][] b)
	{
		for ( int i = 0 ; i < dim ; i++)
		{
			for ( int j = 0 ; j < dim ; j++)
			{
				board[i][j] = b[i][j];
			}
		}

		//fitnessValue = fitness.fitness_function(this);
	}
}