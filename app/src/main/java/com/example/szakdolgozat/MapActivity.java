package com.example.szakdolgozat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class MapActivity extends AppCompatActivity {

    ListView mapView;

    Adatbazis db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        db = new Adatbazis(this);
        db.deleteAll();
        boolean austrian = db.insertpalya("Austrian Grand Prix, Turns: 10", 10);
        boolean styrian = db.insertpalya("Styrian Grand Prix, Turns: 10", 10);
        boolean hungarian = db.insertpalya("Hungarian Grand Prix, Turns: 14", 14);
        boolean british = db.insertpalya("British Grand Prix, Turns: 18", 18);
        boolean seventy = db.insertpalya("70th Aniversary Grand Prix, Turns: 18", 18);
        boolean spanish = db.insertpalya("Spanish Grand Prix, Turns: 16", 16);
        boolean belgian = db.insertpalya("Belgian Grand Prix, Turns: 19", 19);
        boolean italian = db.insertpalya("Italian Grand Prix, Turns: 11", 11);
        boolean tuscan = db.insertpalya("Tuscan Grand Prix, Turns: 15", 15);
        boolean russian = db.insertpalya("Russian Grand Prix, Turns: 18", 18);
        boolean eifel = db.insertpalya("Eifel Grand Prix, Turns: 15", 15);
        boolean portual = db.insertpalya("Portugal Grand Prix, Turns: 15", 15);
        boolean emilia = db.insertpalya("Emilia Romagna Grand Prix, Turns: 19", 19);
        boolean turkish = db.insertpalya("Turkish Grand Prix, Turns: 14", 14);
        boolean bahrain = db.insertpalya("Bahrain Grand Prix, Turns: 15", 15);
        boolean sakhir = db.insertpalya("Sakhir Grand Prix, Turns: 11", 11);
        boolean abudhabi = db.insertpalya("Abu Dhabi Grand Prix, Turns: 21", 21);

        mapView = (ListView)findViewById(R.id.tracks);

        ArrayList<String> theList = new ArrayList<>();
        Cursor data = db.getListElements();

        ArrayList<Integer> turnList = new ArrayList<>(Arrays.asList(10, 10, 14, 18, 18, 16, 19, 11, 15, 18, 15, 15, 19, 14, 15, 11, 21));

        Cursor data2 = db.getListElements();


        if (data.getCount() == 0) {
            Toast.makeText(MapActivity.this, "Adatbázis üres", Toast.LENGTH_SHORT).show();
        }
        else{
            while(data.moveToNext()){
                theList.add(data.getString(0));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                mapView.setAdapter(listAdapter);
            }
        }

        mapView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Toast.makeText(MapActivity.this, "Choosen track: " +i+ ". "+theList.get(i), Toast.LENGTH_SHORT).show();

                Intent mainscreen = new Intent(MapActivity.this, Game2Activity.class);
                mainscreen.putExtra("chosenTrack", theList.get(i));
                mainscreen.putExtra("turnCount", turnList.get(i));
                startActivity(mainscreen);
                finish();
            }
        });
    }
}