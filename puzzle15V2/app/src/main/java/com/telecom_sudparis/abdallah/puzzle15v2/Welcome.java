package com.telecom_sudparis.abdallah.puzzle15v2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import org.w3c.dom.Text;

/**
 * Created by Fred on 05-Feb-16.
 */
public class Welcome extends AppCompatActivity {

    Button mybtn;
    EditText myname;
    String playername;


    // getter for username
    public EditText getmyname () {
        return myname;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);
        addListenerOnButton();
    }

    public void addListenerOnButton() {

        final Context context = this;

        mybtn = (Button) findViewById(R.id.confirmId);
        myname = (EditText) findViewById(R.id.nameId);
        playername = myname.getText().toString();


        mybtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if ( myname.getText().toString().trim().length() == 0 )
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(Welcome.this).create();
                    alertDialog.setTitle("Name Required!!!");
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Please enter your name",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                            }
                        });
                alertDialog.show();
                } else {

                    Intent intent = new Intent(context, GameMode.class);
                    intent.putExtra("mytext", playername );
                    startActivity(intent);
                }

            }

        });


    }
}
