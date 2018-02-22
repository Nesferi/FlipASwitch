package com.example.nestorfernandez.flipaswitch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nestor.fernandez on 26/01/2018.
 */
//Clase que controlará la vista del juego
public class GameView extends SurfaceView {

    private ArrayList<Bitmap> spriteList = new ArrayList<Bitmap>();
    private Bitmap bmp;
    private Bitmap bmpFire;
    private Bitmap backgr = BitmapFactory.decodeResource(getResources(),R.drawable.backimage);
    private Bitmap green = BitmapFactory.decodeResource(getResources(),R.drawable.green_texture);
    //Declaración de las variables necesarias
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private Sprite sprite;
    private SpriteFire spriteFire;
    private Sprite2 sprite2;

    private Bitmap bmpp;

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
        bmpFire= BitmapFactory.decodeResource(getResources(),R.drawable.fire2);
        spriteFire = new SpriteFire(this,bmpFire);

        bmpp= BitmapFactory.decodeResource(getResources(),R.drawable.ninja1);
        spriteList.add(bmpp);
        spriteList.add(BitmapFactory.decodeResource(getResources(),R.drawable.ninja2));
        spriteList.add(BitmapFactory.decodeResource(getResources(),R.drawable.ninja3));
        sprite2 = new Sprite2(this,spriteList);

    }

    @Override
    public void onDraw(Canvas canvas) {
        //Creamos un rectangulo con el tamaño de la pantalla
        // Rect ( left, top, right, bottom)
        Rect dst= new Rect(0,0,canvas.getWidth(),canvas.getHeight());
        //Pintamos el background backgr, adaptandolo al rectangulo dst
        canvas.drawBitmap(backgr,null,dst,null);

        //Lo mismo para el techo
        Rect cieling = new Rect(0,0,canvas.getWidth(),90);
        canvas.drawBitmap(green,null,cieling,null);

        //Creo el rectángulo que contendrá el espacio entre el suelo y el techo de la aplicación
        Rect game = new Rect(0,cieling.bottom,canvas.getWidth(),canvas.getHeight()-cieling.height());

        //Creo y pinto la linea que hará de suelo en el juego. Empieza donde acaba game y mide 25px
        Rect ground = new Rect(0,game.bottom,canvas.getWidth(),canvas.getHeight());
        canvas.drawBitmap(green,null,ground,null);



        //Llamamos al onDraw del sprite, pasandole el canvas y el rectangulo por donde se moverá
        sprite2.onDraw(canvas,game);
        spriteFire.onDraw(canvas,ground);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        sprite2.flipASwitch();
        return super.onTouchEvent(event);
    }
}
