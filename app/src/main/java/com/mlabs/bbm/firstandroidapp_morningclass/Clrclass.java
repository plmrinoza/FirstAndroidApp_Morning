package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by ojt.isg on 05/08/2016.
 */
public class Clrclass extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emptyscr);
        final Button btnMenu = (Button)findViewById(R.id.button2);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPage= new Intent(Clrclass.this,OnTouch.class);
                startActivity(nextPage);
            }
        });
    }
}
