package com.mlabs.bbm.firstandroidapp_morningclass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText email = (EditText) findViewById(R.id.etEmail);
        final EditText password = (EditText) findViewById(R.id.etPassword);
        final Button btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                    if (!isValidEmail(email.getText().toString())) {
                        email.setError("Invalid Email");
                        email.requestFocus();

                    }
                   else if (!isValidPassword(password.getText().toString())) {
                        password.setError("Invalid Password");
                        password.requestFocus();

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Login Successful!",Toast.LENGTH_SHORT).show();
                        Intent loginIntent = new Intent(MainActivity.this, MAIN.class);
                        startActivity(loginIntent);
                    }
                }
            });
        }
        private boolean isValidEmail(String email) {
            String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            Pattern pattern = Pattern.compile(emailPattern);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }
    private boolean isValidPassword(String password) {
        if (password != null && password.length() > 8) {
            return true;
        }
        return false;
    }

    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
}














