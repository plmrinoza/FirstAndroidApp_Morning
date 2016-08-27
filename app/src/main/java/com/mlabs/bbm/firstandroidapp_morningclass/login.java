package com.mlabs.bbm.firstandroidapp_morningclass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.util.Log;

/**
 * Created by Fatima Gargar on 8/4/2016.
 */

public class login extends Activity {
    private EditText emailEditText;
    private EditText passEditText;
    private TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        emailEditText = (EditText) findViewById(R.id.editText_email);
        passEditText = (EditText) findViewById(R.id.editText_password);
        show = (TextView) findViewById(R.id.show);

        findViewById(R.id.btn_signup).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                final String email = emailEditText.getText().toString();
                if (!isValidEmail(email)) {
                    emailEditText.setError("Invalid Email");
                }

                final String pass = passEditText.getText().toString();
                if (!isValidPassword(pass)) {
                    passEditText.setError("Invalid Password");
                }
                if (isValidEmail (email) && isValidPassword (pass)){
                    Intent intent= new Intent(login.this,NextLayout.class);
                    startActivity(intent);
                }

            }
        });
        show.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent){
                int event = motionEvent.getAction();
               // if (event == motionEvent.ACTION_DOWN){
                 //   Log.d("onTouchListener", "ACTION_DOWN was pressed");
                 //   passEditText.setTransformationMethod(new PasswordTransformationMethod());
                //}
                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        passEditText.setTransformationMethod(null);
                        passEditText.setSelection(passEditText.getText().length());
                        return true;
                    case MotionEvent.ACTION_UP:
                        passEditText.setTransformationMethod(new PasswordTransformationMethod());
                        passEditText.setSelection(passEditText.getText().length());
                        return false;
                }
                return false;
            }

        });
    }

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 8) {
            return true;
        }
        return false;

    }
}
