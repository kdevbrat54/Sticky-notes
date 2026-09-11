package com.example.stickynoteswidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class StickyWidgetProvider extends AppWidgetProvider {

    public static final String ACTION_UPDATE =
        "com.example.stickynoteswidget.UPDATE";

    @Override
    public void onUpdate(
        Context context,
        AppWidgetManager manager,
        int[] ids) {

        for (int id : ids) {
            updateWidget(context, manager, id);
        }
    }

    @Override
    public void onReceive(
        Context context,
        Intent intent) {

        super.onReceive(context, intent);

        if (ACTION_UPDATE.equals(intent.getAction())) {

            AppWidgetManager manager =
                AppWidgetManager.getInstance(context);

            int[] ids = manager.getAppWidgetIds(
                new ComponentName(
                    context,
                    StickyWidgetProvider.class
                )
            );

            for (int id : ids) {
                updateWidget(context, manager, id);
            }
        }
    }

    private void updateWidget(
        Context context,
        AppWidgetManager manager,
        int id) {

        RemoteViews views = new RemoteViews(
            context.getPackageName(),
            R.layout.sticky_widget
        );

        String title =
            context.getSharedPreferences(
                "notes",
                Context.MODE_PRIVATE
            ).getString("title", "My Note");

        String text =
            context.getSharedPreferences(
                "notes",
                Context.MODE_PRIVATE
            ).getString(
                "text",
                "Tap + to add a note"
            );

        views.setTextViewText(
            R.id.widget_title,
            title
        );

        views.setTextViewText(
            R.id.widget_text,
            text
        );

        Intent addIntent =
            new Intent(
                context,
                AddNoteActivity.class
            );

        PendingIntent pendingIntent =
            PendingIntent.getActivity(
                context,
                1,
                addIntent,
                PendingIntent.FLAG_UPDATE_CURRENT |
                PendingIntent.FLAG_IMMUTABLE
            );

        views.setOnClickPendingIntent(
            R.id.widget_add,
            pendingIntent
        );

        views.setOnClickPendingIntent(
            R.id.widget_text,
            pendingIntent
        );

        manager.updateAppWidget(id, views);
    }
}
