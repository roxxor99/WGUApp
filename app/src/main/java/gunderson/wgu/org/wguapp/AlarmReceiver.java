package gunderson.wgu.org.wguapp;

import android.app.Notification;
import android.app.NotificationManager;
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


//public class AlarmReceiver extends BroadcastReceiver{
//    @Override
//    public void onRecieve (Context context, Intent intent){
//        //get termStart from db & assign to variable to be compared to localdate?
////        String courseStart = row.getString(row.getColumnIndexOrThrow(TERM_START));
////        LocalDate ld = LocalDate.now();
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
//
//        builder.setAutoCancel(true)
//                .setDefaults(Notification.DEFAULT_ALL)
//
//                //not sure what this will end being yet:
//                    // 1- create variables for LocalDate and TERM_START and compare?
////                .setWhen(TermModel.getTermStart == CurrentDate))
//                .setSmallIcon(R.drawable.owl)
//                .setContentTitle("Alert From WGU")
//                .setContentText("Important Date Event")
//                .setContentInfo("Please check the scheduler for an important date event.");
//
//        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(1,builder.build());
//
//    }

//}
