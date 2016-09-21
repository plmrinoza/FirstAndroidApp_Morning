package com.mlabs.bbm.firstandroidapp_morningclass;


import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountRegister extends AppCompatActivity {

    //open database
    DatabaseHelper accountsDb;

    //variables of each views
    Button registerButton, viewAccountsButton, backButton;
    EditText registerEmail, registerPassword, registerVerifyPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_register);
        //open database
        accountsDb = new DatabaseHelper(this);


        registerEmail = (EditText) findViewById(R.id.registerEmail);
        registerPassword = (EditText) findViewById(R.id.registerPassword);
        registerVerifyPassword = (EditText) findViewById(R.id.registerVerifyPass);
        registerButton = (Button) findViewById(R.id.btnRegister);
        viewAccountsButton = (Button) findViewById(R.id.buttonViewAccounts);
        backButton = (Button) findViewById(R.id.buttonBack);
        addAccount();
        viewAll();
        back();
    }

    public void back(){
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
                onPause();
                finish();
            }
        });
    }

//method for adding account
    public void addAccount() {
        //Button register
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"Input Validation Success", Toast.LENGTH_LONG).show();
                String email=registerEmail.getText().toString();
                String password=registerPassword.getText().toString();
                String verifyPassword = registerVerifyPassword.getText().toString();
                //Check if Fields are empty.
                if(email.equals("")||password.equals("")||verifyPassword.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Field Incomplete.", Toast.LENGTH_LONG).show();
                    return;
                }else if (!validateEmail(email)) {
                    registerEmail.setError("Invalid Email Format");
                    registerEmail.requestFocus();
                }
                else if (!validatePassword(password)) {
                    registerPassword.setError("Atleast Minimum of 10 either Numerical or Alphabetical or Mix");
                    registerPassword.requestFocus();
                }
                // check if both password matches
                else if(!password.equals(verifyPassword))
                {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    // Save the Data in Database
                    accountsDb.insertAccount(email, password);
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }




   public void viewAll() {


        viewAccountsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = accountsDb.getAllData();
                if (cursor.getCount() == 0) {
                    //if no records found.
                    showMessage("Error", "No data found.");
                    //Toast.makeText(getApplicationContext(), "No data available.", Toast.LENGTH_LONG).show();
                return;
                }
                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    buffer.append("Id: " + cursor.getString(0) + " \n");
                    buffer.append("Email: " + cursor.getString(1) + "\n");
                    buffer.append("Password: " + cursor.getString(2) + "\n");
                }
                //show all data
                showMessage("Data",buffer.toString());
            }
        });

    }

public void showMessage(String title, String Message) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setCancelable(true);
    builder.setTitle(title);
    builder.setMessage(Message);
    builder.show();
}
    //Return true if email is valid and false if email is invalid
    protected boolean validateEmail(String email) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
    //Return true if password is valid and false if password is invalid
    protected boolean validatePassword(String password) {
        if(password!=null && password.length()>9) {
            return true;
        } else {
            return false;
        }
    }
/* Code for Choosing File
    public ArrayList<String> readFileFromSQLite() {

        fileName = new ArrayList<String>();

        fileSQLiteAdapter = new FileSQLiteAdapter(FileChooser.this);
        fileSQLiteAdapter.openToRead();
        cursor = fileSQLiteAdapter.queueAll();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    fileName.add(cursor.getString(cursor
                            .getColumnIndex(FileSQLiteAdapter.KEY_CONTENT1)));
                } while (cursor.moveToNext());

            }
            cursor.close();

        }
        fileSQLiteAdapter.close();
        return fileName;

    }
    */


    @Override
    protected void onPause() {
        super.onPause();
    }
}