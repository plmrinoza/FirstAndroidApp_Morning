package com.example.joarpcss.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final DatabaseAdapter sqlDB = new DatabaseAdapter(getApplicationContext());

        final EditText email = (EditText) findViewById(R.id.eTxtEmail);
        final EditText password = (EditText) findViewById(R.id.eTxtPassword);
        final EditText confirmPassword = (EditText) findViewById(R.id.eTxtConfPassword);
        final EditText username = (EditText) findViewById(R.id.eTxtUser);
        final EditText firstname = (EditText) findViewById(R.id.eTxtFname);
        final EditText lastname = (EditText) findViewById(R.id.eTextLname);
        Button btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailAdd = email.getText().toString();
                String pword = password.getText().toString();
                String confPassword = confirmPassword.getText().toString();
                String uname = username.getText().toString();
                String fname = firstname.getText().toString();
                String lname = lastname.getText().toString();

                if (!emailAdd.isEmpty() && !pword.isEmpty() && !confPassword.isEmpty() && !uname.isEmpty() && !fname.isEmpty() && !lname.isEmpty()) {
                    if (validateName(fname) == true && validateName(lname) == true) {
                        if (validateEmail(emailAdd) == true && validatePassword(pword) == true)
                            if (sqlDB.validateEmail(emailAdd) == true) {
                               if (sqlDB.validateUserName(uname) == true) {
                                    if (pword.equals(confPassword)) {
                                        sqlDB.registerUser(emailAdd, pword, uname, fname, lname, getDateTime());
                                        Toast.makeText(getApplicationContext(), "Account created successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Password did not match", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                   Toast.makeText(getApplicationContext(), "Username already exists.", Toast.LENGTH_SHORT).show();
                               }
                            } else {
                                Toast.makeText(getApplicationContext(), "Email Address already exists", Toast.LENGTH_SHORT).show();
                            }
                        else if (validateEmail(emailAdd) == false && validatePassword(pword) == true) {
                            Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                        } else if (validateEmail(emailAdd) == true && validatePassword(pword) == false) {
                            Toast.makeText(getApplicationContext(), "Password must be more than 8 characters", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Names must not contain numbers", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please fill up required fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public boolean validateEmail(String emailAdd) {
        String regexEmail = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";

        Pattern p = Pattern.compile(regexEmail);
        Matcher m = p.matcher(emailAdd);

        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validatePassword(String pword) {
        if (pword.length() >= 8) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateName(String name) {
        String regexEmail = "^[a-zA-Z]+[\\-'\\s]?[a-zA-Z ]+$";
        Pattern p = Pattern.compile(regexEmail);
        Matcher m = p.matcher(name);

        if (m.matches()) {
            return true;
        } else {
            return false;
        }

    }

}
