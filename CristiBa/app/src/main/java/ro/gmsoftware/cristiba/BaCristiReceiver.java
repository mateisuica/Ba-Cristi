package ro.gmsoftware.cristiba;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;

import com.backendless.push.BackendlessBroadcastReceiver;

/**
 * Created by Matei on 5/12/2016.
 */
public class BaCristiReceiver extends BackendlessBroadcastReceiver {

    @Override
    public boolean onMessage(Context context, Intent intent) {

        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.ba_cristi);
        mediaPlayer.start();

        return true;
    }
}
