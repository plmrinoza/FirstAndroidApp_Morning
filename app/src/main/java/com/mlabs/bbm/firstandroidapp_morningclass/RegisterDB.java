package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Fatima Gargar on 9/19/2016.
 */g
public class RegisterDB extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "Register.db";
    private static final String TABLE_NAME = "Users";
    private static final String COL_ID = "id";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";
    private static final String COL_DATE_CREATED = "date_created";

    public RegisterDB(Context con){
        super(con, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE  = "CREATE TABLE" + TABLE_NAME + "(" + COL_ID + "INTEGER PRIMARY," + COL_EMAIL + "TEXT," + COL_PASSWORD + "TEXT," + COL_DATE_CREATED + "DATETIME," + ")";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXIST" + TABLE_NAME);
        onCreate(db);
    }

}
