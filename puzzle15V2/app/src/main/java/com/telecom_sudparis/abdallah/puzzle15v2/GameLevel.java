package com.telecom_sudparis.abdallah.puzzle15v2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Fred on 05-Feb-16.
 */
public class GameLevel extends AppCompatActivity {

    Button snailbtn;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_game_level);
        addListenerOnButton();
    }

    public void addListenerOnButton() {

        final Context context = this;

        snailbtn = (Button) findViewById(R.id.snailId);

        snailbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);

            }

        });
    }


}
