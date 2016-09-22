package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    EditText password, email, Conpass;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Context context = this;
        final DataBaseAdapter DataBaseAdapter;
        DataBaseAdapter = new DataBaseAdapter(this);

        email = (EditText) findViewById(R.id.etRegUser);
        password = (EditText) findViewById(R.id.etRegPass);
        Conpass = (EditText) findViewById(R.id.etConPass);
        register = (Button) findViewById(R.id.btnReg);

        final String Emails = email.getText().toString();
        final String Pass = password.getText().toString();
        final String confirmPassword = Conpass.getText().toString();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Emails.isEmpty() || Pass.isEmpty()
                        || confirmPassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "All Field must be Filled!", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!isValidEmail(Emails)) {
                    email.setError("Not valid Email Add!");
                } else if (!isValidPword(Pass)) {
                    password.setError("Not valid Password!");
                }

                if (!Pass.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    DataBaseAdapter.registerUser(Emails, Pass, GetCurrentDateAndTime());
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignUp.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            }

        });
    }

            private static final String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\\\.+[a-z]+";
            private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
            private Matcher matcher;

            public boolean isValidEmail(String Eadd) {
                matcher = pattern.matcher(Eadd);
                return matcher.matches();

            }
            public boolean isValidPword(String Pword) {
                return Pword.length() >=8;
            }

            public String GetCurrentDateAndTime(){
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            //get current date time with Date()
            Date date = new Date();
            System.out.println(dateFormat.format(date));
            return dateFormat.format(date).toString();
        }
    }