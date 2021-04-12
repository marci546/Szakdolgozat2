package com.example.szakdolgozat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Game2Activity extends AppCompatActivity {

    TextView track, wetTrack, counter;

    Button start_bttn, main_bttn;
    Random rng = new Random();
    int r = rng.nextInt(2);

    long startTime, endTime, currentTime;

    boolean isRacing = true;
    int turnsTaken = 0;
    int numberOfTurns;

    int laps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);


        track = (TextView)findViewById(R.id.chosenTrack);
        Intent intent = getIntent();
        String chosenTrack = intent.getStringExtra("chosenTrack");
        track.setText(chosenTrack);

        numberOfTurns = intent.getIntExtra("turnCount",2);

        r += 1;
        wetTrack = (TextView)findViewById(R.id.wetTrack);
        if (r == 1){
            wetTrack.setText("Dry track");
        }
        else{
            wetTrack.setText("Wet track");
        }

        counter = (TextView)findViewById(R.id.counter);

        start_bttn = (Button)findViewById(R.id.start_bttn);
        main_bttn = (Button)findViewById(R.id.main_bttn);

        start_bttn.setEnabled(true);
        main_bttn.setEnabled(false);



        start_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnsTaken = 1;
                laps = 1;
                final Handler handler = new Handler();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        startTime = System.currentTimeMillis();
                        main_bttn.setBackgroundColor(getResources().getColor(R.color.red));
                        main_bttn.setText("BRAKE!");
                        main_bttn.setEnabled(true);
                        if (turnsTaken < numberOfTurns){
                            handler.postDelayed(this, 3000);
                        }
                        else{
                            turnsTaken = 1;
                            laps++;
                            if (laps <= 10){
                                counter.setText("Lap: " + laps + "/10");
                                handler.postDelayed(this, 3000);
                            }
                            else{
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(Game2Activity.this);
                                builder1.setMessage("You did it!\nWant a new game?");
                                builder1.setCancelable(true);

                                builder1.setPositiveButton(
                                        "Yes",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                Intent mainscreen = new Intent(Game2Activity.this, MainActivity.class);
                                                startActivity(mainscreen);
                                                finish();
                                                dialog.cancel();
                                            }
                                        });

                                builder1.setNegativeButton(
                                        "No",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                                finish();
                                            }
                                        });

                                AlertDialog alert11 = builder1.create();
                                alert11.show();
                            }
                        }
                    }
                }, 2000);
            }
        });

        main_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endTime = System.currentTimeMillis();
                currentTime = endTime - startTime;
                main_bttn.setBackgroundColor(getResources().getColor(R.color.blue));
                main_bttn.setText(currentTime + " ms\nBe ready!");
                turnsTaken++;
                main_bttn.setEnabled(false);
            }
        });

    }
}