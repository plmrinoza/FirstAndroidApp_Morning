package com.mlabs.bbm.firstandroidapp_morningclass;



/**
 * Created by nard on 9/8/2016.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;


public class activity2 extends AppCompatActivity {
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nextact);
        ok = (Button) findViewById(R.id.button2);

        ok.setOnClickListener(new View.OnClickListener() {
        @Override

        public void onClick(View view){
                    Intent goTonextPage = new Intent(activity2.this, OnTouchActivity.class);
                    startActivity(goTonextPage);


                }
            });
        }
    }


