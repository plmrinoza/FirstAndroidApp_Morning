package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static EditText Username;
    private static EditText password;
    private static TextView attempts;
    private static Button Login_btn;
    int attempt_counter = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void LoginButton() {
        Username = (EditText) findViewById(R.id.etUser);
        password = (EditText) findViewById(R.id.etPass);
        attempts = (TextView) findViewById(R.id.tvNumattempt);
        Login_btn = (Button) findViewById(R.id.bLog);

        attempts.setText(Integer.toString(attempt_counter));

        Login_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Username.getText().toString().equals("user") &&
                                password.getText().toString().equals("pass")) {
                            Toast.makeText(MainActivity.this, "User and Password is correct",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, UserAct.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "User and Password is not correct",
                                    Toast.LENGTH_SHORT).show();
                            attempt_counter--;
                            attempts.setText(Integer.toString(attempt_counter));
                            if (attempt_counter == 0) {
                                Login_btn.setEnabled(false);
                            }
                        }

                    }
                }
        );
    }
}



