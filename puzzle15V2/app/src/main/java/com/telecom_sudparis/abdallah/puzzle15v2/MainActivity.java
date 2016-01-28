package com.telecom_sudparis.abdallah.puzzle15v2;
import puzzle15.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {
    // 2D array representation of the board.
    Board board;
    Button [][] tiles;
    final int X_DIFF = 264;
    final int Y_DIFF = 144;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize board
        board = new Board();
        // Initialize tiles 2D array to be able to point to buttons (The buttons should correspond to their
        // values in the board (except the empty tile as there is no button for it). Thus, initialized to their solved position.
        tiles = new Button [4][4];
        tiles[0][0] = (Button)findViewById(R.id.tile_1);
        tiles[0][1] = (Button)findViewById(R.id.tile_2);
        tiles[0][2] = (Button)findViewById(R.id.tile_3);
        tiles[0][3] = (Button)findViewById(R.id.tile_4);
        tiles[1][0] = (Button)findViewById(R.id.tile_5);
        tiles[1][1] = (Button)findViewById(R.id.tile_6);
        tiles[1][2] = (Button)findViewById(R.id.tile_7);
        tiles[1][3] = (Button)findViewById(R.id.tile_8);
        tiles[2][0] = (Button)findViewById(R.id.tile_9);
        tiles[2][1] = (Button)findViewById(R.id.tile_10);
        tiles[2][2] = (Button)findViewById(R.id.tile_11);
        tiles[2][3] = (Button)findViewById(R.id.tile_12);
        tiles[3][0] = (Button)findViewById(R.id.tile_13);
        tiles[3][1] = (Button)findViewById(R.id.tile_14);
        tiles[3][2] = (Button)findViewById(R.id.tile_15);



        Button btn_up = (Button)findViewById(R.id.btn_up);
        btn_up.setOnClickListener(
                new Button.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        // If slide up is valid
                        if (board.slide("up"))
                        {
                            // Position of the tile to be moved [Replaced by the empty tile in slide function]
                            int [] t_move = board.get_position(-1);
                            // Move the button
                            tiles[t_move[0]][t_move[1]].animate().translationYBy(-Y_DIFF);
                            // Update the place of the button to match the one in the board
                            tiles[t_move[0]-1][t_move[1]] = tiles[t_move[0]][t_move[1]];
                            System.out.println("Slided Up.");
                        }
                    }
                }
        );

        Button btn_down = (Button)findViewById(R.id.btn_down);
        btn_down.setOnClickListener(
                new Button.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        // If slide down is valid
                        if (board.slide("down"))
                        {
                            // Position of the tile to be moved [Replaced by the empty tile in slide function]
                            int [] t_move = board.get_position(-1);
                            // Move the button
                            tiles[t_move[0]][t_move[1]].animate().translationYBy(Y_DIFF);
                            // Update the place of the button to match the one in the board
                            tiles[t_move[0]+1][t_move[1]] = tiles[t_move[0]][t_move[1]];
                            System.out.println("Slided down.");
                        }

                    }
                }
        );

        Button btn_right = (Button)findViewById(R.id.btn_right);
        btn_right.setOnClickListener(
                new Button.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        // If slide right is valid
                        if (board.slide("right"))
                        {
                            // Position of the tile to be moved [Replaced by the empty tile in slide function]
                            int [] t_move = board.get_position(-1);
                            // Move the button
                            tiles[t_move[0]][t_move[1]].animate().translationXBy(X_DIFF);
                            // Update the place of the button to match the one in the board
                            tiles[t_move[0]][t_move[1]+1] = tiles[t_move[0]][t_move[1]];
                            System.out.println("Slided right.");
                        }

                    }
                }
        );

        Button btn_left = (Button)findViewById(R.id.btn_left);
        btn_left.setOnClickListener(
                new Button.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        // If slide left is valid
                        if (board.slide("left"))
                        {
                            // Position of the tile to be moved [Replaced by the empty tile in slide function]
                            int [] t_move = board.get_position(-1);
                            // Move the button
                            tiles[t_move[0]][t_move[1]].animate().translationXBy(-X_DIFF);
                            // Update the place of the button to match the one in the board
                            tiles[t_move[0]][t_move[1]-1] = tiles[t_move[0]][t_move[1]];
                            System.out.println("Slided left.");
                        }

                    }
                }
        );


    }
}
