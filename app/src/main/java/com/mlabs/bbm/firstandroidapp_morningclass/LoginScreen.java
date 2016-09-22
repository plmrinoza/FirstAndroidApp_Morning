package com.mlabs.bbm.firstandroidapp_morningclass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    private TextView toggleTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());

        UsersDataSource usersDataSource = new UsersDataSource(getApplicationContext());
        usersDataSource.open();
        usersDataSource.createUser("john_aparejado@yahoo.com","tutunogtunog",""+formattedDate);

        loginBtn = (Button)findViewById(R.id.loginBtn);
        emailEdtTxt = (EditText)findViewById(R.id.emailEditTxt);
        passwordEdtTxt = (EditText)findViewById(R.id.passwordEditTxt);
        emailTxtView = (TextView)findViewById(R.id.emailTextView);
        passwordTxtView = (TextView)findViewById(R.id.passwordTextView);
        toggleTxt = (TextView)findViewById(R.id.toggleTxt);

        validation = new Validation(usersDataSource, emailTxtView, passwordTxtView);

        loginBtn.setOnClickListener(new BtnListener());
        emailEdtTxt.setOnTouchListener(new ETextListener());
        passwordEdtTxt.setOnTouchListener(new ETextListener());
        toggleTxt.setOnTouchListener(new ETextListener());
    }

    private class BtnListener implements View.OnClickListener{

        @Override
        public void onClick(View v){

            if(v.equals(loginBtn)){
                boolean isLoginValid = false;

                isLoginValid = validation.validateLogin(emailEdtTxt.getText().toString(), passwordEdtTxt.getText().toString());

                if(isLoginValid){
                    Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }
    }

    private class ETextListener implements View.OnTouchListener{
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (v.equals(emailEdtTxt))
                emailTxtView.setText("");


            if (v.equals(passwordEdtTxt))
                passwordTxtView.setText("");


            if(v.equals(toggleTxt)) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        passwordEdtTxt.setTransformationMethod(null);
                        passwordEdtTxt.setSelection(passwordEdtTxt.getText().length());
                        return true;
                    case MotionEvent.ACTION_UP:
                        passwordEdtTxt.setTransformationMethod(new PasswordTransformationMethod());
                        passwordEdtTxt.setSelection(passwordEdtTxt.getText().length());
                        return false;
                }
            }

            return false;
        }
    }
}
