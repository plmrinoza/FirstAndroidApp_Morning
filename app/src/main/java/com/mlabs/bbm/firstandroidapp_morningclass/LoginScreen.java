package com.mlabs.bbm.firstandroidapp_morningclass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by ChristianJohn on 8/4/2016.
 */
public class LoginScreen extends Activity{

    private Button loginBtn;
    private Validation validation;
    private EditText emailEdtTxt;
    private EditText passwordEdtTxt;
    private TextView emailTxtView;
    private TextView passwordTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        loginBtn = (Button)findViewById(R.id.loginBtn);
        emailEdtTxt = (EditText)findViewById(R.id.emailEditTxt);
        passwordEdtTxt = (EditText)findViewById(R.id.passwordEditTxt);
        emailTxtView = (TextView)findViewById(R.id.emailTextView);
        passwordTxtView = (TextView)findViewById(R.id.passwordTextView);

        validation = new Validation(emailTxtView, passwordTxtView);

        loginBtn.setOnClickListener(new BtnListener());
        emailEdtTxt.setOnTouchListener(new ETextListener());
        passwordEdtTxt.setOnTouchListener(new ETextListener());
    }

    private class BtnListener implements View.OnClickListener{

        @Override
        public void onClick(View v){

            if(v.equals(loginBtn)){
                boolean isEmailValid = false;
                boolean isPasswordCorrect = false;
                String email = emailEdtTxt.getText().toString();
                email = email.replace(" ","");
                String password = passwordEdtTxt.getText().toString();

                if(validation.ifCorrectEmailFormat(email)) {
                    if (validation.ifEmailMatched(email)) {
                        isEmailValid = true;
                    }
                }

                if(validation.ifPLengthIsSufficient(password, isEmailValid)){
                    if(validation.ifPasswordMatched(password)){
                        isPasswordCorrect = true;
                    }
                }

                if(isEmailValid && isPasswordCorrect){
                    Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        }
    }

    private class ETextListener implements View.OnTouchListener{
        @Override
        public boolean onTouch(View v, MotionEvent event){
            if(v.equals(emailEdtTxt))
                emailTxtView.setText("");

            if(v.equals(passwordEdtTxt))
                passwordTxtView.setText("");

            return false;
        }
    }
}
