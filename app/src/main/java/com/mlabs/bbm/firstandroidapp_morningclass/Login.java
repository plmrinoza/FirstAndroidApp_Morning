package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends AppCompatActivity {
    EditText emailEditText;
    EditText passEditText;
    TextView showText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log);

        emailEditText = (EditText) findViewById(R.id.Emailtxt);
        passEditText = (EditText) findViewById(R.id.Passwordtxt);
        showText = (TextView) findViewById(R.id.show);
        final Context CTX = this;

        findViewById(R.id.button).setOnClickListener(new OnClickListener() { //Log in button
            @Override
            public void onClick(View arg0) {
            String email = emailEditText.getText().toString();
            String pass = passEditText.getText().toString();
                if(email == "") //If Email field is empty
                {
                 emailEditText.setError("Please enter Email");
                }
                else if(pass == "") //If password field is empty
                {
                    passEditText.setError("Please enter Password");
                }
                else {
                    DatabaseHelper db = new DatabaseHelper(CTX);
                    Cursor cr = db.getInfo(db);
                    cr.moveToFirst();
                    boolean logstat = false;

                    do {
                        if (email.equals(cr.getString(0)) && (pass.equals(cr.getString(1)))) //checks database if entered information is present
                        {
                            logstat = true;
                        }
                    } while (cr.moveToNext());
                    if (logstat == true) {
                        Intent intent = new Intent(Login.this, After_Login.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Login.this, "Invalid Email or Password", Toast.LENGTH_LONG).show();
                        emailEditText.setText("");
                        passEditText.setText("");
                    }
                }

            }
        });

        findViewById(R.id.suBtn).setOnClickListener(new OnClickListener() { //Sign Up button
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
                finish();
            }
        });
        showText.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) { //Show password

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        passEditText.setTransformationMethod(null);
                        passEditText.setSelection(passEditText.getText().length());
                        return true;
                    case MotionEvent.ACTION_UP:
                        passEditText.setTransformationMethod(new PasswordTransformationMethod());
                        passEditText.setSelection(passEditText.getText().length());
                        return false;
                }
                return false;
            }
        });
    }

}
