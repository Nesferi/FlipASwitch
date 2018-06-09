package com.example.nestorfernandez.flipaswitch;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Néstor on 06/06/2018.
 */

public class BDHelper extends SQLiteOpenHelper {

    Context ctx;
    public BDHelper(Context context) {
        super(context, "bd_puntos", null, 1);
        ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS puntuacion(id INTEGER PRIMARY KEY AUTOINCREMENT, user TEXT NOT NULL, points INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    BDHelper helper;
    SQLiteDatabase db;
    public void openDB(){
        helper = new BDHelper(ctx);
        db=helper.getWritableDatabase();
    }
    public void closeDB(){
        db.close();
    }

    public void matalosATodos(){
        db.execSQL("DELETE FROM puntuacion");
    }

    public void regisPuntos(String user, Integer points){
        db.execSQL("INSERT INTO puntuacion (user,points) VALUES ('"+user+"',"+points+")");
    }

    public String showPuntos(){
        String datos = "";
        String [] columns= new String[]{"user","points"};
        Cursor c = db.query("puntuacion", columns,null,null,null,null,"points DESC","5");
        if(c.moveToFirst()){

        do {
            datos+=c.getString(c.getColumnIndex("user"));
            datos+="_";
            datos+=c.getString(c.getColumnIndex("points"));
            datos+="\n";
            System.out.println("Datitos: " +datos);
        }while(c.moveToNext());

        }

        return datos;
    }
}
