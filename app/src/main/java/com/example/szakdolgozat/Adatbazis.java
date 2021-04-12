package com.example.szakdolgozat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Adatbazis extends SQLiteOpenHelper {

    public static final int Db_version = 1;
    public static final String Db_name = "szakdoga.db";

    public static final String PALYA_TABLA = "palya";

    public static final String OSZLOP_NEV = "nev";
    public static final String OSZLOP_KANYAR = "kanyar";

    public Adatbazis(Context context) {
        super(context, Db_name, null, Db_version);
    }

    public void onCreate (SQLiteDatabase db){
        db.execSQL("create table palya(OSZLOP_NEV text, OSZLOP_KANYAR integer)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL = "DROP TABLE IF EXISTS " + PALYA_TABLA;
        db.execSQL(SQL);
        onCreate(db);

    }

    public boolean insertpalya (String nev, int kanyar){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("OSZLOP_NEV",nev);
        contentValues.put("OSZLOP_KANYAR",kanyar);
        long result = db.insert("palya",null, contentValues);
        if (result==1){
            return false;
        }
        else{
            return true;
        }

    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM palya");
    }

    public Cursor getListElements(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + PALYA_TABLA,null);
        return data;
    }
}
