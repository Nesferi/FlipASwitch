package com.example.nestorfernandez.flipaswitch.Sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.example.nestorfernandez.flipaswitch.Game.GameView;

/**
 * Created by nestor.fernandez on 08/02/2018.
 */

public class SpriteCell {


    private final int BMP_COLUMNS=3;
    private final int BMP_ROWS=4;
    private int xSpeed=10;
    private int ySpeed=15;
    private int x=0;
    private int y=200;
    private int width;
    private int height;
    private int currentFrame=0;
    private boolean switched=false;


    private Bitmap bmp;
    private GameView gameView;

    public SpriteCell(GameView gameView, Bitmap bmp){
        this.gameView = gameView;
        this.bmp = bmp;
        this.width=bmp.getWidth()/BMP_COLUMNS;
        this.height=bmp.getHeight()/BMP_ROWS;
    }

    private void update(Rect game){
        //Si llega a alguno de los margenes, media vuelta
        if(x>gameView.getWidth()-width-xSpeed || x +xSpeed<0){
            xSpeed=-xSpeed;
        }
        x=x+xSpeed;
        y=y+ySpeed;
        //Calculamos la casilla de destino teórico
        Rect destino=new Rect(x,y,x+width,y+height);
        // Si se sale de los limites del rectángulo jugable,
        // hacemos que deje de moverse en el eje de las Y para que llegue a salirse
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

    public void flipASwitch(){
        Log.i("etiqueta","flipASwitch()");
        if(!switched){
            ySpeed=-15;
            switched=true;
        }else{
            ySpeed=15;
            switched=false;
        }
    }

}

