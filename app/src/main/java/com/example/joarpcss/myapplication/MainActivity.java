package com.example.joarpcss.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
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
        Button login = (Button) findViewById(R.id.btnLogin);
        TextView show = (TextView) findViewById(R.id.txtShow);
        TextView signup = (TextView) findViewById(R.id.txtSignUp);


        final DatabaseAdapter sqlDB = new DatabaseAdapter(getApplicationContext());

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText emailAdd = (EditText) findViewById(R.id.etxtEmailAdd);
                EditText pword = (EditText) findViewById(R.id.etxtPword);

                //if (checkLogin(emailAdd.getText().toString(), pword.getText().toString()) == true) {
                if (sqlDB.validateUser(emailAdd.getText().toString(), pword.getText().toString()) == true) {
                    //Toast.makeText(getApplicationContext(), "Connecting...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, UserAccountActivity.class);
                    startActivity(intent);

                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(
                            MainActivity.this).create();
                    alertDialog.setMessage("Invalid Email Address/Password");
                    alertDialog.show();
                }

            }
        });

        show.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        EditText showPword = (EditText) findViewById(R.id.etxtPword);
                        showPword.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;

                    case MotionEvent.ACTION_UP:
                        EditText hidePword = (EditText) findViewById(R.id.etxtPword);
                        hidePword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                return true;
            }

        });

        signup.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent signup = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(signup);
            }
        });
    }

    public boolean checkLogin(String emailAdd, String pword) {
        String regexEmail = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";

        Pattern p = Pattern.compile(regexEmail);
        Matcher m = p.matcher(emailAdd);

        if (pword.length() >= 8 && m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected  void onPause(){
        super.onPause();
        finish();
    }
}
