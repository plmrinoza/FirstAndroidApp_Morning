package com.mlabs.bbm.firstandroidapp_morningclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.View.OnTouchListener;
public class OnTouchActivity extends AppCompatActivity {
    //ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_touch);
        final ImageView image = (ImageView) findViewById(R.id.imageView1);

        image.setOnTouchListener(new View.OnTouchListener() {
            float initX = 0, initY = 0, finalX = 0, finalY = 0;
            String mss = "";

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        initX = motionEvent.getX();
                        initY = motionEvent.getY();
                        Toast.makeText(getApplicationContext(), ""+String.format("ACTION_DOWN>>>X:%.2f,Y:%.2f",initX, initY), Toast.LENGTH_SHORT).show();
                       return true;
                    case MotionEvent.ACTION_UP:
                        finalX = motionEvent.getX();
                        finalY = motionEvent.getY();
                        if( initX<finalX){
                            mss = String.format("SWIPED LEFT TO RIGHT");
                        }else if(initX > finalX){
                            mss = String.format("SWIPED RIGHT TO LEFT");
                        }
                        Toast.makeText(getApplicationContext(), ""+mss, Toast.LENGTH_SHORT).show();
                        if(initY < finalY){
                            mss = String.format("SWIPED UP TO DOWN");
                        }else if(initY > finalY){
                            mss = String.format("SWIPED DOWN TO UP");
                        }
                        Toast.makeText(getApplicationContext(), ""+mss, Toast.LENGTH_SHORT).show();
                       return true;
                }
                return false;
            }
        });

    }

    Intent intent2 = getIntent();
}


