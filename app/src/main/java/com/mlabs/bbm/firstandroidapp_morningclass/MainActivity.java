package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;

public class MainActivity extends Activity {

    EditText User, Pass, showPass;
    Button AccessBtn, signUpBtn;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AccessBtn = (Button) findViewById(R.id.BLogin);
        signUpBtn = (Button) findViewById(R.id.supB);
        User = (EditText) findViewById(R.id.Uname);
        Pass = (EditText) findViewById(R.id.Pword);
        showPass = (EditText) findViewById(R.id.show);


        AccessBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (validEmailadd(User.getText().toString(), Pass.getText().toString())) {
                    Intent intent = new Intent(MainActivity.this, Loggedin.class);
                    startActivity(intent);
                } else
                    Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_LONG).show();
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Sign_Up.class);
                startActivity(intent);
            }
        });
        showPass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("event", "down");
                        Pass.setTransformationMethod(null);
                        return true;
                    case MotionEvent.ACTION_UP:
                        Log.d("event", "down");
                        Pass.setTransformationMethod(new PasswordTransformationMethod());
                        return true;
                }
                return false;
            }
        });
    }


    boolean validEmailadd(String x, String y){
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(x).matches() && y.length() >= 8 && y.length() !=0){
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
