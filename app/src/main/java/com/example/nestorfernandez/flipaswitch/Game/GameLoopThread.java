package com.example.nestorfernandez.flipaswitch.Game;

import android.graphics.Canvas;

import com.example.nestorfernandez.flipaswitch.constant;

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
        long FPS = 60;
        long tiksPS=1000/FPS;
        long startTime;
        long sleepTime;
        constant.setPoints(0);
        while(running){
            Canvas c = null;
            startTime= System.currentTimeMillis();
            try{
                c=view.getHolder().lockCanvas();
                if(constant.getMobile_width()==-1){
                    constant.setMobile_height(c.getHeight());
                    constant.setMobile_width(c.getWidth());
                    constant.setCieling(100);
                    constant.setGround(c.getHeight()-100);
                }

                //Llamamos mediante un metodo sincronizado al draw del gameView
                synchronized (view.getHolder()){
                    view.onDraw(c);
                    view.fireGenerate(c);
                    constant.incrementPoints();
                }
            }finally{
                if(c!=null){
                    //Terminamos de pintar el Canvas y lo actualizamos
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }
            sleepTime=tiksPS-(System.currentTimeMillis()-startTime);
            try {
                if (sleepTime > 10)
                    sleep(sleepTime);
                else
                    sleep(10);
            }catch (Exception e){}
        }
    }

}
