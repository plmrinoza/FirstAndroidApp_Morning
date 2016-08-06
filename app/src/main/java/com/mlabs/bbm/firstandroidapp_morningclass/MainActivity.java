package com.mlabs.bbm.firstandroidapp_morningclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.content.Intent;
import android.view.View.OnClickListener;



public class MainActivity extends AppCompatActivity
{

    private EditText emailEditText;
    private EditText pasEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = (EditText) findViewById(R.id.eml);
        pasEditText = (EditText) findViewById(R.id.pass);
        findViewById(R.id.button).setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View arg0)
            {

                final String email = emailEditText.getText().toString();
                if (!isValidEmail(email))
                {
                    emailEditText.setError("Invalid Email");
                }

                final String pass = pasEditText.getText().toString();
                if (!isValidPassword(pass))
                {
                    pasEditText.setError("Invalid Password");
                }

                if (isValidEmail (email) && isValidPassword (pass))
                {
                    Intent intent= new Intent(MainActivity.this,validated.class);
                    startActivity(intent);
                }

            }
        });
    }


    private boolean isValidEmail(String email)
    {
        String emailPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String pass)
    {
        if (pass != null && pass.length() >= 8)
        {
            return true;
        }
        return false;

    }
}
