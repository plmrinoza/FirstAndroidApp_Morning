package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;
import android.text.InputType;;


public class MainActivity extends AppCompatActivity {
    int x = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView show=(TextView)findViewById(R.id.tv_show);
        show.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {

                    case MotionEvent.ACTION_DOWN:
                        EditText pText=(EditText)findViewById(R.id.editText2);
                        pText.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case MotionEvent.ACTION_UP:
                        EditText aText=(EditText)findViewById(R.id.editText2);
                        aText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                }
                return true;
            }
        });

    }
    public void sai(View v) {
        final EditText eText=(EditText)findViewById(R.id.editText);
        final EditText pText=(EditText)findViewById(R.id.editText2);
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String passPattern = "([a-zA-Z0-9]+_?)+";
        switch (v.getId()) {
            case R.id.button:
                x ++;
                if(x<=3) {
                    if (pText.getText().length()>=1 && eText.getText().length()>=1) {
                        if (eText.getText().toString().trim().matches(emailPattern)) {
                            if (pText.getText().toString().trim().matches(passPattern)) {
                                if (pText.getText().length() >= 8) {
                                    Toast.makeText(getBaseContext(), "Login successful!", Toast.LENGTH_SHORT).show();
                                    setContentView(R.layout.blank);
                                } else
                                Toast.makeText(getBaseContext(), "Password is too short!", Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(getBaseContext(), "Invalid Password!", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(getBaseContext(), "Invalid Email!", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getBaseContext(), "Email or Password field must not be empty!", Toast.LENGTH_SHORT).show();

                }
                else{Toast.makeText(getBaseContext(), "Hacker is not allowed here!", Toast.LENGTH_SHORT).show();finish(); System.exit(0);}

                break;
        }
    }

}