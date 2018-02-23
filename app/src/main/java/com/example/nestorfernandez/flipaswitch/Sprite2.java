package com.example.nestorfernandez.flipaswitch;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.example.nestorfernandez.flipaswitch.GameView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Sprite2 {


    private int ySpeed=15;
    private int x=200;
    private int y=200;
    private int width;

    private int width2;
    private int height;
    private int currentFrame=0;
    private int currentPosition=0;
    private boolean switched=false;
    private ArrayList<ArrayList<Bitmap>> spriteList;

    private GameView gameView;

    public Sprite2(GameView gameView, ArrayList<ArrayList<Bitmap>> spriteList){
        this.gameView = gameView;
        this.width= spriteList.get(1).get(1).getWidth();
        this.width2=spriteList.get(1).get(1).getWidth();
        this.height=spriteList.get(1).get(1).getHeight();
        this.spriteList=spriteList;
    }

    private void update(Rect game){
        y=y+ySpeed;
        //Calculamos la casilla de destino teórico
        Rect destino=new Rect(x,y,x+width2,y+height);
        // Si se sale de los limites del rectángulo jugable,
        // hacemos que deje de moverse en el eje de las Y para que no llegue a salirse

        if (!game.contains(destino)){
            ySpeed=0;
        }
        //Log.i("etiqueta","ySpeed = "+ySpeed);
        if(ySpeed!=0){
            currentPosition=1;
            width=spriteList.get(1).get(1).getWidth();
            height=spriteList.get(1).get(1).getHeight();
        }else{
            if(switched==true){
                currentPosition=2;
            }else{
                currentPosition=0;
            }
        }
        width=spriteList.get(currentPosition).get(1).getWidth();
        height=spriteList.get(currentPosition).get(1).getHeight();
        //Al hacer el mod entre el numero de columnas, siempre rota los valores
        //Por ejemplo: 3 columnas va cogiendo 0,1,2,0,1,2,0,1,2...
        currentFrame=++currentFrame%8;
    }

    public void onDraw(Canvas canvas, Rect game){
        update(game);
        //Cogemos "la casilla de sprite" que nos interesa pintar
        Rect src = new Rect(0,0,width,height);
        //Cogemos "la casilla" de las coordenadas del destino
        Rect dst=new Rect(x,y,x+width,y+height);
        canvas.drawBitmap(spriteList.get(currentPosition).get(currentFrame),src,dst,null);
    }


    public void flipASwitch(){
        Log.i("etiqueta","flipASwitch()");
        if(!switched){
            ySpeed=-25;
            switched=true;
        }else{
            ySpeed=25;
            switched=false;
        }
    }

}
