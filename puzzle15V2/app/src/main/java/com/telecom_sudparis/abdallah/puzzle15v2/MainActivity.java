package com.telecom_sudparis.abdallah.puzzle15v2;
import puzzle15.*;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {
    // 2D array representation of the board.
    Board board;
    Fitness myFitnessValue = new Fitness1();
    Solver2 mysolver = new Solver2();
    Button [][] tiles;
    final int X_DIFF = 264;
    final int Y_DIFF = 144;
    int board_dim = 4;
    int shuffle_times = 100;
    int shuffle_seed = 2;
    int nbOfMoves = 0;   // Tracks number of moves by user
    TextView moves;     // Display number of moves
    TextView fitness;
    Chronometer myChronometer;
    long startTime;
    public static int random = 27;
    public static int seed = 1;
    ArrayList<String> myhint;
    TextView hintmove;
    Iterator<String> iterator;
    Thread mythread = new Thread();
    int [] t_move;
    TextView status;

    // boolean to prevent shuffling more than once
    boolean shuffled = false;
    // boolean to indicate solve is pressed in valid timing
    boolean solve_pressed = false;
    // boolean to indicate solving is done and viewed fully
    boolean solved = false;


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



        // Button btn_up = (Button)findViewById(R.id.btn_up);
        moves = (TextView)findViewById(R.id.movesId);
        fitness = (TextView)findViewById(R.id.fitnessId);
        hintmove = (TextView)findViewById(R.id.hintId);
        moves.setText("Moves: " + nbOfMoves);
        System.out.println("Random: " + random + "," + "Seed: " + seed);

        //  myChronometer.setBase(SystemClock.elapsedRealtime());
        //myChronometer.setBase(SystemClock.elapsedRealtime());


        Button btn_shuffle = (Button)findViewById(R.id.btn_shuffle);
        btn_shuffle.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!shuffled) {
                            shuffled = true;
                            // Shuffle the board
                            board.shuffle(random, seed);
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
                            moves.setText("Moves: " + nbOfMoves);
                            myChronometer = (Chronometer) findViewById(R.id.chronometerId);
                            myChronometer.setText("Timer: " + "00:00");
                            myChronometer.setBase(SystemClock.elapsedRealtime());
                            myChronometer.start();
                            fitness.setText("FV: " + myFitnessValue.fitness_function(board));
                        }
                        else
                        {
                            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                            alertDialog.setTitle("You can only shuffle once in solver mode.");
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
        );


        /**
         * Solve Button automates solves the game into the final state
         */


        Button mysolve = (Button)findViewById( R.id.solveId );
        Button nextmove = (Button)findViewById(R.id.nextMoveId);

        nextmove.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (!shuffled)
                        {
                            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                            alertDialog.setTitle("Please shuffle the board first.");
                            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();

                                        }
                                    });
                            alertDialog.show();
                        }
                        else if (!solve_pressed)
                        {
                            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                            alertDialog.setTitle("Press solve to compute next steps first");
                            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();

                                        }
                                    });
                            alertDialog.show();
                        }
                        else if (shuffled && !solved && solve_pressed)
                        {
                            if (iterator.hasNext()) {
                                String myNextMove = iterator.next();

                                System.out.println("Next Move: " + myNextMove);

                                switch (myNextMove) {


                                    case "up":
                                        if (board.slide("up")) {
                                            t_move = board.get_position(-1);
                                            // Move the button
                                            tiles[t_move[0]][t_move[1]].animate().translationYBy(-Y_DIFF);
                                            // Update the place of the button to match the one in the board
                                            tiles[t_move[0] - 1][t_move[1]] = tiles[t_move[0]][t_move[1]];
                                            tiles[t_move[0]][t_move[1]] = empty_tile;
                                            nbOfMoves += 1;
                                            System.out.println("Number of Moves: " + nbOfMoves);
                                            moves.setText("Moves: " + nbOfMoves);
                                            System.out.println("Slided Up.");
                                            fitness.setText("FV: " + myFitnessValue.fitness_function(board));

                                        }
                                        break;

                                    case "down":

                                        if (board.slide("down")) {
                                            // Position of the tile to be moved [Replaced by the empty tile in slide function]
                                            int[] t_move = board.get_position(-1);
                                            // Move the button
                                            tiles[t_move[0]][t_move[1]].animate().translationYBy(Y_DIFF);
                                            // Update the place of the button to match the one in the board
                                            tiles[t_move[0] + 1][t_move[1]] = tiles[t_move[0]][t_move[1]];
                                            tiles[t_move[0]][t_move[1]] = empty_tile;
                                            nbOfMoves += 1;
                                            System.out.println("Number of Moves: " + nbOfMoves);
                                            moves.setText("Moves: " + nbOfMoves);
                                            System.out.println("Slided down.");
                                            fitness.setText("FV: " + myFitnessValue.fitness_function(board));
                                        }
                                        break;


                                    case "left":

                                        if (board.slide("left")) {
                                            t_move = board.get_position(-1);
                                            // Move the button
                                            tiles[t_move[0]][t_move[1]].animate().translationXBy(-X_DIFF);
                                            // Update the place of the button to match the one in the board
                                            tiles[t_move[0]][t_move[1] - 1] = tiles[t_move[0]][t_move[1]];
                                            tiles[t_move[0]][t_move[1]] = empty_tile;
                                            nbOfMoves += 1;
                                            System.out.println("Number of Moves: " + nbOfMoves);
                                            moves.setText("Moves: " + nbOfMoves);
                                            System.out.println("Slided left.");
                                            fitness.setText("FV: " + myFitnessValue.fitness_function(board));
                                        }
                                        break;

                                    case "right":

                                        if (board.slide("right")) {
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
                                            fitness.setText("FV: " + myFitnessValue.fitness_function(board));
                                        }
                                        break;
                                    default:
                                        System.out.println("Next move not found");


                                }  // end of switch
                                if (myFitnessValue.fitness_function(board) == 0.0) {

                                    solved = true;
                                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                                    alertDialog.setTitle("Congratulations, you are the current champion!!!!");
                                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();

                                                }
                                            });
                                    alertDialog.show();
                                }
                                hintmove.setText("Depth: " + myhint.get(myhint.size() - 1));

                            }   // end IF
                        }
                        else if (shuffled && solved)
                        {
                            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                            alertDialog.setTitle("You have already solved the board, Congratz.");
                            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();

                                        }
                                    });
                            alertDialog.show();
                        }

                    } // end OnClick
                }
        );

        mysolve.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (shuffled && !solve_pressed )
                        {

                            solve_pressed = true;
                            myhint = mysolver.solve(board);

                            AlertDialog alertDialog1 = new AlertDialog.Builder(MainActivity.this).create();
                            alertDialog1.setTitle("Thanks for waiting, now press on next move to see the magic!!!");
                            alertDialog1.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();

                                        }
                                    });
                            // alertDialog.dismiss();
                            alertDialog1.show();

                            iterator = myhint.iterator();

                            System.out.println("Size of ArrayList: " + myhint.size());

                            for (String move : myhint) {
                                if (move == null) {
                                    status.setText("Maximum depth reached ( Cannot continue, this will take too much time )");
                                }
                                System.out.println("Moves: " + move);
                            }

                            System.out.println("Random: " + random + "," + "Seed: " + seed);

                        }
                        else if (! shuffled){
                            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                            alertDialog.setTitle("Please shuffle before solving the board.");
                            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();

                                        }
                                    });
                            alertDialog.show();
                        }
                        else if (solved)
                        {
                            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                            alertDialog.setTitle("You have already solved the board, Congratz.");
                            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();

                                        }
                                    });
                            alertDialog.show();
                        }
                        else if (solve_pressed){
                            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                            alertDialog.setTitle("Solve processing already done, press next move to solve.");
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
