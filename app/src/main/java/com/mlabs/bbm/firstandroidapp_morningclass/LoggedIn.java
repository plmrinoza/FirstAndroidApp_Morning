package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
//import android.widget.Toast;

/**
 * Created by Pedro on 05/08/2016.
 */

public class LoggedIn extends AppCompatActivity {

    Button touch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggedin);

        touch = (Button) findViewById(R.id.button2);

        touch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoggedIn.this, OnTouch.class );
                startActivity(intent);
            }
        });
    }
}
