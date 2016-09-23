package com.mlabs.bbm.firstandroidapp_morningclass;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import om.mlabs.bbm.firstandroidapp_morningclass.R;

public class blank extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);

    }
    @Override
    protected void onPause(){
        super.onPause();
        finish();
}}
