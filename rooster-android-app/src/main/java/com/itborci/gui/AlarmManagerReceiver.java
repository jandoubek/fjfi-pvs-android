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

	private static final int lengthOfSubject = 6000000; 
	private static final int lengthOfWeek = 604800000;
//	private static final int lengthOfSubject = 15000; 
//	private static final int lengthOfWeek = 25000;
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
		         Notification notification = new Notification(R.drawable.notification_icon, "Rooster: silent mode activated", System.currentTimeMillis());
	
	//	         long[] vibrate = {0,100,200};
	//	         notification.vibrate = vibrate;
		         
		         Intent myIntent = new Intent(context , AlarmManagerReceiver.class);     
		         PendingIntent contentIntent = PendingIntent.getBroadcast(context, 2, myIntent, 0);
		         
		         notification.setLatestEventInfo(context, name, details, contentIntent);
		         mNM.notify(AlarmManager.RTC_WAKEUP, notification);
		         am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		         setAlarm(context, null);
		         
	         } else {
	        	 am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
	        	 Log.i("NOTIFICATION", "I turn off silent mode " + System.currentTimeMillis());
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
			firstTime = setNotificationTime(subject);
			am.setRepeating(AlarmManager.RTC_WAKEUP, firstTime.getTimeInMillis(), lengthOfWeek, pi); // timeunit uz nema DAYS a WEEKS konstanty, zatim natvrdo
			//        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 20000, pi);
		} else {
			PendingIntent pi = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + lengthOfSubject, pi);
//			am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 20000, pi);
			Log.i("NOTIFICATION", "I set 20s from now" + System.currentTimeMillis());
		}
    }

	private Calendar setNotificationTime(Subject subject) {
		int time, day;
		
		time = subject.getHour();
		day = subject.getDay();
		
		Calendar calendar = Calendar.getInstance();
//		calender.
		Log.i("NOTIFICATION", "calendar: " + calendar.toString());
		
		switch (day) {
		case 0:
			calendar.set(Calendar.DAY_OF_WEEK, 2);
			break;
		case 1:
			calendar.set(Calendar.DAY_OF_WEEK, 3);
			break;
		case 2:
			calendar.set(Calendar.DAY_OF_WEEK, 4);
			break;
		case 3:
			calendar.set(Calendar.DAY_OF_WEEK, 5);
			break;
		case 4:
			calendar.set(Calendar.DAY_OF_WEEK, 6);
			break;

		default:
			break;
		}
		
		switch (time) {
		case 0:
			calendar.set(Calendar.HOUR_OF_DAY, 7);
			calendar.set(Calendar.MINUTE, 30);
			calendar.set(Calendar.SECOND, 0);
			break;
		case 1:
			calendar.set(Calendar.HOUR_OF_DAY, 9);
			calendar.set(Calendar.MINUTE, 30);
			calendar.set(Calendar.SECOND, 0);
			break;
		case 2:
			calendar.set(Calendar.HOUR_OF_DAY, 11);
			calendar.set(Calendar.MINUTE, 30);
			calendar.set(Calendar.SECOND, 0);
			break;
		case 3:
			calendar.set(Calendar.HOUR_OF_DAY, 13);
			calendar.set(Calendar.MINUTE, 30);
			calendar.set(Calendar.SECOND, 0);
			break;
		case 4:
			calendar.set(Calendar.HOUR_OF_DAY, 15);
			calendar.set(Calendar.MINUTE, 30);
			calendar.set(Calendar.SECOND, 0);
			break;
		case 5:
			calendar.set(Calendar.HOUR_OF_DAY, 17);
			calendar.set(Calendar.MINUTE, 30);
			calendar.set(Calendar.SECOND, 0);
			break;
		case 6:
			calendar.set(Calendar.HOUR_OF_DAY, 19);
			calendar.set(Calendar.MINUTE, 30);
			calendar.set(Calendar.SECOND, 0);
			break;

		default:
			break;
		}
		
		calendar = setRightTime(calendar);
		
		return calendar;
	}

	private Calendar setRightTime(Calendar calendar) {
		if (calendar.getTimeInMillis() < Calendar.getInstance().getTimeInMillis()) {
			calendar.setTimeInMillis(calendar.getTimeInMillis() + lengthOfWeek);
		}
		
		return calendar;
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
