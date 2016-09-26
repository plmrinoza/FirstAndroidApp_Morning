package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;

import com.mlabs.bbm.firstandroidapp_morningclass.MainAct.DatabaseHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper accountsDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accountsDb = new DatabaseHelper(this);

        final EditText loginEmail = (EditText) findViewById(R.id.editText_user;
        final EditText loginPassword = (EditText) findViewById(R.id.editText2_password);
        final TextView show = (TextView) findViewById(R.id.textView2_show);
        final Button loginwithregex = (Button) findViewById(R.id.buttonSubmit);
        final TextView register = (TextView) findViewById(R.id.textView2_Signup);

        loginwithregex.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String loginE = loginEmail.getText().toString().trim();
                String loginPass = loginPassword.getText().toString().trim();
                String verifyUser = accountsDb.getSinlgeEntry(loginE);
                    if(loginPass.equals(verifyUser)) {
                        String verifyUser = accountsDb.getSingleEntryUname(loginE);
                        String verifyEmail = accountsDb.getSingleEntryEmail(loginE);
                            if(validateEmail(loginE)){
                                if (loginPass.equals(verifyEmail)) {
                                    Intent myIntent = new Intent("com.mlabs.bbm.firstandroidapp_morningclass.MainActivity.user");
                                    startActivity(myIntent);
                                    finish();
                                    Toast.makeText(MainActivity.this,"Invalid Email and Password", Toast.LENGTH_LONG).show();
                                    loginEmail.requestFocus();
                                }
                            }
                               else if(loginPass.equals(verifyUser)) {
                                Intent myIntent = new Intent("com.mlabs.bbm.firstandroidapp_morningclass.MainActivity.user");
                                startActivity(myIntent);
                                finish();
                            }
                            else{
                                Toast.makeText(MainActivity.this,"Invalid Email and Password", Toast.LENGTH_LONG).show();
                                loginEmail.requestFocus();
                            }
                    }

            });

            show.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            loginPassword.setTransformationMethod(null);
                            return true;
                        case MotionEvent.ACTION_UP:
                            loginPassword.setTransformationMethod(new PasswordTransformationMethod());
                            return false;
                    }
                    return false;
                }

            });

            register.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(v.getContext(), "com.mlabs.bbm.firstandroidapp_morningclass.MainActivity.AccountRegister");
                    startActivityForResult(myIntent, 0);
                    onPause();
                }
            });

        }
    protected boolean validateEmail(String email) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
                }
                            }
    }