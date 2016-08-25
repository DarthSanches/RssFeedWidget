package com.darthsanches.rsswidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by alexandroid on 22.08.2016.
 */
public class MainWidget extends AppWidgetProvider {

    final static String ACTION_PREV = "com.darthsanches.rsswidget.prev_action";
    final static String ACTION_NEXT = "com.darthsanches.rsswidget.next_action";
    public final static String ACTION_UPDATE = "com.darthsanches.rsswidget.update_action";
    public final static String CATEGORY = "your.package.CATEGORY_BACKGROUND";

    private static List<JSONObject> jobs;

    static int page;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgets) {
        Log.d(getClass().getName(), "refresh");

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.main_widget_screen);

        Intent intent = new Intent(context, SettingsActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        remoteViews.setOnClickPendingIntent(R.id.setting_button, pendingIntent);
        ComponentName rssWidget = new ComponentName(context, MainWidget.class);

        Intent prevIntent = new Intent(context, MainWidget.class);
        prevIntent.setAction(ACTION_PREV);
        prevIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgets[0]);
        PendingIntent pPrevIntent = PendingIntent.getBroadcast(context, appWidgets[0], prevIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.button_prev, pPrevIntent);


        Intent nextIntent = new Intent(context, MainWidget.class);
        nextIntent.setAction(ACTION_NEXT);
        nextIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgets[0]);
        PendingIntent pNextIntent = PendingIntent.getBroadcast(context, appWidgets[0], nextIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.button_next, pNextIntent);
        appWidgetManager.updateAppWidget(rssWidget, remoteViews);
    }

    public static void updateWidget(Context context, AppWidgetManager appWidgetManager, List<JSONObject> jobs) {

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.main_widget_screen);

        try {

            if (jobs != null && !jobs.isEmpty()) {
                MainWidget.jobs = jobs;
                Log.d("jsaon", jobs.get(page).toString());
                String title = (String) jobs.get(page).get("title");

                String text = (String) jobs.get(page).get("text");

                Log.d("updateWidget", title);

                Log.d("updateWidget", text);

                remoteViews.setTextViewText(R.id.title, title);

                remoteViews.setTextViewText(R.id.text, text);

                //Intent intent = new Intent(context, SettingsActivity.class);

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
        if (ACTION_PREV.equals(intent.getAction())) {
            if (page != 0) {
                page--;
            }
        } else if(ACTION_NEXT.equals(intent.getAction())){
            if (jobs != null && page < jobs.size() - 1) {
                page++;
            }
        }
        updateWidget(context, AppWidgetManager.getInstance(context), jobs);
        Log.d("onReceive", intent.getAction());
    }

}
