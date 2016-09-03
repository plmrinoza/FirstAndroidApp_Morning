package com.mlabs.bbm.firstandroidapp_morningclass.MainAct;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;

import com.mlabs.bbm.firstandroidapp_morningclass.R;

public class blank extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_blank);
        final Button BtnTouch=(Button)findViewById(R.id.btntouch);
        BtnTouch.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent intent = new Intent("com.mlabs.bbm.firstandroidapp_morningclass.MainActivity.activityOnTouch");
                startActivity(intent);
            }
        });


    }
}
