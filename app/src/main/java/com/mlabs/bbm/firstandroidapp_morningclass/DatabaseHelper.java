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
    private static final String COL_ID = "id";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";
    private static final String COL_DATE_CREATED = "date_created";
    private static final String COL_FNAME = "first_name";
    private static final String COL_LNAME = "last_name";
    private static final String COL_UNAME = "username";

    public DatabaseHelper(Context con){
        super(con, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE  = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"  + COL_FNAME + " TEXT," + COL_LNAME + " TEXT," + COL_UNAME + " TEXT," + COL_EMAIL + " TEXT," + COL_PASSWORD + " TEXT," + COL_DATE_CREATED + " TEXT" + ")";
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
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_PASSWORD, password);
        contentValues.put(COL_DATE_CREATED, date);
        contentValues.put(COL_FNAME, fname);
        contentValues.put(COL_LNAME, lname);
        contentValues.put(COL_UNAME, uname);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getInfo(DatabaseHelper dop)
    {
        SQLiteDatabase sq = dop.getReadableDatabase();
        String[] columns = {COL_EMAIL, COL_PASSWORD, COL_ID, COL_DATE_CREATED, COL_FNAME,COL_LNAME, COL_UNAME };
        Cursor cr = sq.query(TABLE_NAME, columns, null, null, null, null, null);
        return cr;
    }

}
