package com.lantar.testtask;

import android.app.Application;

/**
 * Created by LantarPc on 1/18/2018.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Db.getInstance();
    }

}
