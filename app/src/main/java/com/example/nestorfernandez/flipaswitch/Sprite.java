package com.example.nestorfernandez.flipaswitch;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by nestor.fernandez on 26/01/2018.
 */

public class Sprite {


    private final int BMP_COLUMNS=3;
    private final int BMP_ROWS=4;
    private int xSpeed=5;
    private int x=0;
    private int y=0;
    private int width;
    private int height;
    private int currentFrame=0;


    private Bitmap bmp;
    private GameView gameView;

    public Sprite(GameView gameView, Bitmap bmp){
        this.gameView = gameView;
        this.bmp = bmp;
        this.width=bmp.getWidth()/BMP_COLUMNS;
        this.height=bmp.getHeight()/BMP_ROWS;
    }

    private void update(){
        //Si llega a alguno de los margenes, media vuelta
        if(x>gameView.getWidth()-width-xSpeed || x +xSpeed<0){
            xSpeed=-xSpeed;
        }
        x=x+xSpeed;
        //Al hacer el mod entre el numero de columnas, siempre rota los valores
        //Por ejemplo: 3 columnas va cogiendo 0,1,2,0,1,2,0,1,2...
        currentFrame=++currentFrame%BMP_COLUMNS;
    }

    public void onDraw(Canvas canvas){
        update();
        //El rectangulo empieza segun la posicion de la columna que se quiera coger
        int srcX=currentFrame*width;
        //El rectangulo empieza en una fila distitna en funcion de la orientacion
        int srcY=getOrientacion()*height;
        //Cogemos "la casilla de sprite" que nos interesa pintar
        Rect src = new Rect(srcX,srcY,srcX+width,srcY+height);
        //Cogemos "la casilla" de las coordenadas del destino
        Rect dst=new Rect(x,y,x+width,y+height);
        canvas.drawBitmap(bmp,src,dst,null);
    }

    private int getOrientacion(){
        if(xSpeed>0){
            return 2;
        }
        return 1;
    }
}
