package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText emailValidate = (EditText) findViewById(R.id.editText);
        final EditText passwordFormat = (EditText) findViewById(R.id.editText2);
        final Button button = (Button) findViewById(R.id.logIn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailValidate.getText().toString();
                String e = "priam.alcala@gmail.com";1
                String password = passwordFormat.getText().toString();
                String p = "alcala342";
                // onClick of button perform this simplest code.
                if (email.equals(e) && password.equals(p)) {
                    Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
                    send(button);
                    Intent intent = new Intent(MainActivity.this, blank.class);
                    startActivity(intent);
                } else if (validate(email)==false || passwordFormat.getText().length() < 8) {
                    if(validate(email)==false){
                        Toast.makeText(getApplicationContext(), "Invalid E-mail Address!", Toast.LENGTH_SHORT).show();
                    }
                    if(password.length()<8){
                        Toast.makeText(getApplicationContext(), "Invalid Password!", Toast.LENGTH_SHORT).show();
                    }
                } else if (validate(email)==false && passwordFormat.getText().length() < 8)
                    Toast.makeText(getApplicationContext(), "Invalid E-Mail or Password!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public final static boolean validate(CharSequence target){
        String emialPattern = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                               "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                Pattern pattern = Pattern.compile(emaillPattern);
               Matcher matcher = pattern.matcher(email);
               return matcher.matches();
    }
    public void send(View v){
        Intent intent = new Intent(this, blank.class);
        startActivity(intent);
    }
}
