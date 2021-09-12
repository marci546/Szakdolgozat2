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

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        listView = (ListView)findViewById(R.id.drivers);
        ArrayList<String> vezetok = new ArrayList<>();

        vezetok.add("Lewis Hamilton, Mercedes");
        vezetok.add("Valtteri Bottas, Mercedes");
        vezetok.add("Max Verstappen, Red Bull");
        vezetok.add("Alexander Albon, Red Bull");
        vezetok.add("Lando Norris, McLaren");
        vezetok.add("Carlos Sainz Jr, McLaren");
        vezetok.add("Daniel Ricciardo, Rebault");
        vezetok.add("Esteban Ocon, Renault");
        vezetok.add("Sebastian Vettel, Ferrari");
        vezetok.add("Charles Leclerc, Ferrari");
        vezetok.add("Sergio Perez, Racing Point");
        vezetok.add("Lance Stroll, Racing Point");
        vezetok.add("Pierre Gasly, Alpha Tauri");
        vezetok.add("Daniil Kvyat , Alpha Tauri");
        vezetok.add("Kimi Raikkonen, Alfa Romeo");
        vezetok.add("Antonio Giovinazzi, Alfa Romeo");
        vezetok.add("Romain Grosjean, Haas");
        vezetok.add("Kevin Magnussen, Haas");
        vezetok.add("George Russel, Williams");
        vezetok.add("Nicholas Latifi, Williams");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, vezetok);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Toast.makeText(GameActivity.this, "Choosen driver: " +i+ ". "+vezetok.get(i), Toast.LENGTH_SHORT).show();

                Intent mainscreen = new Intent(GameActivity.this, MapActivity.class);
                mainscreen.putExtra("chosenPilot", vezetok.get(i));
                startActivity(mainscreen);
                finish();
            }
        });
    }
}