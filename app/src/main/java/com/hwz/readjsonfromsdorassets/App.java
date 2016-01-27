package com.hwz.readjsonfromsdorassets;

import android.app.Application;

/**
 * Created by nico on 16/1/27.
 */
public class App extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        TabEngine.init(this);
    }
}
