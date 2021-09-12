package com.example.szakdolgozat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MediaPlayer menusong= MediaPlayer.create(MainActivity.this,R.raw.f1theme);
        menusong.setLooping(true);
        menusong.start();
        ImageButton img_bttn = findViewById(R.id.img_button);
        img_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menusong.stop();
                Intent mainscreen = new Intent(MainActivity.this, GameActivity.class);
                startActivity(mainscreen);
                finish();
            }
        });

    }

}