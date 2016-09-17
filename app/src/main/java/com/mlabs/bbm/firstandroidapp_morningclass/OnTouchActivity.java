package com.mlabs.bbm.firstandroidapp_morningclass;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class OnTouchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_touch);

        final ImageView image = (ImageView) findViewById(R.id.imageView);

        image.setOnTouchListener(new View.OnTouchListener() {
            float initX = 0, finalX = 0, initY = 0, finalY = 0;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        initX = motionEvent.getX();
                        initY = motionEvent.getY();
                        Toast.makeText(getApplicationContext(),""+String.format("[x:%,2f][y:%.2f]",motionEvent.getX(),motionEvent.getY()),Toast.LENGTH_LONG).show();
                        return true;
                    case MotionEvent.ACTION_UP:
                        initX = motionEvent.getX();
                        initY = motionEvent.getY();
                        Toast.makeText(getApplicationContext(),""+String.format("[x:%,2f][y:%.2f]",motionEvent.getX(),motionEvent.getY()),Toast.LENGTH_LONG).show();
                        actionDisplay(initX,initY,finalX,finalY);
                        return true;
                }
                return false;
            }
        });

    }

    public void actionDisplay( float initX, float finalX,float initY ,float finalY){
        String wow = "";

        if (initX < finalX){
            wow = String.format("Swiped to Right");
        }

        else if (initX < finalX) {
            wow = String.format("Swiped to Left");
        }
        Toast.makeText(getApplicationContext(),""+wow,Toast.LENGTH_SHORT).show();

        if (initY < finalY) {
            wow = String.format("Swiped Up");
        }

        else if (initY < finalY) {
            wow = String.format("Swiped Down");
        }
        Toast.makeText(getApplicationContext(),""+wow,Toast.LENGTH_SHORT).show();
    }
}
