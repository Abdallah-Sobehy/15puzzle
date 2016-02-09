package com.telecom_sudparis.abdallah.puzzle15v2;
import puzzle15.*;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {
    // 2D array representation of the board.
    Board board;
    Button [][] tiles;
    final int X_DIFF = 264;
    final int Y_DIFF = 144;
    int board_dim = 4;
    int shuffle_times = 100;
    int shuffle_seed = 2;
    int nbOfMoves = 0;   // Tracks number of moves by user
    TextView moves;     // Display number of moves
  //  TextView myTimer;
    Chronometer myChronometer;
    long startTime;

    // Empty tile is represented by a button that will not be visible on the screen
    Button empty_tile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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



        Button btn_up = (Button)findViewById(R.id.btn_up);
        moves = (TextView)findViewById(R.id.movesId);
        moves.setText("Moves: " + nbOfMoves);


      //  myChronometer.setBase(SystemClock.elapsedRealtime());
        //myChronometer.setBase(SystemClock.elapsedRealtime());


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
                            tiles[t_move[0]][t_move[1]] = empty_tile;
                            nbOfMoves += 1;
                            System.out.println("Number of Moves: " + nbOfMoves);
                            moves.setText("Moves: " + nbOfMoves);
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
                            tiles[t_move[0]][t_move[1]] = empty_tile;
                            nbOfMoves += 1;
                            System.out.println("Number of Moves: " + nbOfMoves );
                            moves.setText("Moves: " + nbOfMoves);
                            System.out.println("Slided down.");
                        }

                    }
                }
        );

        Button btn_right = (Button)findViewById(R.id.btn_right);
        btn_right.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // If slide right is valid
                        if (board.slide("right")) {
                            // Position of the tile to be moved [Replaced by the empty tile in slide function]
                            int[] t_move = board.get_position(-1);
                            // Move the button
                            tiles[t_move[0]][t_move[1]].animate().translationXBy(X_DIFF);
                            // Update the place of the button to match the one in the board
                            tiles[t_move[0]][t_move[1] + 1] = tiles[t_move[0]][t_move[1]];
                            tiles[t_move[0]][t_move[1]] = empty_tile;
                            nbOfMoves += 1;
                            System.out.println("Number of Moves: " + nbOfMoves);
                            moves.setText("Moves: " + nbOfMoves);
                            System.out.println("Slided right.");
                        }

                    }
                }
        );

//        myTimer = (TextView)findViewById(R.id.timerId);
//
//        new CountDownTimer(60000, 1000) {
//
//
//            // Sets the Timer
//            public void onTick(long millisUntilFinished) {
//                myTimer.setText("Timer: " + millisUntilFinished / 1000 );
//            }
//
//            // Stops the timer and display the end of game
//            public void onFinish() {
//                System.out.println("Game Over!!!");
//                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
//                alertDialog.setTitle("Game Over!!!");
//                alertDialog.setMessage("Total Number of Moves " + nbOfMoves );
//                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Restart Game",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//
//                            }
//                        });
//                alertDialog.show();
//                // myTimer.setText("Game Over!!!");
//            }
//        }.start();

        // Resets the Timer


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
                            tiles[t_move[0]][t_move[1]] = empty_tile;
                            nbOfMoves += 1;
                            System.out.println("Number of Moves: " + nbOfMoves );
                            moves.setText("Moves: " + nbOfMoves );
                            System.out.println("Slided left.");
                        }

                    }
                }
        );

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
                        nbOfMoves = 0;
                        moves.setText("Moves: " + nbOfMoves );
                        myChronometer = (Chronometer)findViewById(R.id.chronometerId);
                        myChronometer.setText("Timer: " + "00:00");
                        myChronometer.setBase(SystemClock.elapsedRealtime());
                        myChronometer.start();


                    }
                }
        );
    }
    /*
    Display the content of the buttons 2D array on the terminal (for testing reasons)
    */
    void display_buttons(Button[][] tiles)
    {
        System.out.print("|");
        for (int i = 0; i < board_dim; i++) {
            for (int j = 0; j < board_dim; j++) {
                // Switch case to decide the output depending on the value of the tile
                switch (Integer.parseInt((String) (tiles[i][j]).getText())) {
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
                        System.out.print((String) (tiles[i][j]).getText() + "|");
                        break;
                    // Case for a one digit value number
                    default:
                        System.out.print((String) (tiles[i][j]).getText() + " |");
                }
                if (j == board_dim-1)
                {
                    System.out.print("\n");
                    if (i != board_dim-1)
                        System.out.print("|");
                }
            }
        }
        System.out.println();
    }

}
