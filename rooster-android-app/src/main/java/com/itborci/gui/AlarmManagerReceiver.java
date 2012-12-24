package com.itborci.gui;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;

import com.itborci.R;
import com.itborci.POJO.Subject;


public class AlarmManagerReceiver extends BroadcastReceiver {

	final public static String ONE_TIME = "onetime";
	private Subject subject;
	 
	 @Override
	 public void onReceive(Context context, Intent intent) {
	   PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
	         PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "NOTIFICATION");
	         //Acquire the lock
	         wl.acquire();
	         
	         AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
	         
	         Bundle extras = intent.getExtras();
	         
	         if (extras.containsKey("name")) {
	        	 String name = extras.getString("name");
	        	 String details = extras.getString("room") + ", " + extras.getString("teacher");
	         
	//	         Toast.makeText(context, msgStr, Toast.LENGTH_LONG).show();
		         NotificationManager mNM = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		         Notification notification = new Notification(R.drawable.notification_icon, "Rooster notification", System.currentTimeMillis());
	
	//	         long[] vibrate = {0,100,200};
	//	         notification.vibrate = vibrate;
		         
		         Intent myIntent = new Intent(context , AlarmManagerReceiver.class);     
		         PendingIntent contentIntent = PendingIntent.getBroadcast(context, 0, myIntent, 0);
		         
		         notification.setLatestEventInfo(context, name, details, contentIntent);
		         mNM.notify(AlarmManager.RTC_WAKEUP, notification);
		         am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		         setAlarm(context, null);
		         
	         } else {
	        	 am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
	        	 
	         }
	         
	         wl.release();
	 }
	 
	public void setAlarm(Context context, Subject subject)
    {
		AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, AlarmManagerReceiver.class);
        if (subject != null) {
        	this.subject = subject;
			Log.i("NOTIFICATION", "vypis_setAlarm: " + subject.getName() + ", "
					+ subject.getRoom() + ", " + subject.getTeacher() + ", "
					+ subject.getWeek() + ", " + subject.getDay() + ", "
					+ subject.getHour());
			intent.putExtra("name", subject.getName());
			intent.putExtra("teacher", subject.getTeacher());
			intent.putExtra("room", subject.getRoom());
			PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			Calendar firstTime = Calendar.getInstance();
			am.setRepeating(AlarmManager.RTC_WAKEUP,
					System.currentTimeMillis() + 10000, 604800000, pi); // timeunit uz nema DAYS a WEEKS konstanty, zatim natvrdo
			//        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 20000, pi);
		} else {
			PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 3600000, pi);
//			am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 10000, pi);
		}
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
