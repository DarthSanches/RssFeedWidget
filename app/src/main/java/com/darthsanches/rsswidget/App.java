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

        Intent myIntent = new Intent(getBaseContext(), FeedService.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(),  0, myIntent, 0);


        AlarmManager alarmManager = (AlarmManager)getBaseContext().getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = null;
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 60); // first time
        long frequency= 60 * 1000; // in ms
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), frequency, pendingIntent);

        startService(myIntent);
    }
}
