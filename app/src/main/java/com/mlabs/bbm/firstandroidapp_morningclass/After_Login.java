package com.mlabs.bbm.firstandroidapp_morningclass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Earl on 05/08/2016.
 */
public class After_Login extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutt);

        findViewById(R.id.soBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(After_Login.this, Login.class);
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
