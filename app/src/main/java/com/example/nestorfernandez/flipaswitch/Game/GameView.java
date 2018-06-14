package com.example.nestorfernandez.flipaswitch.Game;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nestorfernandez.flipaswitch.AudioService;
import com.example.nestorfernandez.flipaswitch.BDHelper;
import com.example.nestorfernandez.flipaswitch.R;
import com.example.nestorfernandez.flipaswitch.Sprites.Sprite;
import com.example.nestorfernandez.flipaswitch.Sprites.Sprite2;
import com.example.nestorfernandez.flipaswitch.Sprites.SpriteFire;
import com.example.nestorfernandez.flipaswitch.constant;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by nestor.fernandez on 26/01/2018.
 */
//Clase que controlará la vista del juego
public class GameView extends SurfaceView {

    private Bitmap bmpFire;
    private Bitmap backgr2;
    private int backframe = 0;
    private final int back_width = 1270;
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    Paint paint = new Paint();

    private ArrayList<SpriteFire> spritesFires = new ArrayList<>();
    private SpriteFire spriteFire;

    private ArrayList<ArrayList<Bitmap>> spriteList = new ArrayList<>();
    private ArrayList<Bitmap> spriteListRun = new ArrayList<>();
    private ArrayList<Bitmap> spriteListJump = new ArrayList<>();
    private ArrayList<Bitmap> spriteListFly = new ArrayList<>();
    private ArrayList<Bitmap> spriteListGround = new ArrayList<>();
    private Sprite2 sprite2;
    private Boolean isThisAllowed = true;
    public Context ctx = null;
    //Constructor. Recibe un context, genera el holder y define sus clases
    public GameView(Context context) {
        super(context);
        ctx = context;
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
                isThisAllowed = true;

            }
            //Cambio en la vista (?)
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }
            //Destruccion de la vista
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                //"Apagamos" el hilo de gameLoop
                gameLoopThread.setRunning(false);
            }
        });

        ninjaGenerate();
        sprite2 = new Sprite2(this,spriteList);
        backgr2 = BitmapFactory.decodeResource(getResources(),R.mipmap.backimage2);
        Log.i("etiqueta","backgr2. right: "+backgr2.getWidth()+" , Height: "+backgr2.getHeight());

        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(35);
        paint.setColor(Color.BLACK);


    }


    private void ninjaGenerate(){
        spriteListRun.add(BitmapFactory.decodeResource(getResources(),R.drawable.ninja1));
        spriteListRun.add(BitmapFactory.decodeResource(getResources(),R.drawable.ninja2));
        spriteListRun.add(BitmapFactory.decodeResource(getResources(),R.drawable.ninja3));
        spriteListRun.add(BitmapFactory.decodeResource(getResources(),R.drawable.ninja4));
        spriteListRun.add(BitmapFactory.decodeResource(getResources(),R.drawable.ninja5));
        spriteListRun.add(BitmapFactory.decodeResource(getResources(),R.drawable.ninja6));
        spriteListRun.add(BitmapFactory.decodeResource(getResources(),R.drawable.ninja7));
        spriteListRun.add(BitmapFactory.decodeResource(getResources(),R.drawable.ninja8));
        spriteList.add(spriteListRun);
        spriteListJump.add(BitmapFactory.decodeResource(getResources(),R.drawable.ninja18));
        spriteListJump.add(BitmapFactory.decodeResource(getResources(),R.drawable.ninja19));
        spriteListJump.add(BitmapFactory.decodeResource(getResources(),R.drawable.ninja18));
        spriteListJump.add(BitmapFactory.decodeResource(getResources(),R.drawable.ninja19));
        spriteListJump.add(BitmapFactory.decodeResource(getResources(),R.drawable.ninja18));
        spriteListJump.add(BitmapFactory.decodeResource(getResources(),R.drawable.ninja19));
        spriteListJump.add(BitmapFactory.decodeResource(getResources(),R.drawable.ninja18));
        spriteListJump.add(BitmapFactory.decodeResource(getResources(),R.drawable.ninja19));
        spriteList.add(spriteListJump);
        spriteListFly.add(BitmapFactory.decodeResource(getResources(),R.drawable.ninja20));
        spriteListFly.add(BitmapFactory.decodeResource(getResources(),R.drawable.ninja21));
        spriteListFly.add(BitmapFactory.decodeResource(getResources(),R.drawable.ninja22));
        spriteListFly.add(BitmapFactory.decodeResource(getResources(),R.drawable.ninja23));
        spriteListFly.add(BitmapFactory.decodeResource(getResources(),R.drawable.ninja24));
        spriteListFly.add(BitmapFactory.decodeResource(getResources(),R.drawable.ninja25));
        spriteListFly.add(BitmapFactory.decodeResource(getResources(),R.drawable.ninja26));
        spriteListFly.add(BitmapFactory.decodeResource(getResources(),R.drawable.ninja27));
        spriteListFly.add(BitmapFactory.decodeResource(getResources(),R.drawable.ninja28));
        spriteListFly.add(BitmapFactory.decodeResource(getResources(),R.drawable.ninja29));

        spriteList.add(spriteListFly);
    }

    @Override
    public void onDraw(Canvas canvas) {
        if(canvas!=null){

        //Creamos un rectangulo con el tamaño de la pantalla
        Rect dst= new Rect(0,0,canvas.getWidth(),canvas.getHeight());

        //Cogemos el frame de background que queramos
        int left = (int) (backframe*0.02*back_width);
        int right =  left+back_width;
        Rect background = new Rect(left,0,right,backgr2.getHeight());
        //Pintamos el background backgr, adaptandolo al rectangulo dst
        canvas.drawBitmap(backgr2,background /*background*/,dst,null);


        //Creo el rectángulo que contendrá el espacio entre el suelo y el techo de la aplicación
        Rect game = new Rect(0, constant.getCieling(),constant.getMobile_width(),constant.getGround());


        backframe=++backframe%50;

        //Llamamos al onDraw del sprite, pasandole el canvas y el rectangulo por donde se moverá
        sprite2.onDraw(canvas,game);
        for (int i=0;i<spritesFires.size();i++){
            spritesFires.get(i).onDraw(canvas);
            if(spritesFires.get(i).getX()<=0){
                fireDestroyer();
                i--;
            }
        }

        canvas.drawText(constant.getPoints()+"Pts", constant.getMobile_width()/2, 50, paint);
        if(isCollition()){
            if(isThisAllowed){
                isThisAllowed=false;
                Activity activity = (Activity) getContext();
                activity.finish();
            }
        }
        }

    }

    private boolean isCollition() {
        Rect player = sprite2.getPosition();
        for (int i = 0; i < spritesFires.size(); i++) {
            Rect fire = spritesFires.get(i).getPosition();
            if(Rect.intersects(player,fire)){
                return true;
            }
        }
        return false;
    }


    public void fireGenerate(Canvas canvas) {
        Random rand = new Random();
        int n = rand.nextInt(100);
        if(n<5){
            int type = rand.nextInt(2);
            if(type==0){
                bmpFire= BitmapFactory.decodeResource(getResources(),R.drawable.fire2);
                spriteFire = new SpriteFire(this,bmpFire,canvas,0);
                spritesFires.add(spriteFire);
                Log.i("etiqueta","fire generated");
            }else{
                bmpFire = BitmapFactory.decodeResource(getResources(),R.drawable.flyguy2);
                spriteFire = new SpriteFire(this,bmpFire,canvas,1);
                spritesFires.add(spriteFire);
                Log.i("etiqueta","flyguy generated");

            }

        }
    }

    public void fireDestroyer(){
        spritesFires.remove(0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        sprite2.flipASwitch();
        return super.onTouchEvent(event);
    }


}
