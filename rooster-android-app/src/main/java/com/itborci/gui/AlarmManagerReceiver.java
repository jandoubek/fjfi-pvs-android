package com.itborci.gui;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

import com.itborci.R;

public class AlarmManagerReceiver extends BroadcastReceiver {

	final public static String ONE_TIME = "onetime";
	private String subject, message;
	private SharedPreferences sharedPreferences;
	 
	 @Override
	 public void onReceive(Context context, Intent intent) {
	   PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
	         PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "NOTIFICATION");
	         //Acquire the lock
	         wl.acquire();
	         
	         //You can do the processing here.
	         Bundle extras = intent.getExtras();
	         StringBuilder msgStr = new StringBuilder();
	          
	         if(extras != null && extras.getBoolean(ONE_TIME, Boolean.FALSE)){
	          //Make sure this intent has been sent by the one-time timer button.
	          msgStr.append("One time Timer : ");
	         }
	         Format formatter = new SimpleDateFormat("hh:mm:ss a");
	         msgStr.append(formatter.format(new Date()));
	 
//	         Toast.makeText(context, msgStr, Toast.LENGTH_LONG).show();
	         NotificationManager mNM = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
	         Notification notification = new Notification(R.drawable.notification_icon, "Rooster notification", System.currentTimeMillis());

//	         long[] vibrate = {0,100,200};
//	         notification.vibrate = vibrate;
	         
	         Intent myIntent = new Intent(context , MainActivity.class);     
	         PendingIntent contentIntent = PendingIntent.getBroadcast(context, 0, myIntent, 0);
	         
	         Log.i("NOTIFICATION", "vypis: " + subject + ", " + message);
	         if (subject == null || message == null) {
	        	 notification.setLatestEventInfo(context, "Numerika", "T108, Beneš", contentIntent);
	         } else {
	        	 notification.setLatestEventInfo(context, subject, message, contentIntent);
	         }
	         mNM.notify(AlarmManager.RTC_WAKEUP, notification);
	          
	         //Release the lock
	         wl.release();
	 }
	 
	public void setAlarm(Context context, String sub, String clas)
    {
        AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        subject = sub;
        message = clas;
        Log.i("NOTIFICATION",  "vypis_setAlarm: " + subject + ", " + message);
        
        Intent intent = new Intent(context, AlarmManagerReceiver.class);
        intent.putExtra(ONE_TIME, Boolean.FALSE);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        
       // am.setRepeating(, , 0 , pi);
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 20000, pi);
    }
	 
//    public void cancelAlarm(Context context)
//    {
//        Intent intent = new Intent(context, AlarmManagerReceiver.class);
//        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        alarmManager.cancel(sender);
//    }
// 
//    public void setOnetimeTimer(Context context){
//     AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(context, AlarmManagerReceiver.class);
//        intent.putExtra(ONE_TIME, Boolean.TRUE);
//        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
//        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi);
//    }

}
