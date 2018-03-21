package com.example.nestorfernandez.flipaswitch;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nestorfernandez.flipaswitch.Game.GameActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuActivity extends Activity {

    private Button btnPlay;
    private Button btnLeaderboard;
    private Button btnLogIn;
    private Button btnRegister;
    private Context ctx = this;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

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

        //FIREBASE
        auth = FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance();
        users=db.getReference("users");
        DatabaseReference puntos = db.getReference("puntos");


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
                showRegisterDialog();
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

    private void showRegisterDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("REGISTER");
        dialog.setMessage("Registrate");

        LayoutInflater inflater=LayoutInflater.from(this);
        View register = inflater.inflate(R.layout.register,null);
        dialog.setView(register);

        final EditText emailText= register.findViewById(R.id.emailText);
        final EditText passwordText= register.findViewById(R.id.passwordText);

        dialog.setPositiveButton("REGISTER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                auth.signInWithEmailAndPassword("df","DASF")
                        .onS
                auth.createUserWithEmailAndPassword(emailText.getText().toString(),passwordText.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                /*Se ha registrado bien*/
                                Toast.makeText(ctx, "Usuario registrado", Toast.LENGTH_SHORT).show();
                                users.push().setValue("hoal");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ctx, "Error en el registro", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        dialog.show();

    }


}
