package ac.blitz.demo;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import java.util.Calendar;
import java.util.Locale;

public class ExampleAppWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
            int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

            // Create an Intent to launch ExampleActivity
            /*Intent intent = new Intent(context, ExampleActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
*/
            // Get the layout for the App Widget and attach an on-click listener
            // to the button
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.example_appwidget);
//            views.setOnClickPendingIntent(R.id.button, pendingIntent);
            Calendar cal = Calendar.getInstance(Locale.CHINA);
            int now = cal.get(Calendar.DAY_OF_YEAR);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int left = 0;
            if(day >= 7 && day <= 9) {
                NotificationManager  nm = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                Intent intent = new Intent(context, ExampleAppWidgetProvider.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse("custom://"+System.currentTimeMillis()));    // 实现了Intent的区别化
                //PendingIntent
                PendingIntent contentIntent = PendingIntent.getActivity(
                        context,R.string.app_name,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setSmallIcon(R.drawable.icon_class).setWhen(System.currentTimeMillis()).setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_LIGHTS|Notification.DEFAULT_VIBRATE)
                        .setAutoCancel(true).setContentTitle("wangxuan").setContentText("你马上要还贷款拉~~");
                nm.notify((int)System.currentTimeMillis(), builder.build());
            }
            String text = "距离还贷款还有";
            if(day >= 1 && day <= 9) {
                text += ""+(9-day)+"天";
            } else {
                int v=0;
                while(day != 9) {
                    cal.add(Calendar.DAY_OF_YEAR, 1);
                    day = cal.get(Calendar.DAY_OF_MONTH);
                    v++;
                }
                text += ""+v+"天";
            }

            views.setTextViewText(R.id.laji, text);

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

}
