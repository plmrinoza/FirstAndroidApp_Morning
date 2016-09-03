package com.mlabs.bbm.firstandroidapp_morningclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class OnTouchActivity extends AppCompatActivity {
    ImageView finim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_touch);

        finim = (ImageView) findViewById(R.id.finImage);

        finim.setOnTouchListener(new View.OnTouchListener() {
            float initX,initY,finalX,finalY;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        initX = motionEvent.getX();
                        initY = motionEvent.getY();
                        Toast.makeText(getApplicationContext(), "Value of X: " +initX + " Y: " +initY, Toast.LENGTH_LONG).show();
                        Log.d(getApplicationContext().toString(),"IMAGE WAS PRESSED");

                    case MotionEvent.ACTION_UP:
                        finalX = motionEvent.getX();
                        finalY = motionEvent.getY();

                        if (initX < finalX){
                            Log.d(getApplicationContext().toString(),"LEFT TO RIGHT SWIPE");
                            Toast.makeText(OnTouchActivity.this, "LEFT TO RIGHT SWIPE, X: " +finalX + "Y: " + finalY, Toast.LENGTH_LONG).show();
                        }
                        else if (initX > finalX){
                            Log.d(getApplicationContext().toString(),"RIGHT TO LEFT SWIPE");
                            Toast.makeText(OnTouchActivity.this, "RIGHT TO LEFT SWIPE, X: " +finalX + "Y: " + finalY, Toast.LENGTH_LONG).show();
                        }
                        if (initY > finalY){
                            Log.d(getApplicationContext().toString(),"DOWN TO UP SWIPE");
                            Toast.makeText(OnTouchActivity.this, "DOWN TO UP SWIPE, X: " +finalX + "Y: " + finalY, Toast.LENGTH_LONG).show();
                        }
                        else if (initY < finalY){
                            Log.d(getApplicationContext().toString(),"UP TO DOWN SWIPE");
                            Toast.makeText(OnTouchActivity.this, "UP TO DOWN SWIPE, X: " +finalX + "Y: " + finalY, Toast.LENGTH_LONG).show();
                        }
                        return true;
                }
                return false;
            }
        });
    }
}
