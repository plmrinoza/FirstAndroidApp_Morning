package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.util.Log;

/**

 * Created by androidstudio on 17/09/16.

 */

public class DbHandler extends SQLiteOpenHelper {

//all the fields we need
    static final String dbName ="myDB.db";
    static final int dbVersion = 1;
    static final String tableAccount = "tableAccount";
    static final String id = "id";
    static final String UN = "UN"; //username
    static final String EA = "email"; //email ad
    static final String FN = "firstname"; //first name
    static final String LN = "lastname"; //last name
    static final String PW = "PW"; //password
    static final String DC = "DC"; // Date Created
    static final String tableCreate =  "create table tableAccount " +
            "(id integer primary key, " +
            UN + " text, " +
            EA + " text, " +
            FN + " text, " +
            LN + " text, " +
            PW + " text, " +
            DC + " text);";
    SQLiteDatabase db;
//constructor
    public DbHandler(Context context){
        super(context, dbName, null, dbVersion);
    }

//create empty table
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(tableCreate);
        this.db = db;
    }

//updating table settings and stuff
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String query = "drop table if exists " + tableAccount;
        db.execSQL(query);
        this.onCreate(db);
    }

    //enter record to table
    public void regAccount(Accounts a){
        db = this.getWritableDatabase();
        ContentValues  values = new ContentValues();

        String query = "select * from tableAccount";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();

        values.put(id,count);
        values.put(UN, a.getUname());
        values.put(EA, a.getEmail());
        values.put(FN, a.getFname());
        values.put(LN, a.getLname());
        values.put(PW, a.getPass());
        values.put(DC, a.getDate());

        db.insert(tableAccount, null, values);
        db.close();
    }

    public boolean emailUnused(String email){
            db = this.getReadableDatabase();
            String query = "Select * from tableAccount where " + EA + " = '" + email+"'";
            Cursor cursor = db.rawQuery(query, null);
            if(cursor.getCount() <= 0){
                cursor.close();
                return true;
            }
            cursor.close();
            return false;
    }

    public boolean unameUnused(String uname){
        db = this.getReadableDatabase();
        String query = "Select * from tableAccount where " + UN + " = '" + uname+"'";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    public String matchPassEA (String email){
        db = this.getWritableDatabase();
        String query = "select "+ EA +", "+ PW +" from " + tableAccount;
        Cursor cursor = db.rawQuery(query,null);
        String e, p;
        p = null;
        if(cursor.moveToFirst()){
            do{
                e = cursor.getString(0);

                if(e.equals(email)){
                    p = cursor.getString(1);
                    break;
                }

            }while(cursor.moveToNext());
        }
        return p;
    }

    public String matchPassUN (String uname){
        db = this.getWritableDatabase();
        String query = "select " + UN + " ," + PW + " from " + tableAccount;
        Cursor cursor = db.rawQuery(query,null);
        String e, p;
        p = null;
        if(cursor.moveToFirst()){
            do{
                e = cursor.getString(0);

                if(e.equals(uname)){
                    p = cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());
        }
        return p;
    }
}

