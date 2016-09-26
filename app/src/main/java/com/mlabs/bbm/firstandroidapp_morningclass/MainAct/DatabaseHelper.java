package com.mlabs.bbm.firstandroidapp_morningclass.MainAct;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "accountsAndroid.db";
    public static final String TAG = DatabaseHelper.class.getSimpleName();

    public static final String tableName = "accounts";

    public static final String accounts_ID = "ID";
       public static final String accounts_FIRSTNAME = "FIRSTNAME";
       public static final String accounts_LASTNAME = "LASTNAME";
       public static final String accounts_USERNAME = "USERNAME";
    public static final String accounts_EMAIL = "EMAIL";
    public static final String accounts_PASSWORD = "PASSWORD";
    public DatabaseHelper(Context context) {
        super(context, databaseName, null, 1);
        //For checking if database exists.
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //   db.execSQL("Create table " + tableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, PASSWORD TEXT)");
        String CREATE_ACCOUNT_TABLE = "Create Table " + tableName + "("
                + accounts_ID + " INTEGER PRIMARY KEY,"
                + accounts_FIRSTNAME + " TEXT,"
                + accounts_LASTNAME + " TEXT,"
                + accounts_USERNAME + " TEXT,"
                + accounts_EMAIL + " TEXT UNIQUE,"
                + accounts_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_ACCOUNT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int olderVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + tableName);

        onCreate(db);
    }

    public boolean insertAccount(String email, String password) {
        String emailParameter = email;
        String passwordParameter = password;

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues insertValues = new ContentValues();
        insertValues.put("email", emailParameter);
        insertValues.put("password", passwordParameter);
        long result = db.insert(tableName, null, insertValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateAccount(Integer id, String email, String password) {
        String emailParameter = email;
        String passwordParameter = password;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("email", emailParameter);
        updatedValues.put("password", passwordParameter);
        db.update(tableName, updatedValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }
        public String getSinlgeEntry(String userName) {
            public String getSingleEntryUname(String username) {
            SQLiteDatabase db = this.getReadableDatabase();
                    Cursor cursor = db.query(tableName, null, " EMAIL=?",
                                    new String[] { userName }, null, null, null);
                    Cursor cursor = db.query(tableName, null, " USERNAME=?", new String[]{username}, null, null, null);
            if (cursor.getCount() < 1) {
                cursor.close();
                return "NOT EXIST";
            }
            cursor.moveToFirst();
            String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
            cursor.close();
            return password;
        }
            public String getSingleEntryEmail(String email){
                    {
                                SQLiteDatabase db = this.getReadableDatabase();
                        Cursor cursor = db.query(tableName, null, " EMAIL=?", new String[]{email}, null, null, null);
                        if (cursor.getCount() < 1) {
                                cursor.close();
                                return "NOT EXIST";
                            }
                        cursor.moveToFirst();
                        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
                        cursor.close();
                        return password;
                    }
                }
    public Cursor getAllData() {

        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = new String[] { accounts_ID,accounts_EMAIL,accounts_PASSWORD };
        Cursor cursor = db.query(tableName, columns, null,
                null, null, null, null);
        return cursor;
    }
}