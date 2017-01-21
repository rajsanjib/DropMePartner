package com.example.rsanjib.dropme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DropDb extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "dropMe.db";
    public static final String DBASE_TABLE_NAME = "bookings";
    public static final String DBASE_COLUMN_ID = "client_id";
    public static final String DBASE_COLUMN_CURR_LATITUDE= "current_latitude";
    public static final String DBASE_COLUMN_CURR_LONGITUDE = "current_longitude";
    public static final String DBASE_COLUMN_DEST_LATITUDE= "dest_latitude";
    public static final String DBASE_COLUMN_DEST_LONGITUDE = "dest_longitude";
    private HashMap hp;

    public DropDb(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table bookings " +
                        "(id integer primary key, curr_latitude double, curr_longitude double, dest_latitude double, dest_longitude double)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS bookings");
        onCreate(db);
    }

    public boolean insertContact (Integer id, Double curr_latitude, Double curr_longitude, Double dest_latitude, Double dest_longitude ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("curr_latitude", curr_latitude);
        contentValues.put("curr_longitude", curr_longitude);
        contentValues.put("dest_latitude", dest_latitude);
        contentValues.put("dest_longitude", dest_longitude);
        db.insert("contacts", null, contentValues);
        return true;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from bookings", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, DBASE_TABLE_NAME);
        return numRows;
    }

    public boolean updateBookings (Integer id, Double curr_latitude, Double curr_longitude, Double dest_latitude, Double dest_longitude ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("curr_latitude", curr_latitude);
        contentValues.put("curr_longitude", curr_longitude);
        contentValues.put("dest_latitude", dest_latitude);
        contentValues.put("dest_longitude", dest_longitude);
        db.update("bookings", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("bookings",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllBookings() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from bookings", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(DBASE_COLUMN_ID)));
            res.moveToNext();
        }
        return array_list;
    }
}