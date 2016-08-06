package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login = (Button) findViewById(R.id.btnLogin);



        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

               EditText emailAdd =  (EditText) findViewById(R.id.etxtEmailAdd);
               EditText pword =  (EditText) findViewById(R.id.etxtPword);

               if (checkLogin(emailAdd.getText().toString(),pword.getText().toString())== true) {
                    setContentView(R.layout.activity_main2);
               }
               else {
                   AlertDialog alertDialog = new AlertDialog.Builder(
                           MainActivity.this).create();

                   // Setting Dialog Message
                   alertDialog.setMessage("Invalid Email Address/Password");
                   // Showing Alert Message
                   alertDialog.show();
               }

           }
        });

    }

    public boolean checkLogin(String emailAdd, String pword) {
        String regexEmail = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";

        Pattern p = Pattern.compile(regexEmail);
        Matcher m = p.matcher(emailAdd);

        if (pword.length() >= 8 && m.matches()) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    protected  void onPause(){
        super.onPause();
        finish();
    }


}
