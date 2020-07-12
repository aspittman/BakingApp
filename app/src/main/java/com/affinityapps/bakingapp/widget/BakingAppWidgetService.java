package com.affinityapps.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.affinityapps.bakingapp.R;

public class BakingAppWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {

        return new BakingWidgetItemFactory(getApplicationContext(), intent);
    }

    private class BakingWidgetItemFactory implements RemoteViewsFactory {
        private Context context;
        private int itemId;
        private String[] testData = {"Nutella Pie ", "2 Cups Graham Cracker crumbs", "6 TBLSB unsalted butter, melted", "0.5 Cup Granulated Sugar",
                "1.5 TSP salt", "5 TBLSB vanilla", "1 K Nutella or other chocolate spread", "500 G Mascapone Cheese(room temperature)", "1 Cup Heavy Cream (cold)", "4 oz Cream Cheese (softened)",
        "Brownies", "350 G Bittersweet chocolate (60-70% cacao)", "226 G Unsalted Butter", "300 G Granulated sugar", "100 G light brown sugar", "5 UNIT large eggs", "1 TBLSP vanilla extract",
                "140 G all purpose flour", "40 G coca powder", "1.5 TSP salt", "350 G semisweet chocolate chips", "Yellow Cake", "400 G sifted cake flour", "700 G granulated sugar", "4 TSP baking powder",
        "1.5 TSP salt", "2 TBLSP vanilla extract, divided", "8 UNIT egg yolks", "323 G Whole Milk", "961 G unsalted butter, softened and cut into 1 in. cubes", "6 UNIT egg whites",
        "283 G melted and cooled bittersweet or semisweet chocolate", "CheeseCake", "2 Cups Graham Cracker crumbs", "6 TBLSB unsalted butter, melted", "250 G Granulated Sugar", "1 TSP salt",
                "4 TSP vanilla, divided", "680 G cream cheese, softened", "3 UNIT large whole eggs", "2 unit large egg yolks", "250 G heavy cream"};

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
