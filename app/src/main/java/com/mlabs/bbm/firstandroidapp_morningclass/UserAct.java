package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Adrianne on 9/2/2016.
 */
public class UserAct extends AppCompatActivity {
    Button btt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        btt = (Button) findViewById(R.id.button);


        btt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub

                Intent intent2 = new Intent(getApplicationContext(), TouchActivity.class);
                startActivity(intent2);


            }

        });
    }
}