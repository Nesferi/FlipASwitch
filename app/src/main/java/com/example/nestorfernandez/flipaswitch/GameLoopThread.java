package com.example.nestorfernandez.flipaswitch;

import android.graphics.Canvas;

/**
 * Created by nestor.fernandez on 26/01/2018.
 */
//Hilo de continua ejecución
public class GameLoopThread extends Thread {
    //Creacion de variables
    private GameView view;
    private boolean running = false;
    //Constructor. Recibe una vista
    public GameLoopThread(GameView view){
        this.view = view;
    }
    //Activa o desactiva la ejecución del hilo
    public void setRunning(boolean run){
        this.running=run;
    }

    //Ejecucion del hilo
    @Override
    public void run() {
        //Comprobacion de que está activado
        long tiksPS=100;
        long startTime;
        long sleepTime;
        while(running){
            Canvas c = null;
            startTime= System.currentTimeMillis();
            try{
                c=view.getHolder().lockCanvas();
                //Llamamos mediante un metodo sincronizado al draw del gameView
                synchronized (view.getHolder()){
                    view.onDraw(c);
                    view.fireGenerate(c);
                }
            }finally{
                if(c!=null){
                    //Terminamos de pintar el Canvas y lo actualizamos
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }
            sleepTime=tiksPS-(System.currentTimeMillis()-startTime);
            try {
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(10);
            }catch (Exception e){}
        }
    }

}
