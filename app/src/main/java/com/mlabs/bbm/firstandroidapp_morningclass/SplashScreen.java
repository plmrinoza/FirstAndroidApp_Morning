package com.mlabs.bbm.firstandroidapp_morningclass;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.os.Handler;



public class SplashScreen extends MainActivity{
    private ProgressBar mProgress;
    private int mProgressStatus = 0;

    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);


        mProgress = (ProgressBar) findViewById(R.id.progressBar);


        new Thread(new Runnable() {
            public void run() {
                while (mProgressStatus < 100) {
                    mProgressStatus = mProgressStatus + 10;


                    mHandler.post(new Runnable() {
                        public void run() {
                            mProgress.setProgress(mProgressStatus);
                        }

                    });
                }
                Intent i = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(i);
            }
        }).start();

    }

    @Override
    protected  void onPause(){
        super.onPause();
        finish();
    }
}
