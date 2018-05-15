package gunderson.wgu.org.wguapp;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import static gunderson.wgu.org.wguapp.DatabaseHelper.TERM_START;

//!!!UPDATE MANIFEST

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        createNotification(context);
    }

    private void createNotification(Context context) { //String msgTitle, String msgTicker, String msgText, String msgInfo){

        //return to MainLanding
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainLanding.class), 0);

        //Build the user notification
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.owl)
//                .setTicker("Alert From WGU")
                .setContentTitle("Alert From WGU")
                .setContentText("Important Date Event")
                .setContentInfo("Please check the scheduler for an important date event.")
                .setDefaults(Notification.DEFAULT_ALL);

        //the intent to run when clicked and cancel notification on click
        notificationBuilder.setContentIntent(pendingIntent);
        notificationBuilder.setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());

    }


}
