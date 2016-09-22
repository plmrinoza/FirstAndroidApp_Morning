package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationForm extends AppCompatActivity {


    DataBase accountsDb;


    Button registerButton, viewAccountsButton, backButton;
    EditText registerEmail, registerPassword, registerVerifyPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        accountsDb = new DataBase(this);


        registerEmail = (EditText) findViewById(R.id.editText2);
        registerPassword = (EditText) findViewById(R.id.editText4);
        registerVerifyPassword = (EditText) findViewById(R.id.editText5);
        registerButton = (Button) findViewById(R.id.button4);

        backButton = (Button) findViewById(R.id.buttonBack);
        addAccount();
        viewAll();
        back();
    }

    public void back(){
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
                onPause();
                finish();
            }
        });
    }


    public void addAccount() {

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=registerEmail.getText().toString();
                String password=registerPassword.getText().toString();
                String verifyPassword = registerVerifyPassword.getText().toString();

                if(email.equals("")||password.equals("")||verifyPassword.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "requirement missing.", Toast.LENGTH_LONG).show();
                    return;
                }else if (!validateEmail(email)) {
                    registerEmail.setError("input must be an email");
                    registerEmail.requestFocus();
                }
                else if (!validatePassword(password)) {
                    registerPassword.setError("atleast 10 characters");
                    registerPassword.requestFocus();
                }

                else if(!password.equals(verifyPassword))
                {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {

                    accountsDb.insertAccount(email, password);
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }




    public void viewAll() {




    }



    protected boolean validateEmail(String email) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    protected boolean validatePassword(String password) {
        if(password!=null && password.length()>9) {
            return true;
        } else {
            return false;
        }
    }



    @Override
    protected void onPause() {
        super.onPause();
    }
}
