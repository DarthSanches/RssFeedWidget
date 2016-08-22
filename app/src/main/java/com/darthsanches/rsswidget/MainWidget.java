package com.darthsanches.rsswidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Created by alexandroid on 22.08.2016.
 */
public class MainWidget extends AppWidgetProvider {

    final static String ACTION_CHANGE = "ru.startandroid.develop.p1201clickwidget.change_count";
    final static String ACTION_OPEN_SETTINGS = "com.darthsanches.rsswidget.open_settings_action";

    boolean isSettings;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        int widgetID = appWidgetIds[0];
        RemoteViews remoteViews;
        if (isSettings) {
            remoteViews = new RemoteViews(context.getPackageName(), R.layout.settings_screen);
            Intent countIntent = new Intent(context, MainWidget.class);
            countIntent.setAction(ACTION_CHANGE);
            countIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
            PendingIntent pIntent = PendingIntent.getBroadcast(context, widgetID, countIntent, 0);
            remoteViews.setOnClickPendingIntent(R.id.button, pIntent);
        } else {
            remoteViews = new RemoteViews(context.getPackageName(), R.layout.main_widget_screen);
            remoteViews.setTextViewText(R.id.title, "Bugaga!");
            remoteViews.setTextViewText(R.id.text, "Bugagashenka!");

/*            Intent countIntent = new Intent(context, MainWidget.class);
            countIntent.setAction(ACTION_CHANGE);
            countIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
            PendingIntent pIntent = PendingIntent.getBroadcast(context, widgetID, countIntent, 0);
            remoteViews.setOnClickPendingIntent(R.id.button_next, pIntent);*/

            Intent settingsIntent = new Intent(context, MainWidget.class);
            settingsIntent.setAction(ACTION_OPEN_SETTINGS);
            settingsIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
            PendingIntent pIntent = PendingIntent.getBroadcast(context, widgetID, settingsIntent, 0);
            remoteViews.setOnClickPendingIntent(R.id.setting_button, pIntent);
        }
        // Обновляем виджет
        appWidgetManager.updateAppWidget(widgetID, remoteViews);
    }

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
        if(ACTION_OPEN_SETTINGS.equals(intent.getAction())){
            isSettings = true;
        }else {
            isSettings = false;
        }
        Log.i("onReceive", "intent data" + intent.getAction());
    }
}
