package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    
    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "Register.db";
    private static final String TABLE_NAME = "Users";
    private static final String CALL_ID= "id";
    private static final String CALL_EMAIL = "email";
    private static final String CALL_PASSWROD = "password";
    private static final String CALL_DATE_CREATED = "date_created";
    private static final String CALL_FNAME = "first_name";
    private static final String CALL_LNAME = "last_name";
    private static final String CALL_UNAME = "username";

    public DatabaseHelper(Context con){
        super(con, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE  = "CREATE TABLE " + TABLE_NAME + " (" + CALL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"  + CALL_FNAME + " TEXT," + CALL_LNAME + " TEXT," + CALL_UNAME + " TEXT," + CALL_EMAIL + " TEXT," + CALL_PASSWROD + " TEXT," + CALL_DATE_CREATED + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXIST" + TABLE_NAME);
        onCreate(db);
    }

    public boolean insetData(String email, String password, String date, String fname, String lname, String uname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CALL_EMAIL, email);
        contentValues.put(CALL_PASSWROD, password);
        contentValues.put(CALL_DATE_CREATED, date);
        contentValues.put(CALL_FNAME, fname);
        contentValues.put(CALL_LNAME, lname);
        contentValues.put(CALL_UNAME, uname);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getInfo(DatabaseHelper dop)
    {
        SQLiteDatabase sq = dop.getReadableDatabase();
        String[] columns = {CALL_EMAIL, CALL_PASSWROD, CALL_ID, CALL_DATE_CREATED, CALL_FNAME, CALL_LNAME, CALL_UNAME};
        Cursor cr = sq.query(TABLE_NAME, columns, null, null, null, null, null);
        return cr;
    }

}
