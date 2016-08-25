package com.darthsanches.rsswidget;

import android.app.IntentService;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.darthsanches.rsswidget.reader.RssReader;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandroid on 24.08.2016.
 */
public class FeedService extends IntentService {

    public FeedService() {
        super("updateService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        List<JSONObject> jobs = new ArrayList<JSONObject>();

        try {
            jobs = RssReader.getLatestRssFeed(getBaseContext());
        } catch (Exception e) {
            Log.e("RSS ERROR", "Error loading RSS Feed Stream >> " + e.getMessage() + " //" + e.toString());
        }
        Log.d(getClass().getName(), "download");

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getBaseContext());

        MainWidget.updateWidget(getBaseContext(), appWidgetManager, jobs);
    }
}
