package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = (Button) findViewById(R.id.login);
        final Button show = (Button) findViewById(R.id.show) ;
        final EditText Email = (EditText) findViewById(R.id.Email);
        final EditText Username = (EditText) findViewById(R.id.Email);
        final EditText Pw = (EditText) findViewById(R.id.Pw);
        final Button reg = (Button) findViewById(R.id.btnReg);

        show.setOnTouchListener(new View.OnTouchListener() {
            @Override

            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Pw.setTransformationMethod(null);
                        Pw.setSelection(Pw.getText().length());
                        return true;
                    }
                    case MotionEvent.ACTION_UP: {
                        Pw.setTransformationMethod(new PasswordTransformationMethod());
                        Pw.setSelection(Pw.getText().length());
                        return true;
                    }
                }
                return false;
            }

        });
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (Email.equals("") || Pw.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fill Up required fields", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!validateEmail(Email.getText().toString())) {
                    Email.setError("Not a valid email address!");
                }   else if (!validateUsername(Username.getText().toString())) {
                    Username.setError("Not a valid user name!");
                }
                if (!validatePassword(Pw.getText().toString())) {
                    Pw.setError("Not a valid password!");
                } else {
                    Email.setError(null);
                    Pw.setError(null);
                    doLogin();
                }
            }
        });

        reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SignUp.class);
                startActivity(i);
            }

        });

    }

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static final String USERNAME = "^[a-z0-9_-]{3,15}$";
    private Pattern pattern1 = Pattern.compile(USERNAME);
    private Matcher matcher1;

    public boolean validateUsername(String username) {
        matcher1 = pattern1.matcher(username);
        return matcher1.matches();
    }


    public boolean validatePassword(String Pw) {
        return Pw.length() >=8;
    }

    public void doLogin() {
        Toast.makeText(getApplicationContext(), "Successfully Logged-in", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MainActivity.this, TouchActivity.class);

        startActivity(i);
    }

    protected void onPause() {
        super.onPause();
        finish();
    }

}
