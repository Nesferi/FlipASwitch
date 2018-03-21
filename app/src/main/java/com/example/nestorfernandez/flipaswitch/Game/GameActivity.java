package com.example.nestorfernandez.flipaswitch.Game;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.SyncStateContract;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.nestorfernandez.flipaswitch.Model.User;
import com.example.nestorfernandez.flipaswitch.constant;

import com.example.nestorfernandez.flipaswitch.AudioService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ThrowOnExtraProperties;
import com.google.firebase.database.ValueEventListener;

public class GameActivity extends Activity {

    private Intent serviceIntent;
    private ServiceConnection serviceConnection;
    private boolean isServiceBound;
    private AudioService audioService;
    private FirebaseAuth auth;
    private FirebaseDatabase db;

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
        auth=FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance();
        if(auth.getCurrentUser()!=null) {
            db.getReference("users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.i("Datos",dataSnapshot.getValue().toString());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            db.getReference("users").child(auth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.i("Datos",dataSnapshot.toString());
                  User usuario=dataSnapshot.getValue(User.class);
                    db.getReference("puntos").child(usuario.getName()).setValue(constant.getPoints());

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        else
            Toast.makeText(this,"Necesitas estar registrado", Toast.LENGTH_LONG).show();
    }
}
