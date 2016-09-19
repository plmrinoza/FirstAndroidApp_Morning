package com.mlabs.bbm.firstandroidapp_morningclass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD

=======
>>>>>>> f6606f551478ab79d64a5beb1c384f7dfe39ff27

/**
 * Created by benjarmanalili on 31/07/2016.
 */
public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
<<<<<<< HEAD
                } finally {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
=======
                }
                finally{
                    Intent intent = new Intent(SplashScreen.this,Login.class );
>>>>>>> f6606f551478ab79d64a5beb1c384f7dfe39ff27
                    startActivity(intent);
                }
            }
        };
        timerThread.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }


}