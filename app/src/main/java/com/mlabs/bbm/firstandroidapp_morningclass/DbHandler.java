package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**

 * Created by androidstudio on 17/09/16.

 */

public class DbHandler extends SQLiteOpenHelper {

//all the fields we need
    static final String dbName ="myDB";
    static final int dbVersion = 1;
    static final String tableAccount = "tableAccount";
    static final String id = "id";
    static final String UN = "UN"; //email ad
    static final String PW = "PW"; //password
    static final String DC = "DC"; // Date Created

//constructor
    public DbHandler(Context context){
        super(context, dbName, null, dbVersion);
    }

//create empty table
    @Override
    public void onCreate(SQLiteDatabase db){
        String createAccountTable = "CREATE TABLE " + tableAccount +
                "(" +
                id + " INTEGER PRIMARY KEY, " +
                UN + " TEXT, " +
                PW + " TEXT, " +
                DC + " TEXT " + //there's no separate date format unfortunately
                ")";
        db.execSQL(createAccountTable);

    }

//updating table settings and stuff
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        if(oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + tableAccount);
            onCreate(db);
        }
    }

    //enter record to table
    public void addAccount(){
        //finish this
    }

    //read a record
    public void getAccount(){
    }
}