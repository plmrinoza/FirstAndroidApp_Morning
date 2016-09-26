package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
<<<<<<< HEAD
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;

import com.mlabs.bbm.firstandroidapp_morningclass.MainAct.DatabaseHelper;

=======
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
>>>>>>> daf1a508abdefe32f94f5c4ee200722e92e1d82b
import java.util.regex.Matcher;
import java.util.regex.Pattern;


<<<<<<< HEAD
    DatabaseHelper accountsDb;

=======
public class MainActivity extends AppCompatActivity {
>>>>>>> daf1a508abdefe32f94f5c4ee200722e92e1d82b
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
        accountsDb = new DatabaseHelper(this);

        final EditText loginEmail = (EditText) findViewById(R.id.editText_user;
        final EditText loginPassword = (EditText) findViewById(R.id.editText2_password);
        final TextView show = (TextView) findViewById(R.id.textView2_show);
        final Button loginwithregex = (Button) findViewById(R.id.buttonSubmit);
        final TextView register = (TextView) findViewById(R.id.textView2_Signup);

        loginwithregex.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String loginE = loginEmail.getText().toString().trim();
                String loginPass = loginPassword.getText().toString().trim();
                String verifyUser = accountsDb.getSinlgeEntry(loginE);
                    if(loginPass.equals(verifyUser)) {
                        String verifyUser = accountsDb.getSingleEntryUname(loginE);
                        String verifyEmail = accountsDb.getSingleEntryEmail(loginE);
                            if(validateEmail(loginE)){
                                if (loginPass.equals(verifyEmail)) {
                                    Intent myIntent = new Intent("com.mlabs.bbm.firstandroidapp_morningclass.MainActivity.user");
                                    startActivity(myIntent);
                                    finish();
                                    Toast.makeText(MainActivity.this,"Invalid Email and Password", Toast.LENGTH_LONG).show();
                                    loginEmail.requestFocus();
                                }
                            }
                               else if(loginPass.equals(verifyUser)) {
                                Intent myIntent = new Intent("com.mlabs.bbm.firstandroidapp_morningclass.MainActivity.user");
                                startActivity(myIntent);
                                finish();
                            }
                            else{
                                Toast.makeText(MainActivity.this,"Invalid Email and Password", Toast.LENGTH_LONG).show();
                                loginEmail.requestFocus();
                            }
                    }

            });

            show.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            loginPassword.setTransformationMethod(null);
                            return true;
                        case MotionEvent.ACTION_UP:
                            loginPassword.setTransformationMethod(new PasswordTransformationMethod());
                            return false;
                    }
                    return false;
                }

            });

            register.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(v.getContext(), "com.mlabs.bbm.firstandroidapp_morningclass.MainActivity.AccountRegister");
                    startActivityForResult(myIntent, 0);
                    onPause();
                }
            });

        }
    protected boolean validateEmail(String email) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
                }
                            }
    }
=======

        Button login1 = (Button) findViewById(R.id.login);
        login1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final EditText Email1 = (EditText) findViewById(R.id.Email);
                final EditText Pw1 = (EditText) findViewById(R.id.Pw);

                String email = Email1.getText().toString();
                String password = Pw1.getText().toString();

                if (!validateEmail(email)) {
                    Email1.setError("Not a valid email address!");
                } else if (!validatePassword(password)) {
                    Pw1.setError("Not a valid password!");
                } else {
                    Email1.setError(null);
                    Pw1.setError(null);
                    doLogin();
                }
            }
        });
    }

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validatePassword(String Pw) {
        return Pw.length() >=8;
    }

    public void doLogin() {
        Toast.makeText(getApplicationContext(), "Successfully Logged-in", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MainActivity.this, NextScreen.class);

        startActivity(i);


    }

}



>>>>>>> daf1a508abdefe32f94f5c4ee200722e92e1d82b
