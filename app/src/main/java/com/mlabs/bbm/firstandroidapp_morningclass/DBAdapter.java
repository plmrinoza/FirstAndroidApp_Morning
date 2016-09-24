package com.mlabs.bbm.firstandroidapp_morningclass;

/**
 * Created by androidstudio on 24/09/16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class DBAdapter {

    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    static final String DATABASE_CREATE = "create table " + "LOGIN" + "(" + "ID" + " integer primary key autoincrement," + "USERNAME text,PASSWORD text); ";
}
