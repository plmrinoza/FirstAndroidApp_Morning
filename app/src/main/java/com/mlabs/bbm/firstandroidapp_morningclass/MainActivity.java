package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button login;
    EditText u, p;
    TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.button);
        u = (EditText) findViewById(R.id.editText);
        p = (EditText) findViewById(R.id.editText1);
        show = (TextView) findViewById(R.id.textView);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidEmail(u.getText().toString(),p.getText().toString())){
                    Intent intent = new Intent(MainActivity.this,blank.class );
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_LONG).show();
            }
        });

        show.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("event", "down");
                        p.setTransformationMethod(null);
                        return true;
                    case MotionEvent.ACTION_UP:
                        Log.d("event", "up");
                        p.setTransformationMethod(new PasswordTransformationMethod());
                        return true;
                }
                return false;
            }
        });
    }

    boolean isValidEmail(String x, String y) {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(x).matches() && y.length() >= 8 && y.length() != 0) {
            return true;
        }
        else
            return false;

    }

    @Override
    protected  void onPause(){
        super.onPause();
        finish();
    }
}