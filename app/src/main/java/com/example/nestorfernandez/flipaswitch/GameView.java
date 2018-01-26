package com.example.nestorfernandez.flipaswitch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by nestor.fernandez on 26/01/2018.
 */
//Clase que controlará la vista del juego
public class GameView extends SurfaceView {

    private Bitmap bmp;
    //Declaración de las variables necesarias
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private Sprite sprite;


    //Constructor. Recibe un context, genera el holder y define sus clases
    public GameView(Context context) {
        super(context);
        gameLoopThread=new GameLoopThread(this);
        holder=getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            //Creacion de la vista?
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                //"Encendemos" el hilo de gameLoop
                gameLoopThread.setRunning(true);
                //Arrancamos el metodo run
                gameLoopThread.start();

            }
            //Cambio en la vista?
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }
            //Destruccion de la vista?
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                //"Apagamos" el hilo de gameLoop
                gameLoopThread.setRunning(false);
            }
        });
        bmp= BitmapFactory.decodeResource(getResources(),R.drawable.good);
        sprite = new Sprite(this,bmp);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        sprite.onDraw(canvas);
    }
}
