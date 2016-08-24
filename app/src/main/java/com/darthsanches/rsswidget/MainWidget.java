package com.darthsanches.rsswidget;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import com.darthsanches.rsswidget.reader.RssReader;
import com.darthsanches.rsswidget.util.CommonStringsHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by alexandroid on 22.08.2016.
 */
public class MainWidget extends AppWidgetProvider {

    final static String ACTION_CHANGE = "ru.startandroid.develop.p1201clickwidget.change_count";
    final static String ACTION_OPEN_SETTINGS = "com.darthsanches.rsswidget.open_settings_action";

    private List<JSONObject> jobs = new ArrayList<JSONObject>();

    boolean isSettings;

/*    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgets) {
        Log.d(getClass().getName(), "refresh");

        try {
            //new FeedResponceTask(context).execute("");
            *//*CommonStringsHelper res = new CommonStringsHelper(context);
            jobs = RssReader.getLatestRssFeed(res);*//*
        } catch (Exception e) {
            Log.e("RSS ERROR", "Error loading RSS Feed Stream >> " + e.getMessage() + " //" + e.toString());
        }

        MainWidget.updateWidget(context, appWidgetManager, jobs);

    }*/

    public static void updateWidget(Context context, AppWidgetManager appWidgetManager, List<JSONObject> jobs) {

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.main_widget_screen);

        try {

            if(!jobs.isEmpty()) {

                Log.d("jsaon", jobs.get(0).toString());
                String title = (String) jobs.get(0).get("title");

                String text = (String) jobs.get(0).get("text");

                Log.d("updateWidget", title);

                Log.d("updateWidget", text);

                remoteViews.setTextViewText(R.id.title, title);

                remoteViews.setTextViewText(R.id.text, text);

                //Intent intent = new Intent(context, RssActivity.class);

                //PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

                //remoteViews.setOnClickPendingIntent(R.id.job_text, pendingIntent);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ComponentName rssWidget = new ComponentName(context, MainWidget.class);

        appWidgetManager.updateAppWidget(rssWidget, remoteViews);
    }

//    @Override
//    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
//        super.onUpdate(context, appWidgetManager, appWidgetIds);
//        int widgetID = appWidgetIds[0];
//        RemoteViews remoteViews;
//        if (isSettings) {
//            remoteViews = new RemoteViews(context.getPackageName(), R.layout.main_widget_screen);
//            Intent countIntent = new Intent(context, MainWidget.class);
//            countIntent.setAction(ACTION_CHANGE);
//            countIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
//            PendingIntent pIntent = PendingIntent.getBroadcast(context, widgetID, countIntent, 0);
//            remoteViews.setOnClickPendingIntent(R.id.button, pIntent);
//        } else {
//            remoteViews = new RemoteViews(context.getPackageName(), R.layout.main_widget_screen);
//            remoteViews.setTextViewText(R.id.title, "Bugaga!");
//            remoteViews.setTextViewText(R.id.text, "Bugagashenka!");
//
///*            Intent countIntent = new Intent(context, MainWidget.class);
//            countIntent.setAction(ACTION_CHANGE);
//            countIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
//            PendingIntent pIntent = PendingIntent.getBroadcast(context, widgetID, countIntent, 0);
//            remoteViews.setOnClickPendingIntent(R.id.button_next, pIntent);*/
//
//            Intent settingsIntent = new Intent(context, MainWidget.class);
//            settingsIntent.setAction(ACTION_OPEN_SETTINGS);
//            settingsIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
//            PendingIntent pIntent = PendingIntent.getBroadcast(context, widgetID, settingsIntent, 0);
//            remoteViews.setOnClickPendingIntent(R.id.setting_button, pIntent);
//        }
//        // Обновляем виджет
//        appWidgetManager.updateAppWidget(widgetID, remoteViews);
//    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (ACTION_OPEN_SETTINGS.equals(intent.getAction())) {
            isSettings = true;
//            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.main_widget_screen);
//            remoteViews.setInt(R.id.feed_screen, "visibility", View.GONE);
//            remoteViews.setInt(R.id.settings_screen, "visibility", View.VISIBLE);
//            AppWidgetManager.getInstance(context).updateAppWidget(
//                    new ComponentName(context, MainWidget.class), remoteViews);
        } else {
            isSettings = false;
        }
        Log.i("onReceive", "intent data" + intent.getAction());
    }

    public boolean isMyServiceRunning(Context ctx, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
