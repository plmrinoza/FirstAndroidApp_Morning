package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextUtils;

public class MainActivity extends AppCompatActivity {

    EditText password, email;
    TextView Show;
    Button login;
    boolean showpassword = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.etUser);
        password = (EditText) findViewById(R.id.etPass);
        Show = (TextView) findViewById(R.id.tvshow);
        login = (Button) findViewById(R.id.bLog);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().equals("user")
                        && password.getText().toString().equals("pass")) {
                    Toast.makeText(getApplicationContext(), "Connecting...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, UserAct.class);
                    startActivity(intent);
                } else if (isValidEmail(email.getText().toString()) == false || password.getText().toString().length() < 8) {
                    if (isValidEmail(email.getText().toString()) == false) {
                        Toast.makeText(getApplicationContext(), "Invalid E-mail!", Toast.LENGTH_SHORT).show();
                        email.getText().clear();
                        email.requestFocus();
                    }
                    if (password.getText().toString().length() < 8) {
                        Toast.makeText(getApplicationContext(), "Provide at least 15 characters long Password!", Toast.LENGTH_SHORT).show();
                        password.getText().clear();
                        password.requestFocus();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "E-mail and Password does not match!", Toast.LENGTH_SHORT).show();
                    email.getText().clear();
                    password.getText().clear();
                    email.requestFocus();
                }
            }

        });

        Show.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        password.setTransformationMethod(null);
                        return true;
                    case MotionEvent.ACTION_UP:
                        password.setTransformationMethod(new PasswordTransformationMethod());
                        return false;


                }
                return false;

            }
        });
    }


    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}



