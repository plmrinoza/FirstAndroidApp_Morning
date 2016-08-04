package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static EditText username;
    private static EditText password;
    private static Button login_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginButton();
    }

    public void LoginButton(){
        username = (EditText) findViewById(R.id. editText_user);
        password = (EditText) findViewById(R.id. editText2_password);
        login_btn = (Button) findViewById(R.id.buttonSubmit);

        login_btn.setOnClickListener(
                new OnClickListener(){

                    @Override
                    public void onClick(View view) {

                        if (username.getText().toString().equals("drevstar@yahoo.com") &&
                                password.getText().toString().equals("12345678")) {
                            Toast.makeText(MainActivity.this,"User and Password is Correct",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent("com.mlabs.bbm.firstandroidapp_morningclass.MainActivity.user");
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this,"User and Password is Incorrect",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }

                }
        );
    }
}
