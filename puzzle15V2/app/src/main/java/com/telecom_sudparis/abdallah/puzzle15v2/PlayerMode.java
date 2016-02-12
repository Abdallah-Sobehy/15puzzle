package com.telecom_sudparis.abdallah.puzzle15v2;
import puzzle15.*;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import java.util.prefs.Preferences;

public class PlayerMode extends AppCompatActivity implements View.OnClickListener {
    // 2D array representation of the board.
    Board board;
    TextView moves;
    Button [][] tiles;
    final int X_DIFF = 264;
    final int Y_DIFF = 144;
    int board_dim = 4;
    public static int shuffle_times = 100;
    int shuffle_seed = 2;
    // Empty tile is represented by a button that will not be visible on the screen
    Button empty_tile;
    int nbOfMoves = 0;
    Chronometer myChronometer;
    Fitness myFitnessValue = new Fitness1();


    // Move tile up in both interface and board
    public void move_up()
    {
        if (board.slide("up"))
        {
            // Position of the tile to be moved [Replaced by the empty tile in slide function]
            int [] t_move = board.get_position(-1);
            // Move the button
            tiles[t_move[0]][t_move[1]].animate().translationYBy(-Y_DIFF);
            // Update the place of the button to match the one in the board
            tiles[t_move[0]-1][t_move[1]] = tiles[t_move[0]][t_move[1]];
            tiles[t_move[0]][t_move[1]] = empty_tile;
            System.out.println("Slided Up.");
            moves = (TextView)findViewById(R.id.movesId);
            nbOfMoves += 1;
            moves.setText("Moves: " + nbOfMoves);

            if ( myFitnessValue.fitness_function(board) == 0.0) {

                AlertDialog alertDialog = new AlertDialog.Builder(PlayerMode.this).create();
                alertDialog.setTitle("Congratulations, you are the current champion!!!!");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                            }
                        });
                alertDialog.show();
            }
        }
    }
    // Move tile down in both interface and board
    public void move_down()
    {
        if (board.slide("down"))
        {
            // Position of the tile to be moved [Replaced by the empty tile in slide function]
            int [] t_move = board.get_position(-1);
            // Move the button
            tiles[t_move[0]][t_move[1]].animate().translationYBy(Y_DIFF);
            // Update the place of the button to match the one in the board
            tiles[t_move[0]+1][t_move[1]] = tiles[t_move[0]][t_move[1]];
            tiles[t_move[0]][t_move[1]] = empty_tile;
            System.out.println("Slided down.");
            moves = (TextView)findViewById(R.id.movesId);
            nbOfMoves += 1;
            moves.setText("Moves: " + nbOfMoves);

            if ( myFitnessValue.fitness_function(board) == 0.0) {

                AlertDialog alertDialog = new AlertDialog.Builder(PlayerMode.this).create();
                alertDialog.setTitle("Congratulations, you are the current champion!!!!");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                            }
                        });
                alertDialog.show();
            }
        }
    }
    // Move tile right in both interface and board
    public void move_right()
    {
        if (board.slide("right"))
        {
            // Position of the tile to be moved [Replaced by the empty tile in slide function]
            int [] t_move = board.get_position(-1);
            // Move the button
            tiles[t_move[0]][t_move[1]].animate().translationXBy(X_DIFF);
            // Update the place of the button to match the one in the board
            tiles[t_move[0]][t_move[1]+1] = tiles[t_move[0]][t_move[1]];
            tiles[t_move[0]][t_move[1]] = empty_tile;
            System.out.println("Slided right.");
            moves = (TextView)findViewById(R.id.movesId);
            nbOfMoves += 1;
            moves.setText("Moves: " + nbOfMoves);

            if ( myFitnessValue.fitness_function(board) == 0.0) {

                AlertDialog alertDialog = new AlertDialog.Builder(PlayerMode.this).create();
                alertDialog.setTitle("Congratulations, you are the current champion!!!!");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                            }
                        });
                alertDialog.show();
            }
        }
    }
    // Move tile left in both interface and board
    public void move_left()
    {
        if (board.slide("left"))
        {
            // Position of the tile to be moved [Replaced by the empty tile in slide function]
            int [] t_move = board.get_position(-1);
            // Move the button
            tiles[t_move[0]][t_move[1]].animate().translationXBy(-X_DIFF);
            // Update the place of the button to match the one in the board
            tiles[t_move[0]][t_move[1]-1] = tiles[t_move[0]][t_move[1]];
            tiles[t_move[0]][t_move[1]] = empty_tile;
            System.out.println("Slided left.");
            moves = (TextView)findViewById(R.id.movesId);
            nbOfMoves += 1;
            moves.setText("Moves: " + nbOfMoves);

            if ( myFitnessValue.fitness_function(board) == 0.0) {

                AlertDialog alertDialog = new AlertDialog.Builder(PlayerMode.this).create();
                alertDialog.setTitle("Congratulations, you are the current champion!!!!");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                            }
                        });
                alertDialog.show();
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_main);
        // Initialize board
        board = new Board();
        // Initialize tiles 2D array to be able to point to buttons (The buttons should correspond to their
        // values in the board. Thus, initialized to their solved position.
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

        // Empty tile is represented by a button that will not be visible on the screen
        empty_tile =new Button(this);
        empty_tile.setText("-1");
        tiles[3][3] = empty_tile;

        // Set on click listeners for all buttons (Except the empty tile)
        for (int i = 0 ; i < 4 ; i++)
        {
            for (int j = 0 ; j < 4 ; j ++)
            {
                Button b = (Button)tiles[i][j];
                b.setOnClickListener(this);
            }
        }

        Button btn_shuffle = (Button)findViewById(R.id.btn_shuffle);
        btn_shuffle.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Shuffle the board
                        board.shuffle(shuffle_times);
                        // Temp values to update the interface and buttons 2D array with the new state of the board.
                        // Stores the value of the string text of the button
                        String tmp_value;
                        // position of the tile in the shuffles board
                        int[] tmp_pos;
                        // tmp button for aimating the selected button to match the button to match the position in the shuffled board
                        Button tmp_button;
                        // 2D array of buttons stores new positions of buttons to be pointed at by the global tiles array in the end.
                        Button[][] tmp_tiles = new Button[4][4];
                        board.display();

                        for (int i = 0; i < board_dim; i++) {
                            for (int j = 0; j < board_dim; j++) {
                                tmp_button = tiles[i][j];
                                tmp_value = (String) (tmp_button).getText();
                                tmp_pos = board.get_position(Integer.parseInt(tmp_value));
                                tmp_button.animate().translationXBy((tmp_pos[1] - j) * X_DIFF);
                                tmp_button.animate().translationYBy((tmp_pos[0] - i) * Y_DIFF);
                                tmp_tiles[tmp_pos[0]][tmp_pos[1]] = tmp_button;
                            }
                        }
                        tiles = tmp_tiles;
                        moves = (TextView)findViewById(R.id.movesId);
                        nbOfMoves = 0;
                        moves.setText("Moves: " + nbOfMoves);
                        myChronometer = (Chronometer)findViewById(R.id.chronometerId);
                        myChronometer.setText("Timer: " + "00:00");
                        myChronometer.setBase(SystemClock.elapsedRealtime());
                        myChronometer.start();
                    }
                }
        );
    }
    public void onClick(View v) {
        String text_inButton;
        text_inButton = (String) ((TextView) v).getText();
        int[] b = new int[2], s = new int[2];
        int number = Integer.parseInt(text_inButton);
        b = board.get_position(number);
        s = board.get_position(-1);
        System.out.println("Empty tile position : " + s);
        System.out.println("Pressed button tile position : " + b);

        // Checking if the pressed button is a neighor of the empty tile
        if ((s[0] == b[0] && Math.abs(s[1] - b[1]) == 1) || (s[1] == b[1] && Math.abs(s[0] - b[0]) == 1)) {
            // If the 2 buttons are on the same column, then translate in Y direction
            if (s[0] == b[0]) {
                // If the pressed button is above the empty button then move down
                if (s[1] > b[1])
                    move_right();
                    // Else move up
                else
                    move_left();
            }
            // If the 2 buttons are on the same row, then translate in X direction
            if (s[1] == b[1]) {
                // If the pressed button is on the left of the empty button then move right
                if (s[0] > b[0])
                    move_down();
                    // Else move left
                else
                    move_up();
            }
        }
    }

}
