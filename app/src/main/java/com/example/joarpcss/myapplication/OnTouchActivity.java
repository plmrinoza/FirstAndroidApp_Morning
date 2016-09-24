package com.example.joarpcss.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class OnTouchActivity extends AppCompatActivity {


    private float x;
    private float y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_touch);


        final ImageView imageOnTouch = (ImageView) findViewById(R.id.imgViewOnTouch);


        imageOnTouch.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {


                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x = event.getX();
                        y = event.getY();
                        Toast.makeText(OnTouchActivity.this, "x=" + x + "y=" + y, Toast.LENGTH_LONG).show();
                        break;

                    case MotionEvent.ACTION_UP:

                        float finalX = event.getX();
                        float finalY = event.getY();

                        if (x > finalX)
                            Toast.makeText(OnTouchActivity.this, "SWIPE LEFT x=" + finalX + "y=" + finalY, Toast.LENGTH_LONG).show();
                        if (x < finalX)
                            Toast.makeText(OnTouchActivity.this, "SWIPE RIGHT x=" + finalX + "y=" + finalY , Toast.LENGTH_LONG).show();
                        if (y < finalY)
                            Toast.makeText(OnTouchActivity.this, "SWIPE DOWN x=" + finalX + "y=" + finalY, Toast.LENGTH_LONG).show();
                        if (y > finalY)
                            Toast.makeText(OnTouchActivity.this, "SWIPE UP x=" + finalX + "y=" + finalY , Toast.LENGTH_LONG).show();
                        break;
                }

                return true;
            }
        });
    }
}
