package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class OnTouch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_touch);

        final ImageView img1;

        img1=(ImageView)findViewById(R.id.kil );

        img1.setOnTouchListener(new View.OnTouchListener(){

            float x1 = 0, y1 = 0, x2 = 0, y2 = 0;
            @Override

            public boolean onTouch(View view, MotionEvent motionEvent) {

                int event = motionEvent.getAction();
                switch (event) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = motionEvent.getX();
                        y1 = motionEvent.getY();
                        Toast.makeText(getApplicationContext(), "" + String.format("swipe", x1, y1), Toast.LENGTH_SHORT).show();
                        return true;
                    case MotionEvent.ACTION_UP:
                        x2 = motionEvent.getX();
                        y2 = motionEvent.getY();
                        displayAction(x1, x2, y1, y2);
                        return true;

                }
                return false;
            }
            public void displayAction(float valx1, float valx2, float valy1, float valy2)
            {
                String msg = "";
                if(valx1<valx2)
                {
                    msg = String.format("right");

                }else if(valx1 > valx2){
                    msg = String.format("left");
                }

                Toast.makeText(getApplicationContext(),"" +msg, Toast.LENGTH_SHORT).show();
                if (valy1  < valy2)
                {
                    msg = String.format("down");

                }else if (valy1 > valy2)
                {
                    msg = String.format("up");

                }
                Toast.makeText(getApplicationContext(),"" +msg, Toast.LENGTH_SHORT).show();
            }

        });
    }

}

