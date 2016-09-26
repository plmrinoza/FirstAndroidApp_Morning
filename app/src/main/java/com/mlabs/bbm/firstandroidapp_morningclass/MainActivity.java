package com.mlabs.bbm.firstandroidapp_morningclass;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.MotionEvent;



public class MainActivity extends AppCompatActivity {

EditText userName,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText userName= (EditText)findViewById(R.id.etUser);
        final EditText password = (EditText) findViewById(R.id.etPassword);
        final Button btnSignup = (Button) findViewById(R.id.btnSignup);
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final Button btnShow = (Button) findViewById(R.id.btnShow);
        final Context context = this;

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String emailUname = userName.getText().toString();
                String pass = password.getText().toString();


                if (emailUname.equals("")) {

                    userName.setError("Please enter your username or email!");
                } else if (pass.equals("")) {

                    password.setError("Please enter your password!");
                } else if (!isValidPassword(pass)) {
                    Toast.makeText(getApplicationContext(), "Invalid Passwordl!", Toast.LENGTH_SHORT).show();


                } else {
                    DatabaseAdapter db = new DatabaseAdapter(context);
                    boolean login = db.validateUser(userName.getText().toString().trim(), password.getText().toString().trim());


                    if (login == true) {
                        Intent loginIntent = new Intent(MainActivity.this, blank.class);
                        startActivity(loginIntent);
                        finish();

                    } else {

                        Toast.makeText(getApplicationContext(), "Invalid Email and Passwordl!", Toast.LENGTH_SHORT).show();

                    }
                }


                btnSignup.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        Intent myIntent = new Intent(v.getContext(), Registerform.class);
                        startActivityForResult(myIntent, 0);
                        onPause();
                    }

                });


                btnShow.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {

//                         if(event == motionEvent.ACTION_DOWN){
//                             Log.d("onTouchListener", "ACTION_DOWN was pressed");
//                             password.setTransformationMethod(null);
//                             return true;
//
//                         }
//                         else{
//                             Log.d("onTouchListener", "ACTION_DOWN was released");
//                             password.setTransformationMethod(new PasswordTransformationMethod());
//                             return false;
//                         }
//
                        switch (motionEvent.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                password.setTransformationMethod(null);
                                password.setSelection(password.getText().length());
                                return true;
                            case MotionEvent.ACTION_UP:
                                password.setTransformationMethod(new PasswordTransformationMethod());
                                password.setSelection(password.getText().length());
                                return false;
                        }
                        return true;

                    }
                });
            }


            private boolean isValidPassword(String password) {
                if (password != null && password.length() > 8) {
                    return true;
                }
                return false;

            }
        });
    }
}
















