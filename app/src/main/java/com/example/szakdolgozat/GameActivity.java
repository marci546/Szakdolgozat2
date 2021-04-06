package com.example.szakdolgozat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.content.SharedPreferences;
import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    String driverdata;


    public String valsztott_jatekos (String jatekos){
        return jatekos;
    }


    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);


        listView = (ListView)findViewById(R.id.drivers);
        ArrayList<String> vezetok = new ArrayList<>();

        vezetok.add("Lewis Hamilton, Mercedes, Speed: 9.5");
        vezetok.add("Valtteri Bottas, Mercedes, Speed: 8.5");
        vezetok.add("Max Verstappen, Red Bull, Speed: 9");
        vezetok.add("Alexander Albon, Red Bull, Speed: 7.8");
        vezetok.add("Lando Norris, McLaren, Speed: 8");
        vezetok.add("Carlos Sainz Jr., McLaren, Speed: 8.2");
        vezetok.add("Daniel Ricciardo, Rebault, Speed: 8.3");
        vezetok.add("Esteban Ocon, Renault, Speed: 7.5");
        vezetok.add("Sebastian Vettel, Ferrari, Speed: 7.4");
        vezetok.add("Charles Leclerc, Ferrari, Speed: 8");
        vezetok.add("Sergio Perez, Racing Point, Speed: 8.1");
        vezetok.add("Lance Stroll, Racing Point, Speed: 7.3");
        vezetok.add("Pierre Gasly, Alpha Tauri, Speed: 7.5");
        vezetok.add("Daniil Kvyat , Alpha Tauri, Speed: 7.2");
        vezetok.add("Kimi Raikkonen, Alfa Romeo, Speed: 7.8");
        vezetok.add("Antonio Giovinazzi, Alfa Romeo, Speed: 7");
        vezetok.add("Romain Grosjean, Haas, Speed: 6.8");
        vezetok.add("Kevin Magnussen, Haas, Speed: 6.9");
        vezetok.add("George Russel, Williams, Speed: 7");
        vezetok.add("Nicholas latifi, Williams, Speed: 6.5");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, vezetok);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Toast.makeText(GameActivity.this, "Choosen driver: " +i+ ". "+vezetok.get(i), Toast.LENGTH_SHORT).show();

                /*valamit kell kezdeni ezzel a kod resszel :) */


                driverdata = vezetok.get(i);


                /*atvisszuk masik oldalra a kivalaszott vezetot*/
                Intent mainscreen = new Intent(GameActivity.this, MapActivity.class);
                startActivity(mainscreen);
                finish();
            }
        });
    }
}