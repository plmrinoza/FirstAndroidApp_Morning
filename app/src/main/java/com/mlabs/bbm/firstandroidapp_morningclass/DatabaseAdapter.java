package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;


public class DatabaseAdapter extends SQLiteOpenHelper {
    private static final String TAG= DatabaseAdapter.class.getSimpleName();
    private static final String DATABASE_NAME= "user.db";
    private static final int DATABASE_VERSION= 1;
    private static final String TABLE_USER= "user";
    private static final String KEY_ID= "id";
    private static final String KEY_EMAIL= "email";
    private static final String KEY_PASSWORD= "password";
    private static final String KEY_CREATED_AT= "created_at";

    public DatabaseAdapter(Context _context){
        super(_context,DATABASE_NAME,null, DATABASE_VERSION);
    }

   @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_USER_TABLE= "CREATE TABLE" + TABLE_USER + "("
                + KEY_ID + "INTEGER PRIMARY KEY"
                + KEY_EMAIL + "TEXT UNIQUE"
                + KEY_PASSWORD + "TEXT," + KEY_CREATED_AT + "TEXT" + ")";
       db.execSQL(CREATE_USER_TABLE);

       Log.d(TAG, "Database tables created!");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_USER);
        onCreate(db);
    }

    public boolean registerUser(String email, String password,String created_at ){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL,email);
        values.put(KEY_PASSWORD,password);
        values.put(KEY_CREATED_AT,created_at);

        Long id = db.insert(TABLE_USER,null, values);
        db.close();

        Log.d(TAG, "Successfully Added User: " + id);
        return true;
    }

    public boolean validateUser(String userName, String password){
        HashMap<String,String> user= new HashMap<String,String>();
        String selectQuery = "SELECT email, password FROM " + TABLE_USER  + " WHERE " + KEY_EMAIL + "=\"" + userName+"\" AND " + KEY_PASSWORD + "=\""+password+"\"";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor  = db.rawQuery(selectQuery,null);

        boolean result = false;

        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            user.put("email", cursor.getString(1));
            user.put("password", cursor.getString(2));
            user.put("created_at", cursor.getString(3));
            result = true;
        }
        else
        {
            result = false;
        }
        cursor.close();
        db.close();
        return result;
        
      /**
        Log.d(TAG, "Fetching user for SQLite" + user.toString());
        if(password.equals(user.get(password))){
            Log.d(TAG, "Password was validated!");
            return true;
        }
        else{
            Log.d(TAG, "Password was incorrect");
            return false;
        }
    }
**/
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_USER, null, null);
        db.close();
        Log.d(TAG, "Deleted all user record from sqlite.");
    }



}
