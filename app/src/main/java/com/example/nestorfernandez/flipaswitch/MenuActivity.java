package com.example.nestorfernandez.flipaswitch;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.nestorfernandez.flipaswitch.Game.GameActivity;

public class MenuActivity extends AppCompatActivity {

    private Button btnPlay;
    private Button btnLeaderboard;
    private Button btnLogIn;
    private Button btnRegister;
    private Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Eliminamos el título de la pantalla
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Habilitamos el modo fullscreen a la aplicación
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);


        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnLogIn = (Button) findViewById(R.id.btnLog);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLeaderboard = (Button) findViewById(R.id.btnLeaderboard);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx,GameActivity.class);
                startActivity(intent);

            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnLeaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
    }


}
