package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by androidstudio on 17/09/16.
 */
public class DatabaseAdapter {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    public DatabaseAdapter(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void createUser(String username, String password, String date) {
        database.execSQL("INSERT INTO "+ MySQLiteHelper.TABLE_USER +" VALUES('"+MySQLiteHelper.COLUMN_USERNAME+"','"+MySQLiteHelper.COLUMN_PASSWORD+"');");
    }
}
