package com.mlabs.bbm.firstandroidapp_morningclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText email = (EditText) findViewById(R.id.email);
        final TextView show = (TextView) findViewById(R.id.show);
        final EditText password = (EditText) findViewById(R.id.password);
        Button validate = (Button) findViewById(R.id.validate);

        validate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!validateEmail(email.getText().toString())) {
                    email.setError("Invalid Email");
                    email.requestFocus();
                } else if (!validatePassword(password.getText().toString())) {
                    password.setError("Invalid Password");
                    password.requestFocus();
                } else {
                    Toast.makeText(MainActivity.this, "Input Validation Success", Toast.LENGTH_LONG).show();
                }

            }
        });

        show.setOnTouchListener(new View.OnTouchListener(){
                       @Override
                       public boolean onTouch(View v, MotionEvent event){
                               switch ( event.getAction() ) {

                                              case MotionEvent.ACTION_DOWN:
                                               EditText pText=(EditText)findViewById(R.id.password);
                                               pText.setInputType(InputType.TYPE_CLASS_TEXT);break;
                                      case MotionEvent.ACTION_UP:
                                               EditText aText=(EditText)findViewById(R.id.password);
                                                aText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                                                break;
                                  }
                                return true;
                            }

                            });

    }

    //Return true if password is valid and false if password is invalid
    protected boolean validatePassword(String password) {
        if(password!=null && password.length()>8) {
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



}

