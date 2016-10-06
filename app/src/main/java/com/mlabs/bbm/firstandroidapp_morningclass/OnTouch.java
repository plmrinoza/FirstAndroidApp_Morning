package com.mlabs.bbm.firstandroidapp_morningclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.view.InputEvent;
import android.widget.Toast;

public class OnTouch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ontouch);

        final ImageView image = (ImageView)findViewById(R.id.ontouch_image);

        image.setOnTouchListener(new View.OnTouchListener() {

            float x1, y1, x2, y2;

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        y1 = event.getY();

                        Toast.makeText(getApplicationContext(), "x1 = " + x1 + " | y1 = " + y1
                                , Toast.LENGTH_LONG).show();
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        y2 = event.getY();

                        if (x1 < x2)
                            Toast.makeText(getApplicationContext(), "x2 = " + x1 + " | y2 = " + y1 +
                                " | right", Toast.LENGTH_LONG).show();
                        if (x1 > x2)
                            Toast.makeText(getApplicationContext(), "x2 = " + x1 + " | y2 = " + y1 +
                                    " | left", Toast.LENGTH_LONG).show();
                        if (y1 < y2)
                            Toast.makeText(getApplicationContext(), "x2 = " + x1 + " | y2 = " + y1 +
                                    " | down", Toast.LENGTH_LONG).show();
                        if (y1 > y2)
                            Toast.makeText(getApplicationContext(), "x2 = " + x1 + " | y2 = " + y1 +
                                    " | up", Toast.LENGTH_LONG).show();

                }
                return true;
            }
        });





    }
}
