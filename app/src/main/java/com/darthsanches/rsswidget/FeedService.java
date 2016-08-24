package com.darthsanches.rsswidget;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by alexandroid on 24.08.2016.
 */
public class FeedService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(getClass().getName(), "update");
        new FeedResponceTask(getBaseContext()).execute();
        return super.onStartCommand(intent, flags, startId);
    }
}
