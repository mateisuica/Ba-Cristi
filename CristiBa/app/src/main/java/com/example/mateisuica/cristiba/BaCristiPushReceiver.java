package com.example.mateisuica.cristiba;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;

import com.backendless.push.BackendlessBroadcastReceiver;

/**
 * Created by matei.suica on 4/15/2016.
 */
public class BaCristiPushReceiver extends BackendlessBroadcastReceiver {

    @Override
    public boolean onMessage(Context context, Intent intent) {

        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.ba_cristi);
        mediaPlayer.start();
        return super.onMessage(context, intent);
    }
}
