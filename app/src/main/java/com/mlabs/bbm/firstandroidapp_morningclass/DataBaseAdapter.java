package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by jojomariemoralde on 23/09/16.
 */
public class DataBaseAdapter extends SQLiteOpenHelper {
    private static final String TAG = DataBaseAdapter.class.getSimpleName();
    //Database Name
    private static final String DATABASE_NAME = "user.db";
    //DB version
    private static final int DATABASE_VERSION = 1;
    //DB Table Name;
    private static final String TABLE_USER = "user";
    //Defining Column names;
    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_CREATED_AT = "created_at";

    public DataBaseAdapter(Context _context){
        super(_context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    //Define Database Table
    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_EMAIL + " TEXT UNQUE,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_CREATED_AT + " TEXT" + ")";
        sqlDB.execSQL(CREATE_USER_TABLE);
        Log.d(TAG, "Database tables created");

    }

    //Upgrading database this will happen if an update is available
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        //Recreate table.
        onCreate(db);
    }

    //Creating new user/s:
    public void registerUser(String email, String password, String created_at){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, email); //Email
        values.put(KEY_PASSWORD, password); //Password
        values.put(KEY_CREATED_AT, created_at); //Created At

        //Instering Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); //Close database connection

        Log.d(TAG, "Successfully Added user: " + id);
    }

    //Pulling records from Database
    public boolean validateUser(String userName,String password){
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM " + TABLE_USER + "WHERE " + KEY_EMAIL + "=" + userName;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        //Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            user.put("email", cursor.getString(1));
            user.put("password", cursor.getString(2));
            user.put("created_at", cursor.getString(3));
        }
        cursor.close();
        db.close();
        //return user record
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());
        //  Toast.makeText(DataBaseAdapter.this, "Password is incorrect", Toast.LENGHT_SHORT).show();
        if(password.equals(user.get(password))){

            Log.d(TAG, "password was validated ");
            return true;
        }
        else{

            Log.d(TAG, "Password mismatch ");
            return false;
        }
    }

    //Delete all registered user.
    public void deleteUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        //Delete All rows
        db.delete(TABLE_USER, null, null);
        db.close();
        Log.d(TAG, "Deleted all user records from sqlite");
    }
}
