package com.mlabs.bbm.firstandroidapp_morningclass;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.regex.Matcher; //Used for validating email and password
import java.util.regex.Pattern;
/*
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.InputType;
import android.widget.Toast;
import android.util.Log;
import android.text.TextWatcher;
*/
import android.content.Intent;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//Yam was here
    //Database Instance name
    DatabaseHelper accountsDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accountsDb = new DatabaseHelper(this);

        final EditText loginEmail = (EditText) findViewById(R.id.editTextEmail);
        final EditText loginPassword = (EditText) findViewById(R.id.editTextPass);
        final TextView show = (TextView) findViewById(R.id.textViewShowPassword);
        final Button loginwithregex = (Button) findViewById(R.id.loginButton);
        //In order to register
        final TextView register = (TextView) findViewById(R.id.textViewRegister);

        loginwithregex.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String loginE = loginEmail.getText().toString().trim();
                String loginPass = loginPassword.getText().toString().trim();
               /* if (!validateEmail(loginE)) {
                    loginEmail.setError("Invalid Email");
                    loginEmail.requestFocus();
                }
                else if (!validatePassword(loginPass)) {
                    loginPassword.setError("Invalid Password");
                    loginPassword.requestFocus();
                }*/
                    //Toast.makeText(MainActivity.this,"Input Validation Success", Toast.LENGTH_LONG).show();
                String verifyUser = accountsDb.getSingleEntryUname(loginE);
                String verifyEmail = accountsDb.getSingleEntryEmail(loginE);

                //If user entered his/her email address.
                if(validateEmail(loginE)){
                        if (loginPass.equals(verifyEmail)) {
                            Intent myIntent = new Intent(MainActivity.this, MainMenu.class);
                            startActivity(myIntent);
                            finish();
                        }else{
                            Toast.makeText(MainActivity.this,"Invalid Username/Email and Password", Toast.LENGTH_LONG).show();
                            loginEmail.requestFocus();
                        }
                }
                //If user entered his/her username.
                else if(loginPass.equals(verifyUser)) {
                        Intent myIntent = new Intent(MainActivity.this, MainMenu.class);
                        startActivity(myIntent);
                        finish();
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Invalid Username/Email and Password", Toast.LENGTH_LONG).show();
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
                //Toast.makeText(MainActivity.this,"Input Validation Success", Toast.LENGTH_LONG).show();

                Intent myIntent = new Intent(v.getContext(), AccountRegister.class);
                startActivityForResult(myIntent, 0);

                //fix stacking of login screen when switching to register

                finish();
            }
            });

    }
        //Return true if password is valid and false if password is invalid
    /*    protected boolean validatePassword(String password) {
            if(password!=null && password.length()>9) {
                return true;
            } else {
                return false;
            }
        }*/

        //Return true if email is valid and false if email is invalid
        protected boolean validateEmail(String email) {
            String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

            Pattern pattern = Pattern.compile(emailPattern);
            Matcher matcher = pattern.matcher(email);

            return matcher.matches();
        }

/*
    public void signIn(View v){

        final EditText loginEmail = (EditText) findViewById(R.id.editTextEmail);
        final EditText loginPassword = (EditText) findViewById(R.id.editTextPass);
        Button btnLogin=(Button) findViewById(R.id.loginButton);
        accountsDb = new DatabaseHelper(this);

        // Set On ClickListener
        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // get The User name and Password
                String email=loginEmail.getText().toString();
                String password=loginPassword.getText().toString();

                // fetch the Password form database for respective user name
               // String storedPassword=accountsDb.getSingleEntry(email);

                // check if the Stored password matches with  Password entered by user
                if(password.equals(storedPassword))
                {
                    //Toast.makeText(HomeActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                    //dialog.dismiss();
                }
                else
                {
                    //Toast.makeText(HomeActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    */

    @Override
    protected void onResume() {
        super.onResume();

    }

}

/*experiments*/

//                int event = motionEvent.getAction();
//                if(event == motionEvent.ACTION_DOWN) {
//                    Log.d("onTouchListener", "ACTION_DOWN was pressed.");
//                    //loginPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
//                    loginPassword.setTransformationMethod(null);
//                    return true;
//                } else {
//                    Log.d("onTouchListener", "ACTION_DOWN was released.");
//                    //loginPassword.setInputType(129);
//                    loginPassword.setTransformationMethod(new PasswordTransformationMethod());
//                    return true;
//                }

/* Button next = (Button) findViewById(R.id.loginButton);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MainMenu.class);
                startActivityForResult(myIntent, 0);
            }
        }); // ");" is used for events
*/


/*public class MainActivity extends AppCompatActivity {
    EditText editTxt;
    private EditText regresult;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        editTxt = (EditText) findViewById(R.id.editID);
        regresult = (TextView) findViewById(R.id.txtID);
        String urName = editTxt.getText().toString();
        editTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editTxt.getText().toString().matches("/^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$/;")) {
                    regresult.setText("");
                } else {
                    regresult.setText("invalid number");
                }
            }
        });
    }
 */
