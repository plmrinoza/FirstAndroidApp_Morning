package com.mlabs.bbm.firstandroidapp_morningclass;

/**
 * Created by homer on 9/27/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class AL extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.avtivity_main_lo);

        findViewById(R.id.soBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AL.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    protected  void onPause(){
        super.onPause();
        finish();
    }
}
