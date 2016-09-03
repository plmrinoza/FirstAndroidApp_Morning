package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class login extends AppCompatActivity {

    Button thbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        thbtn = (Button) findViewById(R.id.tchbtn);

        thbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,OnTouchActivity.class);
                startActivity(intent);

            }
        });

        thbtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent intent = new Intent(login.this,OnTouchActivity.class);
                startActivity(intent);
                return false;
            }
        });
    }
}
