package com.example.polemistesfitness;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Total.db";
    private static final int DATABASE_VERSION = 1;

    //The table and names no spaces allowed here put _
    private static final String TABLE_NAME = "my_set";
    private static final String TOTAL_ID = " total_id";
    private static final String TOTAL_EXERCISENAME = "total_exercisename";
    private static final String TOTAL_WEIGHT = "total_weight";
    private static final String TOTAL_REPS = "total_reps";
    private static final String TOTAL_DATE = "total_date";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //in your create statement you NEED spaces after every one
        String query = "CREATE TABLE " + TABLE_NAME
                + " (" + TOTAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                TOTAL_EXERCISENAME + " TEXT, " +
                TOTAL_WEIGHT + " INTEGER, " +
                TOTAL_REPS + " INTEGER, " +
                TOTAL_DATE + "  TEXT);";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addSet(String exerciseName, int weight, int reps, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        //content values is used to put shit into database table
        ContentValues cv  = new ContentValues();

        cv.put(TOTAL_EXERCISENAME,exerciseName);
        cv.put(TOTAL_WEIGHT,weight);
        cv.put(TOTAL_REPS,reps);
        cv.put(TOTAL_DATE,date);

        //this method inserts and will store the result
        long result = db.insert(TABLE_NAME, null, cv);
        //if its -1 our application faield to insert
        if (result==-1){
            Toast.makeText(context, "FAILED", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "add success", Toast.LENGTH_SHORT).show();
        }
    }

    //method to read all data from our database table
    Cursor readAllDate() {

//this select query needs a WHERE clause so the date is only from last week
        String query = " SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        if(db!=null)
        {
            cursor = db.rawQuery(query, null);
        }
        return cursor; //This will contain all the data from the db table
    }

    //This query will return all sets from that day

    Cursor readDate(String date) {

        String query = " SELECT * FROM " + TABLE_NAME + " WHERE " + TOTAL_DATE + " LIKE" + " '%" + date + "%' ";
        System.out.println(query);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        if(db!=null)
        {
            cursor = db.rawQuery(query, null);
        }
//this cursor is returning empty
        return cursor;

    }
}
