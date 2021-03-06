package com.itborci.layers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
	public static final String KEY_ID = "id";
    public static final String KEY_SUBJECT = "subject";
    public static final String KEY_TEACHER = "teacher";
    public static final String KEY_ROOM = "room";
    public static final String KEY_DAY = "day";
    public static final String KEY_WEEK = "week";
    public static final String KEY_HOUR = "hour";
    public static final String KEY_COLOR = "color";
    public static final String KEY_BELL = "bell";
    private static final String TAG = "DBAdapter";
    
    private static final String DATABASE_NAME = "RoosterDB";
    private static final String DATABASE_TABLE = "subjects";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_CREATE =
        "create table subjects (id integer primary key autoincrement, "
        + "subject text not null, teacher text not null, room text not null, day integer not null, "
		+ "week integer not null, hour integer not null, color integer not null, "
        + "bell integer not null);";
        
    private final Context context; 
    
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx) 
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
        
    private static class DatabaseHelper extends SQLiteOpenHelper 
    {
        DatabaseHelper(Context context) 
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) 
        {
            db.execSQL(TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, 
        int newVersion) 
        {
            if(oldVersion != newVersion) {
                Log.w(TAG, "Upgrading database from version " + oldVersion
                        + " to "
                        + newVersion + ", which will destroy all old data");
                    db.execSQL("DROP TABLE IF EXISTS subjects");
                    onCreate(db);
            }
        }
    }    
    
    //Otevře DB
    public DBAdapter open() throws SQLException 
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //Zavře DB   
    public void close() throws SQLException
    {
        DBHelper.close();
    }
    
    //Vloží položku do tabulky subjects
    public long insertSubject(String subject, String teacher, String room, int day, int week, int hour, int color, int bell) throws SQLException 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_SUBJECT, subject);
        initialValues.put(KEY_TEACHER, teacher);
        initialValues.put(KEY_ROOM, room);
        initialValues.put(KEY_DAY, day);
        initialValues.put(KEY_WEEK, week);
        initialValues.put(KEY_HOUR, hour);
        initialValues.put(KEY_COLOR, color);
        initialValues.put(KEY_BELL, bell);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //Smaže určitou položku z tabulky subjects
    public boolean deleteSubjectById(long rowId) throws SQLException
    {
        return db.delete(DATABASE_TABLE, KEY_ID + "=" + rowId, null) > 0;
    }
    
    //Vrátí všechny záznamy z tabulky subjects
    public Cursor getAllSubjects() throws SQLException
    {
        return db.query(DATABASE_TABLE, new String[] {
        		KEY_ID, 
        		KEY_SUBJECT,
        		KEY_TEACHER,
        		KEY_ROOM,
        		KEY_DAY,
        		KEY_WEEK,
        		KEY_HOUR,
        		KEY_COLOR,
        		KEY_BELL}, 
                null, 
                null, 
                null, 
                null, 
                null);
    }

    //Vrátí určitý záznam z tabulky subjects
    public Cursor getSubjectById(long rowId) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {
                		KEY_ID, 
                		KEY_SUBJECT,
                		KEY_TEACHER,
                		KEY_ROOM,
                		KEY_DAY,
                		KEY_WEEK,
                		KEY_HOUR,
                		KEY_COLOR,
                		KEY_BELL},
                		KEY_ID + "=" + rowId, 
                		null,
                		null, 
                		null, 
                		null, 
                		null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //Aktualizace určitého záznamu z tabulky subjects
    public boolean updateSubjectById(long rowId, String subject, String teacher, String room, int day, int week, int hour, int color, int bell) throws SQLException 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_SUBJECT, subject);
        args.put(KEY_TEACHER, teacher);
        args.put(KEY_ROOM, room);
        args.put(KEY_DAY, day);
        args.put(KEY_WEEK, week);
        args.put(KEY_HOUR, hour);
        args.put(KEY_COLOR, color);
        args.put(KEY_BELL, bell);
        return db.update(DATABASE_TABLE, args, KEY_ID + "=" + rowId, null) > 0;
    }

    //Vrátí určitý záznam z tabulky subjects
    public Cursor getSubjectByWeekDayHour(int week, int day, int hour) throws SQLException
    {
        // TODO Beware of SQL Injection, use '?' parameters in WHERE clause
        Cursor mCursor = db.query(
                true,
                DATABASE_TABLE,
                new String[]{
                        KEY_ID,
                        KEY_SUBJECT,
                        KEY_TEACHER,
                        KEY_ROOM,
                        KEY_DAY,
                        KEY_WEEK,
                        KEY_HOUR,
                        KEY_COLOR,
                        KEY_BELL
                },
                KEY_WEEK + " = " + week + " and " +
                        KEY_DAY + " = " + day + " and " +
                        KEY_HOUR + " = " + hour,
                null,
                null,
                null,
                null,
                null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
}
