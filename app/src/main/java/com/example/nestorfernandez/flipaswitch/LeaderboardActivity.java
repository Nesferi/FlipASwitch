package com.example.nestorfernandez.flipaswitch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.nestorfernandez.flipaswitch.Model.Points;
import com.example.nestorfernandez.flipaswitch.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LeaderboardActivity extends AppCompatActivity {

    private TextView txtPlayer1;
    private TextView txtPlayer2;
    private TextView txtPlayer3;
    private TextView txtPlayer4;
    private TextView txtPlayer5;
    private TextView txtPoints1;
    private TextView txtPoints2;
    private TextView txtPoints3;
    private TextView txtPoints4;
    private TextView txtPoints5;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Habilitamos el modo fullscreen a la aplicación
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_leaderboard);

        txtPlayer1 = (TextView) findViewById(R.id.txtPlayer1);
        txtPlayer2 = (TextView) findViewById(R.id.txtPlayer2);
        txtPlayer3 = (TextView) findViewById(R.id.txtPlayer3);
        txtPlayer4 = (TextView) findViewById(R.id.txtPlayer4);
        txtPlayer5 = (TextView) findViewById(R.id.txtPlayer5);
        txtPoints1 = (TextView) findViewById(R.id.txtPoints1);
        txtPoints2 = (TextView) findViewById(R.id.txtPoints2);
        txtPoints3 = (TextView) findViewById(R.id.txtPoints3);
        txtPoints4 = (TextView) findViewById(R.id.txtPoints4);
        txtPoints5 = (TextView) findViewById(R.id.txtPoints5);

        auth = FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance();
        points=db.getReference("puntos");

        db.getReference("puntos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("Datos",dataSnapshot.getValue()+"");
                Points points=dataSnapshot.getValue(Points.class);

                Log.i("Datos","Name   "+points.getName());
                Log.i("Datos","Points   "+points.getPoints());

            }

            @Override

            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
