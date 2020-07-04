package com.affinityapps.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import com.affinityapps.bakingapp.R;
import com.affinityapps.bakingapp.RecipeCard;
import com.affinityapps.bakingapp.ingredients.IngredientsListFragment;
import com.affinityapps.bakingapp.recipes.MainActivity;
import com.affinityapps.bakingapp.recipes.RecipeCardFragment;

class BakingAppWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.baking_widget);
            remoteViews.setOnClickPendingIntent(R.id.baking_widget_ingredients_view, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}
