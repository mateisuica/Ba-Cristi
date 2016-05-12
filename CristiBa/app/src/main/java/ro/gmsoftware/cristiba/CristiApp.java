package ro.gmsoftware.cristiba;

import android.app.Application;
import android.util.Log;

import com.backendless.Backendless;
import com.backendless.DeviceRegistration;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

/**
 * Created by Matei on 4/15/2016.
 */
public class CristiApp extends Application {

    public static String token;
    @Override
    public void onCreate() {
        super.onCreate();
        Backendless.initApp(this, "F947A164-9623-9DDF-FFA0-78FCB43E7800", "68BB6030-56BA-4B1A-FFD6-F454EAF17E00", "v1");
        Backendless.Messaging.registerDevice("385203128016");
        Backendless.Messaging.getDeviceRegistration(new AsyncCallback<DeviceRegistration>() {
            @Override
            public void handleResponse(DeviceRegistration response) {
                token = response.getDeviceId();
                Log.d("TOKEN", token);
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }
}
