package com.mlabs.bbm.firstandroidapp_morningclass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by androidstudio on 06/08/16.
 */
public class blank extends Activity{
    @Override
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);

        setContentView(R.layout.blankpage);
        final Button btnTouch = (Button)findViewById(R.id.btnOnTouch);

        btnTouch.setOnClickListener(new View.OnClickListener(){
            @Override
         public void onClick(View view){
                Intent goTonextPage = new Intent(blank.this,OnTouchActivity.class);
                startActivity(goTonextPage);
            }
        });
    }
}
