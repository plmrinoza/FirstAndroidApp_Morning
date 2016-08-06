package com.mlabs.bbm.firstandroidapp_morningclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText uT;

    EditText pT;

    Button Button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        uT = (EditText) findViewById(R.id.userText);
        pT = (EditText) findViewById(R.id.passText);
        Button = (Button) findViewById(R.id.button);

        Button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                if (isEmailValid(uT.getText().toString(),pT.getText().toString())){
                    Intent intent = new Intent(MainActivity.this,login.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_LONG).show();


            }
        });
    }
    boolean isEmailValid(String x, String y) {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(x).matches() && y.length() >= 8 && y.length() !=0) {
            return true;
        }
        else
            return false;
    }

    @Override
    protected void onPause(){
        super.onPause();
        finish();

    }
}
