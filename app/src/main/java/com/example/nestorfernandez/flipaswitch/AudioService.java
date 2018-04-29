package com.example.nestorfernandez.flipaswitch;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by nestor.fernandez on 14/03/2018.
 */

public class AudioService extends Service {



    private MediaPlayer audio ;

    /*public AudioService(Context context) {
    }*/

    @Override
    public void onCreate() {
        audio= MediaPlayer.create(this,R.raw.immigrant_song);

    }

    public class MyServiceBinder extends Binder {
        public AudioService getService(){
            return AudioService.this;
        }
    }
    private IBinder servicioBinder=new MyServiceBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("service2"," onBind");
        return servicioBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("service2","onStartCommand, thread id: "+Thread.currentThread().getId());
        new Thread(new Runnable() {
            @Override
            public void run() {
                startMusic();
            }
        }).start();
        return START_STICKY;
    }


    public void startMusic(){
        if(!audio.isPlaying()){
            audio.setLooping(true);
            audio.start();
        }
    }

    public void stopMusic(){
        audio.release();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        stopMusic();
    }
}
