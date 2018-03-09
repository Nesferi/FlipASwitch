package com.example.nestorfernandez.flipaswitch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Eliminamos el título de la pantalla
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Habilitamos el modo fullscreen a la aplicación
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Cargamos el GameView en la actividad
        setContentView(new GameView(this));
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

    }

    /*
    Capturamos si pulsa el botón de atrás en el teléfono
     */
    @Override
    public void onBackPressed() {
        finish();
    }
}
