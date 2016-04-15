package com.example.mateisuica.cristiba;

import android.app.Application;

import com.backendless.Backendless;

/**
 * Created by Matei on 4/15/2016.
 */
public class CristiApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Backendless.initApp(this, "F947A164-9623-9DDF-FFA0-78FCB43E7800", "68BB6030-56BA-4B1A-FFD6-F454EAF17E00", "v1");
    }
}
