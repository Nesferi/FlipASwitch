package com.example.nestorfernandez.flipaswitch;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by nestor.fernandez on 08/02/2018.
 */

public class SpriteFire {
    private final int BMP_COLUMNS=8;
    private final int BMP_ROWS=4;
    private final GameView gameView;
    private final Bitmap bmp;
    private int xSpeed=-10;
    private int x=1000;
    private int y;
    private int width;
    private int height;
    private int currentColumn=0;
    private int currentRow=0;

    public SpriteFire(GameView gameView, Bitmap bmp) {
        this.gameView = gameView;
        this.bmp = bmp;
        this.width=bmp.getWidth()/BMP_COLUMNS;
        this.height=bmp.getHeight()/BMP_ROWS;
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

    public void onDraw(Canvas canvas, Rect ground){
        update();
        y=ground.top;
        //El rectangulo empieza segun la posicion de la columna que se quiera coger
        int srcX=currentColumn*width;
        //El rectangulo empieza en la fila donde se situan los sprites de "avance hacia la derecha"
        int srcY=currentRow*height;
        //Cogemos "la casilla de sprite" que nos interesa pintar
        Rect src = new Rect(srcX,srcY,srcX+width,srcY+height);
        //Cogemos "la casilla" de las coordenadas del destino
        Rect dst=new Rect(x,y-height,x+width,y+35);
        canvas.drawBitmap(bmp,src,dst,null);
    }


}
