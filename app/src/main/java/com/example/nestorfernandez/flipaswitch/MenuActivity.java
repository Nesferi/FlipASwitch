package com.example.nestorfernandez.flipaswitch;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nestorfernandez.flipaswitch.Game.GameActivity;

public class MenuActivity extends Activity {

    private Button btnPlay;
    private Button btnLeaderboard;
    private Button btnRegister;
    private Context ctx = this;
    private Boolean secondChance = false;

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
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLeaderboard = (Button) findViewById(R.id.btnLeaderboard);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx,GameActivity.class);
                startActivity(intent);

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterDialog();
            }
        });

        btnLeaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx,LeaderboardActivity.class);
                startActivity(intent);
            }
        });

    }


    private void showRegisterDialog() {

            //Creo el Alert de logeo
            final AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
            dialog.setTitle("Elige un nombre para guardar tus puntos");
            //Boolean para cambiar el título si es la primera vez o no
            if(secondChance){
                dialog.setMessage("Por favor, escribe algo!");
            }else{
                dialog.setMessage("Escribe tu nombre para guardar tus puntos!");
            }

            LayoutInflater inflater=LayoutInflater.from(ctx);
            View endGame = inflater.inflate(R.layout.endgame,null);
            dialog.setView(endGame);

            final EditText userText= endGame.findViewById(R.id.userText);

            dialog.setPositiveButton("REGISTER", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    System.out.println(" "+userText.getText().toString());
                        //Si el nombre está vacío, volvemos a cargar el dialog
                        if((" "+userText.getText()).equals(" ")){
                            secondChance = true;
                            showRegisterDialog();
                        }else{
                            secondChance = false;
                            constant.setUserName(userText.getText().toString());
                            Toast.makeText(ctx, "A partir de ahora, tus puntos se guardaran como: "+userText.getText().toString(), Toast.LENGTH_SHORT).show();
                        }

                }
            });
            dialog.show();
    }


}
