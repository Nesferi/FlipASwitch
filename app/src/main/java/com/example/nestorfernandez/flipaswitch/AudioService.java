package com.example.nestorfernandez.flipaswitch;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by nestor.fernandez on 14/03/2018.
 */

public class AudioService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private MediaPlayer audio = MediaPlayer.create(this,R.raw.ostia_tio);

    public void startMusic(){
        if(!audio.isPlaying()){
            audio.setLooping(true);
            audio.start();
        }
    }

    public void stopMusic(){
        stopSelf();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (audio != null){
            audio.release();
        }
    }
}
