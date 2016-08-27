package com.mlabs.bbm.firstandroidapp_morningclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.widget.EditText;
import android.view.View;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
{

    private EditText emailEditText;
    private EditText pasEditText;
    private TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = (EditText) findViewById(R.id.eml);
        pasEditText = (EditText) findViewById(R.id.pass);
        show = (TextView) findViewById(R.id.Show);

        findViewById(R.id.button).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                final String email = emailEditText.getText().toString();
                if (!isValidEmail(email)) {
                    emailEditText.setError("Invalid Email");
                }

                final String pass = pasEditText.getText().toString();
                if (!isValidPassword(pass)) {
                    pasEditText.setError("Invalid Password");
                }

                if (isValidEmail(email) && isValidPassword(pass)) {
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(intent);
                }

            }
        });
        show.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        pasEditText.setTransformationMethod(null);
                        pasEditText.setSelection(pasEditText.getText().length());
                        return true;
                    case MotionEvent.ACTION_UP:
                        pasEditText.setTransformationMethod(new PasswordTransformationMethod());
                        pasEditText.setSelection(pasEditText.getText().length());
                        return false;

                }
                return false;


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

  //  show=(TextView) findViewById(R.id.Show);
    //
    /*show=(TextView)findviewbyid(R.id.show);


    R.id.show.setonclicktouch()
    {
        if(showpassword)
        {
            password.settransformationmethod(null);
            show.settext()
        }
    }
    */

    //showEditText = (TextView) findViewById(R.id.show);
   /* showEditText.setOnTouchlistener(new View.OnTouchListener()
    {
      @Override
              public boolean onTouch(View view, MotionEvent motionEvent)
        {
            int event = motionEvent getActon();
            if(event == motionEvent.ACTION_DOWN)
            {
                Log.d("onTouchListener","ACTION_DOWN was pressed");
                pasEditText.setTransformationMethod(null);
                return true;
            }
            else
            {
                Log.d("onTouchListener","ACTION_DOWN was released");
                pasEditText.setTransformationMethod(new PasswordTransformationMethod());
                return false;
            }

        }
    });*/


            @Override
            protected  void onPause(){
            super.onPause();
            finish();
        }
}

