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
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private static EditText username;
    private static EditText password;
    private static Button login_btn;
    public static TextView show;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginButton();
    }
    public void LoginButton() {
        username = (EditText) findViewById(R.id.editText_user);
        password = (EditText) findViewById(R.id.editText2_password);
        login_btn = (Button) findViewById(R.id.buttonSubmit);
        show = (TextView) findViewById(R.id.showtextView);

        login_btn.setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        if (username.getText().toString().endsWith(".com") &&
                                password.getText().toString().length() >= 8) {
                            Toast.makeText(MainActivity.this, "User and Password is Correct",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent("com.mlabs.bbm.firstandroidapp_morningclass.MainActivity.blank");
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "User and Password is Incorrect",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

                show.setOnTouchListener(new View.OnTouchListener() {
                                            public boolean onTouch(View view, MotionEvent event) {
                                                switch (event.getAction()) {
                                                    case MotionEvent.ACTION_DOWN:
                                                        password.setTransformationMethod(null);
                                                        return true;
                                                    case MotionEvent.ACTION_UP:
                                                        password.setTransformationMethod(new PasswordTransformationMethod());
                                                        return false;
                                                    case MotionEvent.ACTION_CANCEL:
                                                        password.setTransformationMethod(new PasswordTransformationMethod());
                                                        return false;
                                                    default:
                                                        return false;
                                                }
                                            }

                                        }
                );
    }

}
