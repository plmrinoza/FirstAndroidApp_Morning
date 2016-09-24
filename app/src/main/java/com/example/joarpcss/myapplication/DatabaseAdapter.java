package com.example.joarpcss.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Joar PCs's on 9/23/2016.
 */

public class DatabaseAdapter extends SQLiteOpenHelper {

    private static final String TAG = DatabaseAdapter.class.getSimpleName();

    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USER = "users";

    private static final String ID = "userId";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String DATECREATED = "date_created";


    public DatabaseAdapter(Context _context) {
        super(_context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" + ID + "INTEGER PRIMARY KEY, "
                + EMAIL + " TEXT UNIQUE," + PASSWORD + " TEXT," + DATECREATED + " TEXT" + ")";

        sqlDB.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void registerUser(String email, String password, String datecreated) {
        SQLiteDatabase db = this.getWritableDatabase();


            ContentValues values = new ContentValues();
            values.put(EMAIL, email);
            values.put(PASSWORD, password);
            values.put(DATECREATED, datecreated);

            //long id = db.insert(TABLE_USER, null, values);

            db.insert(TABLE_USER, null, values);
            db.close();

    }


    public boolean validateUser(String emailAdd, String password) {

        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE " + EMAIL + " = '" + emailAdd +"' AND " + PASSWORD + " = '" + password + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;
        }

        cursor.close();
        db.close();
        return false;


    }

    public boolean validateEmail(String emailAdd) {
        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE " + EMAIL + " = '" + emailAdd +"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        if (cursor.getCount() <= 0) {
            cursor.close();
            return true;
        }
            cursor.close();
            return false;
    }
}
