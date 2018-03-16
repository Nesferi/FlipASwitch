package com.example.nestorfernandez.flipaswitch.Game;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.nestorfernandez.flipaswitch.AudioService;

public class GameActivity extends AppCompatActivity {

    private Intent serviceIntent;
    private ServiceConnection serviceConnection;
    private boolean isServiceBound;
    private AudioService audioService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Eliminamos el título de la pantalla
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Habilitamos el modo fullscreen a la aplicación
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Cargamos el GameView en la actividad

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);


        serviceIntent=new Intent(this,AudioService.class);
        startService(serviceIntent);
        PlayMusic();

        setContentView(new GameView(this));
        
    }

    private void PlayMusic() {

        Log.i("service2", "Aun no en service connected");
        serviceConnection=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                isServiceBound=true;
                Log.i("service2", "On service connected");
                audioService=((AudioService.MyServiceBinder) service).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                isServiceBound=false;
            }
        };
        bindService(serviceIntent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    /*
    Capturamos si pulsa el botón de atrás en el teléfono
     */
    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        stopService(serviceIntent);
        Log.i("etiqueta","activity finish");
    }
}
