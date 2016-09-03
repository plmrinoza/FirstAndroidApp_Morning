package com.mlabs.bbm.firstandroidapp_morningclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class OnTouchActivity extends AppCompatActivity {
    private float initX = 0, initY = 0, finalX = 0, finalY = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_touch);

        final ImageView image = (ImageView)findViewById(R.id.imageView);
        image.setOnTouchListener(new View.OnTouchListener(){
           @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
               int event = motionEvent.getAction();
               switch (event) {
                   case MotionEvent.ACTION_DOWN:
                       initX = motionEvent.getX();
                       initY = motionEvent.getY();
                       Toast.makeText(getApplicationContext(), ""+String.format("Action_Down>>>X:%.2f,Y:%.2f",initX, initY), Toast.LENGTH_SHORT).show();
                       return true;
                   case MotionEvent.ACTION_UP:
                       finalX = motionEvent.getX();
                       finalY = motionEvent.getY();
                       actionDisplay(initX, finalX, initY, finalY);

               }
               return false;
           }
        });
    }
    public void actionDisplay(float initX, float finalX, float initY, float finalY){
        String msg = "";
        if(initX<finalX){
            msg = String.format("Swiped Left to Right");
        }
        else if(initX>finalX)
        {
            msg = String.format("Swiped Right to Left");
        }
        Toast.makeText(getApplicationContext(),""+msg, Toast.LENGTH_SHORT).show();

        if(initY<finalY){
            msg = String.format("Swiped Up to Down");
        }
        else if(initY>finalY)
        {
            msg = String.format("Swiped Down to Up");
        }
        Toast.makeText(getApplicationContext(),""+msg, Toast.LENGTH_SHORT).show();
    }

}
