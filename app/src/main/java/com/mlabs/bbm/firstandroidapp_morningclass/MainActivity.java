package com.mlabs.bbm.firstandroidapp_morningclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText uT;

    EditText pT;

    Button Button;
    TextView show;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        uT = (EditText) findViewById(R.id.userText);
        pT = (EditText) findViewById(R.id.passText);
        Button = (Button) findViewById(R.id.button);
        show = (TextView) findViewById(R.id.show);


        Button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                if (isEmailValid(uT.getText().toString(),pT.getText().toString())){
                    Intent intent = new Intent(MainActivity.this,login.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_LONG).show();


            }
        });

        show.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.d("event","down");
                        pT.setTransformationMethod(null);
                        return true;
                    case MotionEvent.ACTION_UP:
                        Log.d("event","up");
                        pT.setTransformationMethod(new PasswordTransformationMethod());
                        return true;

                }
                return false;

            }
        });
    }
    boolean isEmailValid(String x, String y) {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(x).matches() && y.length() >= 8 && y.length() !=0) {
            return true;
        }
        else
            return false;
    }



    @Override
    protected void onPause(){
        super.onPause();
        finish();

    }
}
