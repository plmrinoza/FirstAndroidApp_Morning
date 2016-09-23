package com.mlabs.bbm.firstandroidapp_morningclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SignupActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_signup3);

            final DataBaseAdapter sqlDB = new DataBaseAdapter(getApplicationContext());

            final EditText email = (EditText)findViewById(R.id.editTextEmail);
        final EditText password = (EditText)findViewById(R.id.editTextPassword);
        final EditText verifyPassword = (EditText)findViewById(R.id.editTextVerifyPassword);
        final Button btnRegister = (Button)findViewById(R.id.buttonRegister);

        final String emailInput = email.getText().toString().trim();
        final String passwordInput = password.getText().toString().trim();
        final String passwordInputVerify = verifyPassword.getText().toString().trim();

        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d(getApplicationContext().toString(),"BENJAR CLICK!");
                if (!emailInput.isEmpty() && !passwordInput.isEmpty() && !passwordInputVerify.isEmpty()) {
                    Log.d(getApplicationContext().toString(), "BENJAR");
                    if (passwordInput.equals(passwordInputVerify)) {
                        Log.d(SignupActivity3.this.toString(), "Signing up..");
                        sqlDB.registerUser(emailInput, passwordInput, getCurrentDateTime());
                        Toast.makeText(getApplicationContext(), "User successfully added", Toast.LENGTH_LONG).show();
                        Intent goBackToLoginScreen = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(goBackToLoginScreen);
                    } else {
                        Toast.makeText(getApplicationContext(), "Password did not match", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });
    }
    public String getCurrentDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //get current date time with Date()
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        return dateFormat.format(date).toString();
    }
}
