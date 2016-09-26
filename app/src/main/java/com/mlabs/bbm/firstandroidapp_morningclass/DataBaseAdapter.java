//DATABASE ADAPTER//

package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;


/**
 * Created by LocalAd on 9/20/2016.
 */
public class DataBaseAdapter extends SQLiteOpenHelper {
    private static final String TAG = DataBaseAdapter.class.getSimpleName();
    //DB Name
    private static final String DATABASE_NAME = "users.db";
    //DB version
    private static  final int DATABASE_VERSION = 1;
    //DB TableName
    private static final String TABLE_USER = "user";
    //Defining Column names:
    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_CREATE_AT = "create_at";

    private static final String KEY_FIRST_NAME = "firstname";
    private static final String KEY_LAST_NAME = "lastname";
    private static final String KEY_USER_NAME = "username";


    static final String DATABASE_CREATE = "create table " + "LOGIN" + "( "
            + "ID" + " integer primary key autoincrement,"
            + "FirstName text, LastName text, UserName text, Email  text,Password text); ";


    public SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;

    public DataBaseAdapter(Context _context){
        super(_context, DATABASE_NAME,null,DATABASE_VERSION);
        context = _context;
        dbHelper = new DataBaseHelper(_context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }

    public DataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDb(){
        return db;
    }


    //Define Database Table
    @Override
    public void onCreate(SQLiteDatabase sqlDB){
        String CREATE_USER_TABLE = "CREATE TABLE" + TABLE_USER + "("
                + KEY_ID + "INTEGER PRIMARY KEY,"
                + KEY_FIRST_NAME + "TEXT,"
                + KEY_LAST_NAME + "TEXT,"
                + KEY_USER_NAME + "TEXT UNIQUE,"
                + KEY_EMAIL + "TEXT UNIQUE,"
                + KEY_PASSWORD + "TEXT,"
                + KEY_CREATE_AT + " TEXT " + ")";

        sqlDB.execSQL(CREATE_USER_TABLE);
        Log.d(TAG, "Database tables created");
    }

    //Updating database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion){
        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        //Recreate Table
        onCreate(db);
    }

    //Creating new user/s:
    public void registerUser(String firstname, String lastname, String username, String email, String password, String create_at){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FIRST_NAME, firstname);//first name
        values.put(KEY_LAST_NAME, lastname);//last name
        values.put(KEY_USER_NAME, username);//user name
        values.put(KEY_EMAIL, email); //email
        values.put(KEY_PASSWORD, password); //password
        values.put(KEY_CREATE_AT, create_at); // created at

        //Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); //close database connection

        Log.d(TAG, "Successfully Added User " +id);

    }

    //Pulling records from Database
    public boolean validateUser(String userName, String password){
        HashMap<String,String> user = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM " + TABLE_USER + "WHERE" +
                KEY_EMAIL+ "=" + userName;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        //Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0){
            user.put("firstname", cursor.getString(1));
            user.put("lastname", cursor.getString(2));
            user.put("username", cursor.getString(3));
            user.put("email", cursor.getString(4));
            user.put("password", cursor.getString(5));
            user.put("created_at", cursor.getString(6));
        }
        cursor.close();
        db.close();
        //return user record
        Log.d(TAG, "Fetching user from SQLite: " + user.toString());
               // Toast.makeText(DataBaseAdapter.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
        if(password.equals(user.get(password))){

            Log.d(TAG, "Password was validated ");
            return true;
        }
        else {

            Log.d(TAG, "Password Incorrect ");
            return false;
        }
    }

    public long insertPlayer(String UsernameReg, String PasswordReg, String EmailReg)
    {
        ContentValues values = new ContentValues();
        values.put("USERNAME", UsernameReg);
        values.put("PASSWORD", PasswordReg);
        values.put("EMAIL", EmailReg);

        return db.insertWithOnConflict(DATABASE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);

    }

    public String getData() {
      return null;
    }
}
