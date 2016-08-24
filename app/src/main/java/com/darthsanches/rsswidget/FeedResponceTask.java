package com.darthsanches.rsswidget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.darthsanches.rsswidget.reader.RssReader;
import com.darthsanches.rsswidget.util.CommonStringsHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandroid on 23.08.2016.
 */
public class FeedResponceTask extends AsyncTask<String, Integer, List<JSONObject>> {

    private Context ctx;

    public FeedResponceTask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(List<JSONObject> jobs) {

    }

    @Override
    protected List<JSONObject> doInBackground(String... arg0) {
        List<JSONObject> jobs = new ArrayList<JSONObject>();

        try {
            CommonStringsHelper res = new CommonStringsHelper(ctx.getResources());
            jobs = RssReader.getLatestRssFeed(res);
        } catch (Exception e) {
            Log.e("RSS ERROR", "Error loading RSS Feed Stream >> " + e.getMessage() + " //" + e.toString());
        }
        Log.d(getClass().getName(), "download");

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(ctx);

        MainWidget.updateWidget(ctx, appWidgetManager, jobs);

        return jobs;
    }
}

