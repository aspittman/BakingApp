package com.affinityapps.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.affinityapps.bakingapp.R;

class BakingAppWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {

        return new BakingWidgetItemFactory(getApplicationContext(), intent);
    }

    class BakingWidgetItemFactory implements RemoteViewsFactory {
        private Context context;
        private int itemId;
        private String[] testData = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};

        BakingWidgetItemFactory(Context context, Intent intent) {
            this.context = context;
            this.itemId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        @Override
        public void onCreate() {
            //connect to volley or data source
        }

        @Override
        public void onDataSetChanged() {
            //used for updating widget
        }

        @Override
        public void onDestroy() {
            //close connection to data source
        }

        @Override
        public int getCount() {
            return testData.length;
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.baking_widget_item);
            remoteViews.setTextViewText(R.id.baking_widget_item_text, testData[position]);
            return remoteViews;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
