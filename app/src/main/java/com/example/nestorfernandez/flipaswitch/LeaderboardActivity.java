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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Habilitamos el modo fullscreen a la aplicación
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_leaderboard);

        TextView[] playerArray = new TextView[5];
        TextView[] pointsArray = new TextView[5];

        playerArray[0] = (TextView) findViewById(R.id.txtPlayer1);
        playerArray[1] = (TextView) findViewById(R.id.txtPlayer2);
        playerArray[2] = (TextView) findViewById(R.id.txtPlayer3);
        playerArray[3] = (TextView) findViewById(R.id.txtPlayer4);
        playerArray[4] = (TextView) findViewById(R.id.txtPlayer5);
        pointsArray[0] = (TextView) findViewById(R.id.txtPoints1);
        pointsArray[1] = (TextView) findViewById(R.id.txtPoints2);
        pointsArray[2] = (TextView) findViewById(R.id.txtPoints3);
        pointsArray[3] = (TextView) findViewById(R.id.txtPoints4);
        pointsArray[4] = (TextView) findViewById(R.id.txtPoints5);

        BDHelper helper = new BDHelper(this);
        helper.openDB();
        String info = helper.showPuntos();
        String[] parts = info.split("\n");
        // Relleno las puntuaciones que haya (Mínimo dos puntuaciones y máximo 5)
        for (int i=0; i<parts.length; i++) {
            System.out.println("partes"+parts.length);
        }
            if(parts.length>=2){

        for (int i=0; i<parts.length; i++){
            String[] nampoin = parts[i].split("_");
            System.out.println("player array "+i+" : "+playerArray[i]);
            System.out.println("points array "+i+" : "+pointsArray[i]);
            playerArray[i].setText(nampoin[0]);
            pointsArray[i].setText(nampoin[1]);
        }
        }
        helper.closeDB();

    }
}
