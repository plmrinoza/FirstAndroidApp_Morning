package com.mlabs.bbm.firstandroidapp_morningclass;

/**
 * Created by nard on 9/8/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by benjarmanalili on 31/07/2016.
 */
public class splash extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splashscreen);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                finally{
                    Intent intent = new Intent(splash.this,MainActivity.class );
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected  void onPause(){
        super.onPause();
        finish();
    }
}