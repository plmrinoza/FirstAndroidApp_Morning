package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
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

        final Button login1 = (Button) findViewById(R.id.login);
        login1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final EditText Email1 = (EditText) findViewById(R.id.Email);
                final EditText Pw1 = (EditText) findViewById(R.id.Pw);
                final TextView show1 = (TextView) findViewById(R.id.show) ;

                String email = Email1.getText().toString();
                final String password = Pw1.getText().toString();

                if (!validateEmail(email)) {
                    Email1.setError("Not a valid email address!");
                } else if (!validatePassword(password)) {
                    Pw1.setError("Not a valid password!");
                } else {
                    Email1.setError(null);
                    Pw1.setError(null);
                    doLogin();
                }

                show1.setOnTouchListener(new View.OnTouchListener() {
                    @Override

                    public boolean onTouch(View view, MotionEvent motionEvent) {

                        switch (motionEvent.getAction()) {
                            case MotionEvent.ACTION_DOWN: {
                                Pw1.setTransformationMethod(null);
                                return true;
                            }

                            case MotionEvent.ACTION_UP: {
                                Pw1.setTransformationMethod(new PasswordTransformationMethod());
                                Pw1.setSelection(Pw1.getText().length());

                                return true;
                            }


                        }
                        return false;
                    }


                });

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

    public boolean validatePassword(String Pw) {
        return Pw.length() >=8;
    }

    public void doLogin() {
        Toast.makeText(getApplicationContext(), "Successfully Logged-in", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MainActivity.this, NextScreen.class);

        startActivity(i);


    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }


}



