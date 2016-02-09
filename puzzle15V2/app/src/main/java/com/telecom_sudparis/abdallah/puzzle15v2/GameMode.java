package com.telecom_sudparis.abdallah.puzzle15v2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Fred on 06-Feb-16.
 */
public class GameMode extends AppCompatActivity {

    Button computerBtn;
    Button playerBtn;
    TextView mylogin;



    // Computer Mode
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_mode);
        addListenerOnButton();
        addListenerOnButtonPlayer();
    }

    public void addListenerOnButton() {

        final Context context = this;


        computerBtn = (Button) findViewById(R.id.computerID);

        computerBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, GameLevel.class);
                startActivity(intent);

            }

        });
    }

       // Player Mode
       public void onCreatePlayer(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.game_mode);
          // addListenerOnButton();
           addListenerOnButtonPlayer();
       }

        public void addListenerOnButtonPlayer() {

        final Context context = this;

        playerBtn = (Button) findViewById(R.id.playerId);

        playerBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, GameLevel.class);
                startActivity(intent);

            }

        });
    }
}
