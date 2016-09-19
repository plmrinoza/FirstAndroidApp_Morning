package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*import android.app.Activity;*/
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;


public class Login extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passEditText;
    private TextView showText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log);

        emailEditText = (EditText) findViewById(R.id.Emailtxt);
        passEditText = (EditText) findViewById(R.id.Passwordtxt);
        showText = (TextView) findViewById(R.id.show);

        findViewById(R.id.button).setOnClickListener(new OnClickListener() {
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

                if (isValidEmail(email) && isValidPassword(pass))
                {
                    Intent intent = new Intent(Login.this,After_Login.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
        showText.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
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

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String pass){
        if (pass !=null && pass.length() > 7){
            return true;
        }
        return false;
    }
}
