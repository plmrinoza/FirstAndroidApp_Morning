package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    //db name
    public static final String databaseName = "accountsAndroid.db";

    //table name
    public static final String tableName = "accounts";
    //public static final String tableName = "users";

    //columns
    public static final String accounts_ID = "ID";
    public static final String accounts_EMAIL = "EMAIL";
    public static final String accounts_PASSWORD = "PASSWORD";
   // public static final String accounts_CREATED_AT = "CREATED_AT";


    //public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

    public DatabaseHelper(Context context){
        super(context, databaseName, null, 1);
        //For checking if database exists.
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     //   db.execSQL("Create table " + tableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, PASSWORD TEXT)");
        String CREATE_ACCOUNT_TABLE = "Create Table " + tableName + "("
             + accounts_ID + " INTEGER PRIMARY KEY,"
             + accounts_EMAIL + " TEXT UNIQUE,"
             + accounts_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_ACCOUNT_TABLE);

    }

        @Override
        public void onUpgrade(SQLiteDatabase db, int olderVersion, int newVersion) {
            // Drop older table if existed.
            db.execSQL("DROP TABLE IF EXISTS " + tableName);
            //Recreate Table.
            onCreate(db);
        }

    public boolean insertAccount (String email, String password) {
        String emailParameter = email;
        String passwordParameter = password;

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues insertValues = new ContentValues();
        insertValues.put("email", emailParameter);
        insertValues.put("password", passwordParameter);
        long result = db.insert(tableName, null, insertValues);

        if(result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean updateAccount (Integer id, String email, String password)  {
        String emailParameter = email;
        String passwordParameter = password;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("email", emailParameter);
        updatedValues.put("password", passwordParameter);
        db.update(tableName, updatedValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = new String[] { accounts_ID,accounts_EMAIL,accounts_PASSWORD };
        Cursor cursor = db.query(tableName, columns, null,
                null, null, null, null);
        return cursor;
    }
/*
public Cursor getAllData() {
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor  cursor = db.rawQuery("select * from "+ tableName,null);
    return cursor;
}
*/


}
