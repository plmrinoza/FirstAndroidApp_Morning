package com.example.joarpcss.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button btnOnTouch = (Button) findViewById(R.id.btnOnTouch);

        btnOnTouch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(Main2Activity.this, OnTouchActivity.class);
                startActivity(intent);

            }
        });
    }
}
