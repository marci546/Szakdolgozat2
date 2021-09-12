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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game2Activity extends AppCompatActivity {
    ImageButton joystickleft, joystickright;
    ImageView leftlamp, rightlamp, car, pilot;
    TextView time;
    Date wholeGameStart, turnStartTime, turnEndTime;
    long turnCurrentTime;
    int turnsTaken = 0;
    int numberOfTurns, currentLap;
    ArrayList<Long> turnTimers;
    SimpleDateFormat idoFormatum = new SimpleDateFormat("mm:ss.SSS");
    Timer t = new Timer();
    Random rnd = new Random();

    // Játékkal kapcsolatos változók definiálása, és inicializálása
    int chosenLamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);
        Intent intent = getIntent();
        numberOfTurns = intent.getIntExtra("turnCount", 1);
        String chosenTrack = intent.getStringExtra("chosenTrack");
        turnTimers = new ArrayList(numberOfTurns);

        // Widget azonositok hozzarendelese
        leftlamp = findViewById(R.id.leftlamp);
        rightlamp = findViewById(R.id.rightlamp);
        pilot = findViewById(R.id.pilota);
        car = findViewById(R.id.auto);
        joystickleft = findViewById(R.id.leftjoystick);
        joystickright = findViewById(R.id.rightjoystick);
        time = findViewById(R.id.time);

        // azonositokhoz kapcsolt widgetek parameterezeseinek atallitasa
        String chosenPilotAndCar = intent.getStringExtra("chosenPilot").replaceAll(" ", "");
        int chosenPilot = getResources().getIdentifier((chosenPilotAndCar.split(",")[0].toLowerCase()), "drawable", getPackageName());
        int chosenCar = getResources().getIdentifier((chosenPilotAndCar.split(",")[1].toLowerCase()), "drawable", getPackageName());
        pilot.setImageResource(chosenPilot);
        car.setImageResource(chosenCar);

        joystickleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chosenLamp == 0) {
                    turnEndTime = new Date();
                    turnCurrentTime = turnEndTime.getTime() - turnStartTime.getTime();
                    turnTimers.add(turnCurrentTime);
                    lampaAlaphelyzet();
                } else {
                    balLampaAtkapcsolas("redlamp");
                    jobbLampaAtkapcsolas("redlamp");
                    hibasMegoldasFenyjelzes();
                }
                if(turnTimers.size() > numberOfTurns-1) {
                    jatekVege();
                }
            }
        });

        // Jobb joystick click
        joystickright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chosenLamp == 1) {
                    // HELYES Megoldás
                    turnEndTime = new Date();
                    turnCurrentTime = turnEndTime.getTime() - turnStartTime.getTime();
                    turnTimers.add(turnCurrentTime);
                    lampaAlaphelyzet();
                } else {
                    balLampaAtkapcsolas("redlamp");
                    jobbLampaAtkapcsolas("redlamp");
                    hibasMegoldasFenyjelzes();
                }
                if(turnTimers.size() > numberOfTurns-1) {
                    jatekVege();
                }
            }
        });

        lampaAlaphelyzet();
        idozito();
    }

    void lampaAlaphelyzet() {
        chosenLamp = -1;
        balLampaAtkapcsolas("greylamp");
        jobbLampaAtkapcsolas("greylamp");
        ujFenyjelzes();
    }

    void balLampaAtkapcsolas(String lampatipus) {
        leftlamp.setImageResource(getResources().getIdentifier(lampatipus, "drawable", getPackageName()));
        leftlamp.setTag(lampatipus);
    }

    void jobbLampaAtkapcsolas(String lampatipus) {
        rightlamp.setImageResource(getResources().getIdentifier(lampatipus, "drawable", getPackageName()));
        rightlamp.setTag(lampatipus);
    }

    void hibasMegoldasFenyjelzes() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                lampaAlaphelyzet();
            }
        }, 1000);
    }

    void idozito() {
        wholeGameStart = new Date();
        int count = 0;
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                time.setText(String.valueOf(idoFormatum.format((new Date()).getTime() - wholeGameStart.getTime())));
            }
        }, 0, 10);
    }

    void ujFenyjelzes() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(turnTimers.size() <= numberOfTurns-1) {
                    turnStartTime = new Date();
                    currentLap++;
                    chosenLamp = rnd.nextInt(2);
                    if(chosenLamp == 0) {
                        balLampaAtkapcsolas("greenlamp");
                    } else {
                        jobbLampaAtkapcsolas("greenlamp");
                    }
                }
            }
        }, rnd.nextInt(2000)+1000);
    }

    void jatekVege() {
        t.cancel(); // Időzitő leállitása

        // Átlagos reakcióidő kiszámitása a turnTimers tömb elemeinek összeadásával, és az elemszám elosztásával (AVG számitás)
        long sum = 0;
        for (long turnTimer: turnTimers) {
            sum += turnTimer;
        }
        long avgTime = sum/turnTimers.size();

        // Alertdialog mutatása az átlagos reakcióidővel, és a teljes játékidővel
        AlertDialog.Builder builder1 = new AlertDialog.Builder(Game2Activity.this, R.style.MyDialogTheme);
        builder1.setTitle("Vége! Újra szeretnéd kezdeni?");
        builder1.setMessage("Átlagos reakcióidő: " + idoFormatum.format(avgTime) + "\nTeljes játékidő: " + idoFormatum.format((new Date()).getTime() - wholeGameStart.getTime()));
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Igen",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent mainscreen = new Intent(Game2Activity.this, GameActivity.class);
                        startActivity(mainscreen);
                        finish();
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "Nem",
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