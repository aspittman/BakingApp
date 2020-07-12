package com.affinityapps.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.Toast;
import com.affinityapps.bakingapp.R;
import com.affinityapps.bakingapp.RecipeCard;
import com.affinityapps.bakingapp.ingredients.IngredientsListFragment;
import com.affinityapps.bakingapp.recipes.MainActivity;
import com.affinityapps.bakingapp.recipes.RecipeCardFragment;

public class BakingAppWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            Intent serviceIntent = new Intent(context, BakingAppWidgetService.class);
            serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.baking_widget);
            remoteViews.setOnClickPendingIntent(R.id.baking_widget_ingredients_view, pendingIntent);
            remoteViews.setRemoteAdapter(R.id.baking_widget_stack_view, serviceIntent);
            remoteViews.setEmptyView(R.id.baking_widget_stack_view, R.id.baking_widget_ingredients_view);
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.baking_widget);

        int minWidth = newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH);
        int maxWidth = newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_WIDTH);
        int minHeight = newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT);
        int maxHeight = newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_HEIGHT);

        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);

        String dimensions = "minWidth" + minWidth +"\nmaxWidth" + maxWidth + "\nminHeight" + minHeight + "\nmaxHeight" + maxHeight;
        Toast.makeText(context, dimensions, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        Toast.makeText(context, "on Delete", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEnabled(Context context) {
        Toast.makeText(context, "on Enabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisabled(Context context) {
        Toast.makeText(context, "on Disabled", Toast.LENGTH_SHORT).show();
    }
}
