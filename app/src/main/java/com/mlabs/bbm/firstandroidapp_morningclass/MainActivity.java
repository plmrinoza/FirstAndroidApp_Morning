package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
         private static EditText Username;
         private static EditText Password;
         private static Button login_btn;
         private static TextView show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                LoginButton();
            }

                public void LoginButton() {
                    Username = (EditText) findViewById(R.id.editText_user);
                    Password = (EditText) findViewById(R.id.editText2_password);
                    login_btn = (Button) findViewById(R.id.buttonSubmit);
                    show = (TextView) findViewById(R.id.showpass);

                    login_btn.setOnClickListener(
                            new OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (Username.getText().toString().endsWith(".com") &&
                                            Password.getText().toString().length() >= 8) {
                                        Toast.makeText(MainActivity.this, "User and Password is Correct",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MainActivity.this, blank.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(MainActivity.this, "User and Password is Incorrect",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                    );
                    show.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {


                            switch (event.getAction()) {
                                case MotionEvent.ACTION_DOWN:
                                    EditText pText = (EditText) findViewById(R.id.editText2_password);
                                    pText.setInputType(InputType.TYPE_CLASS_TEXT);
                                    break;
                                case MotionEvent.ACTION_UP:
                                    EditText aText = (EditText) findViewById(R.id.editText2_password);
                                    aText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                                    break;

                            }
                            return true;
                        }
                    });
                }
                    protected boolean validatePassword(String email){
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);


        return matcher.matches();
    }



                }








