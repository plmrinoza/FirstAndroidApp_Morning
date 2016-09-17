package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class blank extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank);

        final Button btnTouch = (Button) findViewById(R.id.btnTouch);
            btnTouch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(blank.this, OnTouchActivity.class);
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                }
            });
    }
}
