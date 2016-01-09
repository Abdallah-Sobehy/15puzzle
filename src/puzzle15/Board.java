package puzzle15;
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

}
