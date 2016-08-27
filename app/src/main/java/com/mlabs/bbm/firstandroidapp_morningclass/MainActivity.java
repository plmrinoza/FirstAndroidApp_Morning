package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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
                if(email.getText().toString().equals("markhersonhuelgas@gmail.com") &&

                        password.getText().toString().equals("mark04herson29")){
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(intent);

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

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()>0){
                    show.setVisibility(View.VISIBLE);
                }else{
                    show.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

//        show.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//              if(showPassword){
//                  password.setTransformationMethod(null);
//                  show.setText("Hide");
//                  showPassword=false;
//              }else{
//                  password.setTransformationMethod(new PasswordTransformationMethod());
//                  show.setText("Show");
//                  showPassword=true;
//              }
//            }
//        });

        show.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View view, MotionEvent event){
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        password.setTransformationMethod(null);
                        return true;
                    case MotionEvent.ACTION_UP:
                        password.setTransformationMethod(new PasswordTransformationMethod());
                        return false;
                    case MotionEvent.ACTION_CANCEL:
                        password.setTransformationMethod(new PasswordTransformationMethod());
                        return false;
                    default:
                        return false;
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
}
