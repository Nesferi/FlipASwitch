package com.example.nestorfernandez.flipaswitch;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import java.util.Random;

/**
 * Created by nestor.fernandez on 08/02/2018.
 */

public class SpriteFire {
    private final int BMP_COLUMNS=8;
    private final int BMP_ROWS=4;
    private final GameView gameView;
    private final Bitmap bmp;
    private int xSpeed=-25;
    private int x;
    private int y;
    private int width;
    private int height;
    private int currentColumn=0;
    private int currentRow=0;
    private Rect position;

    public SpriteFire(GameView gameView, Bitmap bmp,Canvas canvas, int type) {
        this.gameView = gameView;
        this.bmp = bmp;
        this.width=bmp.getWidth()/BMP_COLUMNS;
        this.height=bmp.getHeight()/BMP_ROWS;
        x=canvas.getWidth();

        if (type==0){
            y=canvas.getHeight()-90;
        }else{
            y=90+height;
        }
        Log.i("etiqueta","y:"+y);
    }

    public void update(){
        //Ciclo las columnas del fuego
        if(currentColumn==(BMP_COLUMNS-1)){
            currentColumn=0;
        }else{
            currentColumn++;
        }
        //Ciclo las filas del fuego
        if(currentRow==(BMP_ROWS-1)){
            currentRow=0;
        }else{
            currentRow++;
        }
        x+=xSpeed;
    }

    public void onDraw(Canvas canvas){
        update();
//        y=ground.top+35;
        //El rectangulo empieza segun la posicion de la columna que se quiera coger
        int srcX=currentColumn*width;
        //El rectangulo empieza en la fila donde se situan los sprites de "avance hacia la derecha"
        int srcY=currentRow*height;
        //Cogemos "la casilla de sprite" que nos interesa pintar
        Rect src = new Rect(srcX,srcY,srcX+width,srcY+height);
        //Cogemos "la casilla" de las coordenadas del destino
        position=new Rect(x,y-height,x+width,y);
        canvas.drawBitmap(bmp,src,position,null);
    }

    public int getX() {
        return x;
    }

    public Rect getPosition() {
        return position;
    }
}
