package com.mlabs.bbm.firstandroidapp_morningclass;



import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Message;
import android.os.Handler;

import android.widget.ProgressBar;
import android.widget.TextView;

import android.util.Log;


import android.view.Window;

//Guide from http://www.codexpedia.com/android/android-splash-screen-with-progress-bar-example/
/**
 * Created by melbaMac on 8/4/16.
 */


public class SplashScreen2 extends Activity{
    private ProgressBar mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash2);

        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        Thread timerThread = new Thread(){
            public void run(){
                    insertMethodHere();
                    startApp();
                  //onPause();
            }
        };
        timerThread.start();
    }
    private void insertMethodHere() {
        for (int progress=0; progress<100; progress+=10) {
            try {
                Thread.sleep(350);
                mProgress.setProgress(progress);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void startApp() {
        Intent intent = new Intent(SplashScreen2.this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected  void onPause(){
        super.onPause();
        finish();
    }


}