package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.view.View;
import android.widget.Toast;


public class OnTouchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_touch);

        final ImageView image = (ImageView) findViewById(R.id.imageView2);


        image.setOnTouchListener(new View.OnTouchListener() {
            float iniX, finX, iniY, finY;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //int event = motionEvent.getAction();
                switch (motionEvent.getAction()) {
                 // switch (event) {
                    case MotionEvent.ACTION_DOWN:
                        //Toast.makeText(context, text, duration);

                        iniX = motionEvent.getX();
                        iniY = motionEvent.getY();
                        Toast.makeText(OnTouchActivity.this, "Value of X: " + iniX + "Value of Y: " + iniY, Toast.LENGTH_SHORT).show();
                        break;
                        //return true;
                    case MotionEvent.ACTION_UP:
                        finX = motionEvent.getX();
                        finY = motionEvent.getY();
                        //finY =(int) motionEvent.getY();

                        if(iniX > finX) {
                            Toast.makeText(OnTouchActivity.this, "Right to Left Swipe, X: " + finX + "Y: " + finY, Toast.LENGTH_SHORT).show();
                        }
                        if(iniX < finX) {
                            Toast.makeText(OnTouchActivity.this, "Left to Right Swipe, X: " + finX + "Y: " + finY, Toast.LENGTH_SHORT).show();
                        }
                        if(iniY < finY){
                           Toast.makeText(OnTouchActivity.this, "Up to Down Swipe, X: " + finX + "Y: " + finY, Toast.LENGTH_SHORT).show();
                        }
                        if(iniY > finY){
                            Toast.makeText(OnTouchActivity.this, "Down to Up Swipe, X: " + finX + "Y: " + finY, Toast.LENGTH_SHORT).show();
                        }
                        break;
                        //return true;

                    //@Override
                    //public boolean onTouchEvent(MotionEvent event) {
                        //int x = (int)event.getX();
                        //int y = (int)event.getY();
                        //switch (event.getAction()) {
                            //case MotionEvent.ACTION_DOWN:
                          //  case MotionEvent.ACTION_MOVE:
                        //    case MotionEvent.ACTION_UP:
                      //  }
                    //    return false;
                  //  }
                }
                //return false;
                return true;
            }
        });
    }
}
