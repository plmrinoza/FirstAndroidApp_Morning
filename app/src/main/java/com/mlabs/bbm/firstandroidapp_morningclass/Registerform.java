package com.mlabs.bbm.firstandroidapp_morningclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registerform extends AppCompatActivity {
    DatabaseAdapter acctsDB;
    Button btnBack, btnSend;
    EditText regEmail,regPass,regConPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        acctsDB = new DatabaseAdapter(this);

        regEmail= (EditText) findViewById(R.id.regEmail);
        regPass = (EditText) findViewById(R.id.regPass);
        regConPass = (EditText) findViewById(R.id.regConPass);
        btnBack= (Button)findViewById(R.id.btnBack);
        btnBack= (Button)findViewById(R.id.btnBack);
        btnSend= (Button)findViewById(R.id.btnSend);
        addAcct();

    }

    public void back(){
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent myIntent= new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(myIntent,0);
                onPause();
                finish();
            }
        });
    }
     public void addAcct(){
         btnSend.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View v){
                 String email= regEmail.getText().toString().trim();
                 String password= regPass.getText().toString();
                 String confirmPassword= regConPass.getText().toString();
                 String emailPattern= "\"^[_A-Za-z0-9-]+(\\\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$\";";

                 if (email.equals("")){
                     Toast.makeText(getApplicationContext(), "Field empty", Toast.LENGTH_LONG).show();
                     return;

                 }
                 else if (password.equals("")){
                     Toast.makeText(getApplicationContext(), "Field empty", Toast.LENGTH_LONG).show();
                     return;

                 }
                  else if (confirmPassword.equals("")){
                     Toast.makeText(getApplicationContext(), "Field empty", Toast.LENGTH_LONG).show();
                     return;

                 }
                 else if(email.matches(emailPattern)){
                     Toast.makeText(getApplicationContext(),"Valid email address",Toast.LENGTH_SHORT).show();
                 }
                 else{
                     Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();


                 }
                  if (!isValidPassword(password)) {
                      Toast.makeText(getApplicationContext(),"Invalid password",Toast.LENGTH_SHORT).show();
                  }
                 if(!password.equals(confirmPassword)){
                     Toast.makeText(getApplicationContext(), "Password does not match!", Toast.LENGTH_LONG).show();
                 }
                 else
                 {
                     Toast.makeText(getApplicationContext(), "Successfuly registered!", Toast.LENGTH_LONG).show();
                 }

             }

                 private boolean isValidPassword(String password) {
                     if (password != null && password.length() > 8) {
                         return true;
                     }
                     return false;

                 }
         });
     }}



