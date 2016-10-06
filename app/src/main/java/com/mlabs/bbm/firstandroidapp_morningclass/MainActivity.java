package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button signIn;
    EditText ue, p;
    TextView show,signUp;
    DbHandler db = new DbHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signIn = (Button) findViewById(R.id.button);
        ue = (EditText) findViewById(R.id.editText);
        p = (EditText) findViewById(R.id.editText1);
        show = (TextView) findViewById(R.id.textView);
        signUp = (TextView) findViewById(R.id.txtSignUp);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String credential = ue.getText().toString();
                String pass = p.getText().toString();

                if (db.loginCheck(credential, pass)){
                    Intent intent = new Intent(MainActivity.this,LoggedIn.class );
                    startActivity(intent);
                    finish();
                }
//                if (isValidEmail(ue.getText().toString(),p.getText().toString())){
//                    Intent intent = new Intent(MainActivity.this,LoggedIn.class );
//                    startActivity(intent);
//                    finish();
//                }
                else
                    Toast.makeText(getApplicationContext(), "Invalid Login Credentials!", Toast.LENGTH_LONG).show();
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

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
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
}