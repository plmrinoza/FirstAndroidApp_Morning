package com.mlabs.bbm.firstandroidapp_morningclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.regex.Matcher; //Used for validating email and password
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.text.InputType;
import android.widget.Toast;
import android.util.Log;
import android.text.TextWatcher;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText loginEmail = (EditText) findViewById(R.id.editTextEmail);
        final EditText loginPassword = (EditText) findViewById(R.id.editTextPass);
        final TextView show = (TextView) findViewById(R.id.textViewShowPassword);
        Button loginwithregex = (Button) findViewById(R.id.loginButton);

        loginwithregex.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (!validateEmail(loginEmail.getText().toString())) {
                    loginEmail.setError("Invalid Email");
                    loginEmail.requestFocus();
                } else if (!validatePassword(loginPassword.getText().toString())) {
                    loginPassword.setError("Invalid Password");
                    loginPassword.requestFocus();
                } else {
                    //Toast.makeText(MainActivity.this,"Input Validation Success", Toast.LENGTH_LONG).show();
                    Intent myIntent = new Intent(v.getContext(), MainMenu.class);
                    startActivityForResult(myIntent, 0);
                }

            }
        });

//        show.setOnClickListener(OnClickListener((view) {
//            if showpass
//
//        });

        show.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

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

    }
//Return true if password is valid and false if password is invalid
        protected boolean validatePassword(String password) {
            if(password!=null && password.length()>9) {
                return true;
            } else {
                return false;
            }
        }

        //Return true if email is valid and false if email is invalid
        protected boolean validateEmail(String email) {
            String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

            Pattern pattern = Pattern.compile(emailPattern);
            Matcher matcher = pattern.matcher(email);

            return matcher.matches();
        }

    protected void onPause(){
        super.onPause();
        finish();
    }
    }

/*experiments*/
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
