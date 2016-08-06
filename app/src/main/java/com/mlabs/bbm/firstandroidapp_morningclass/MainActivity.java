package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button login;
    EditText u, p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.button);
        u = (EditText) findViewById(R.id.editText);
        p = (EditText) findViewById(R.id.editText1);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidEmail(u.getText().toString(),p.getText().toString())){
                    Intent intent = new Intent(MainActivity.this,blank.class );
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_LONG).show();
            }
        });
    }
    boolean isValidEmail(String x, String y) {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(x).matches() && y.length() >= 8 && y.length() != 0) {
            return true;
        }
        else
            return false;

    }

    @Override
    protected  void onPause(){
        super.onPause();
        finish();
    }
}