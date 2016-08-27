package com.mlabs.bbm.firstandroidapp_morningclass;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Earl on 05/08/2016.
 */
public class After_Login extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layoutt);
    }
    @Override
    protected  void onPause(){
        super.onPause();
        finish();
    }
}
