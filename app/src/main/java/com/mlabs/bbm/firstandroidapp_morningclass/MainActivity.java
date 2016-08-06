package com.mlabs.bbm.firstandroidapp_morningclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

public class MainActivity extends AppCompatActivity {
        Button login;
        EditText email, password;
        TextView show;
        Boolean showPassword=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=(Button)findViewById(R.id.loginButton);
        email=(EditText)findViewById(R.id.emailEditText);
        password=(EditText)findViewById(R.id.passwordEditText);
        show=(TextView)findViewById(R.id.showLabel);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
                                public void onClick(View view) {
                                        if(email.getText().toString().equals("crisostomodaryljhay@yahoo.com") &&
                                                        password.getText().toString().equals("daryljhay123")){
                                                Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                                                sendMessage(login);

                                                    }else if(isValidEmail(email.getText().toString())==false || password.getText().toString().length()<8){
                                                if(isValidEmail(email.getText().toString())==false){
                                                        Toast.makeText(getApplicationContext(), "Invalid E-mail!", Toast.LENGTH_SHORT).show();
                                                        email.getText().clear();
                                                        email.requestFocus();
                                                    }

                                                        if(password.getText().toString().length()<8){
                                                        Toast.makeText(getApplicationContext(), "Provide at least 8 characters long Password!", Toast.LENGTH_SHORT).show();
                                                        password.getText().clear();
                                                        password.requestFocus();
                                                    }
                                            }else{
                                                Toast.makeText(getApplicationContext(), "E-mail and Password does not match!", Toast.LENGTH_SHORT).show();
                                                email.getText().clear();
                                                password.getText().clear();
                                                email.requestFocus();
                                            }
                                    }
                            });

                        show.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View view) {
            if(showPassword){
                password.setTransformationMethod(null);
                                 showPassword=false;
                              }else{
                                  password.setTransformationMethod(new PasswordTransformationMethod());
                                  showPassword=true;
                              }
                        }
                });
        }

            public final static boolean isValidEmail(CharSequence target) {
                if (TextUtils.isEmpty(target)) {
                    return false;
                } else {
                    return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();

                }
            }


            protected  void onPause(){
                super.onPause();
               finish();
           }

            public void sendMessage(View view)
            {
                       Intent intent = new Intent(this,Main3Activity.class);
                        startActivity(intent);
            }
}

