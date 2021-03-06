package gunderson.wgu.org.wguapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Notification set", Toast.LENGTH_SHORT).show();
        createNotification(context);
    }

    private void createNotification(Context context) {
        PendingIntent mIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainLanding.class), 0);

        //Build the notification
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.owl)
                .setContentTitle("Alert From WGU")
                .setContentText("Important Date Event")
                .setContentInfo("Please check the scheduler for an important date event.")
                .setDefaults(Notification.DEFAULT_ALL);

        //the intent to run when clicked and cancel notification on click
        mBuilder.setContentIntent(mIntent);
        mBuilder.setAutoCancel(true);

        NotificationManager mManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        mManager.notify(1, mBuilder.build());

    }
}
