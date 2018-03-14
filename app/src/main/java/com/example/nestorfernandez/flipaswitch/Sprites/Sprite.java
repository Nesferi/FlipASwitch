package com.example.nestorfernandez.flipaswitch.Sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.example.nestorfernandez.flipaswitch.Game.GameView;

/**
 * Created by nestor.fernandez on 26/01/2018.
 */

public class Sprite {


    private final int BMP_COLUMNS=3;
    private final int BMP_ROWS=4;
    private int ySpeed=15;
    private int x=200;
    private int y=200;
    private int width;
    private int height;
    private int currentFrame=0;
    private boolean switched=false;


    private Bitmap bmp;
    private GameView gameView;

    public Sprite(GameView gameView, Bitmap bmp){
        this.gameView = gameView;
        this.bmp = bmp;
        this.width=bmp.getWidth()/BMP_COLUMNS;
        this.height=bmp.getHeight()/BMP_ROWS;
    }

    private void update(Rect game){
        y=y+ySpeed;
        //Calculamos la casilla de destino teórico
        Rect destino=new Rect(x,y,x+width,y+height);
        // Si se sale de los limites del rectángulo jugable,
        // hacemos que deje de moverse en el eje de las Y para que no llegue a salirse
        Log.i("etiqueta","ySpeed = "+ySpeed);

        if (!game.contains(destino)){
            ySpeed=0;
        }
        Log.i("etiqueta","ySpeed = "+ySpeed);

        //Al hacer el mod entre el numero de columnas, siempre rota los valores
        //Por ejemplo: 3 columnas va cogiendo 0,1,2,0,1,2,0,1,2...
        currentFrame=++currentFrame%BMP_COLUMNS;
    }

    public void onDraw(Canvas canvas, Rect game){
        update(game);
        //El rectangulo empieza segun la posicion de la columna que se quiera coger
        int srcX=currentFrame*width;
        //El rectangulo empieza en la fila donde se situan los sprites de "avance hacia la derecha"
        int srcY=2*height;
        //Cogemos "la casilla de sprite" que nos interesa pintar
        Rect src = new Rect(srcX,srcY,srcX+width,srcY+height);
        //Cogemos "la casilla" de las coordenadas del destino
        Rect dst=new Rect(x,y,x+width,y+height);
        canvas.drawBitmap(bmp,src,dst,null);
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
