package com.example.rsanjib.dropme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class PartnerDb extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "dropMe.db";
    public static final String DBASE_TABLE_NAME = "partners";
    public static final String DBASE_COLUMN_ID = "pid";
    public static final String DBASE_COLUMN_CURR_LATITUDE= "current_latitude";
    public static final String DBASE_COLUMN_CURR_LONGITUDE = "current_longitude";
    private HashMap hp;

    public PartnerDb(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table partners " +
                        "(pid integer primary key, curr_latitude double, curr_longitude double)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS partners");
        onCreate(db);
    }

    public boolean insertContact (Integer id, Double curr_latitude, Double curr_longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("curr_latitude", curr_latitude);
        contentValues.put("curr_longitude", curr_longitude);
        db.insert("contacts", null, contentValues);
        return true;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from partners", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, DBASE_TABLE_NAME);
        return numRows;
    }

    public boolean updatepartners (Integer id, Double curr_latitude, Double curr_longitude, Double dest_latitude, Double dest_longitude ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("curr_latitude", curr_latitude);
        contentValues.put("curr_longitude", curr_longitude);
        db.update("partners", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("partners",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllpartners() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from partners", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(DBASE_COLUMN_ID)));
            res.moveToNext();
        }
        return array_list;
    }
}