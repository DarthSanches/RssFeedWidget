package com.darthsanches.rsswidget;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by alexandroid on 24.08.2016.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(getClass().getName(), "create");
    }
}
