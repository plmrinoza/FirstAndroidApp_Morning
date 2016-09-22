package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by androidstudio on 17/09/16.
 */
public class UsersDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = {MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_EMAIL, MySQLiteHelper.COLUMN_PASSWORD, MySQLiteHelper.COLUMN_DATE};
    //testing di makapagpush and pull

    public UsersDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void createUser(String email, String password, String date) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_EMAIL, email);
        values.put(MySQLiteHelper.COLUMN_PASSWORD, password);
        values.put(MySQLiteHelper.COLUMN_DATE, date);
        long insertId = database.insert(MySQLiteHelper.TABLE_USER, null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_USER, allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);

        cursor.close();
    }

    public User getUser(String email){
        User user = null;

        Cursor cursor = database.query(MySQLiteHelper.TABLE_USER, allColumns, "emails = ?", new String[]{String.valueOf(email)}, null, null, null, null);

        if(cursor != null)
            cursor.moveToFirst();

        user = cursorToUser(cursor);

        return user;
    }

    public User cursorToUser(Cursor cursor) {
        User user = new User();
        user.setId(cursor.getLong(0));
        user.setEmail(cursor.getString(1));
        user.setPassword(cursor.getString(2));
        user.setDate(cursor.getString(3));

        return user;
    }
}
