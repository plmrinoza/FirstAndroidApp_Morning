package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final ImageView imgView = (ImageView)findViewById(R.id.bg);

        imgView.setOnTouchListener(new View.OnTouchListener() {
            float initX = 0, initY = 0, finalX = 0, finalY = 0;

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
                  Toast.makeText(getApplicationContext(), ""+String.format("ACTION_UP>>>X:%.2f,Y:%.2f",finalX, finalY), Toast.LENGTH_SHORT).show();
                  displayAction(initX, finalX, initY, finalY);
                  return false;
                }return false;
            }
        });


    }

    public void displayAction(float x1, float x2, float y1, float y2){
        String msg = "";

        if(x1 == x2){

        }else if(x1 > x2){
            msg = String.format("Swiped right to left");
        }else if(x1 < x2){
            msg = String.format("Swiped left to right");
        }

        Toast.makeText(getApplicationContext(), ""+msg, Toast.LENGTH_SHORT).show();

        if(y1 == y2){
            msg = String.format("Swiped up to down");
        }else if(y1 > y2){
            msg = String.format("Swiped down to up");
        }else if (y1 < y2){

        }

        Toast.makeText(getApplicationContext(), ""+msg, Toast.LENGTH_SHORT).show();
    }
}
